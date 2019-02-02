package net.konzult.adventcalendar2018.day7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssemblyGuide {
    private final List<StepOrder> stepOrderList;
    private Map<Character, Step> steps = new HashMap<Character, Step>();
    private String stepSequence = "";
    private int maxElves = 5;
    private int minute;
    private int baseTime = 60;
    private int usedElves;

    public AssemblyGuide(List<StepOrder> stepOrderList) {
        this.stepOrderList = stepOrderList;
    }

    public void init() {
        for (StepOrder stepOrder : stepOrderList) {
            Step firstStep = getOrCreateStep(stepOrder.getFirst());
            Step secondStep = getOrCreateStep(stepOrder.getSecond());
            secondStep.setReady(false);
            secondStep.getPredecessors().put(firstStep.getId(), firstStep);
        }
    }

    private Step getOrCreateStep(char stepId) {
        Step step = steps.get(stepId);
        if (step == null) {
            step = new Step(stepId);
            steps.put(stepId, step);
        }
        return step;
    }

    public String getStepSequence() {
        return stepSequence;
    }

    public String calculateSimpleStepSequence() {
        Step step = getNextStep();
        while (step != null) {
            step.setReady(false);
            step.setCompleted(true);
            char stepId = step.getId();
            stepSequence += stepId;
            markFollowingReady(stepId);
            step = getNextStep();
        }
        return stepSequence;
    }

    public void work() {
        usedElves = 0;
        minute = 0;
        Step nextStep = getNextStep();
        while (!isAllStepsCompleted()) {
            while (nextStep != null && usedElves < maxElves) {
                startStep(nextStep);
                nextStep = getNextStep();
            }
            Step completedStep = steps.values().stream()
                    .filter(step -> step.isInProgress())
                    .min(Comparator.comparing(Step::getFinishMinute))
                    .get();
            completeStep(completedStep);
            nextStep = getNextStep();
        }
    }

    private boolean isAllStepsCompleted() {
        return steps.values().stream().noneMatch(step -> !step.isCompleted());
    }

    private void completeStep(Step step) {
        stepSequence += step.getId();
        step.setInProgress(false);
        step.setCompleted(true);
        usedElves--;
        markFollowingReady(step.getId());
        minute = step.getFinishMinute();
    }

    private void startStep(Step step) {
        usedElves++;
        step.setInProgress(true);
        step.setFinishMinute(minute + stepTime(step));
        step.setReady(false);
    }

    private int stepTime(Step step) {
        return step.getId() - 'A' + baseTime + 1;
    }


    private void markFollowingReady(char stepId) {
        steps.values().stream()
                .filter(step -> step.getPredecessors().containsKey(stepId))
                .filter(step -> step.getPredecessors()
                        .values().stream()
                        .noneMatch(previousStep -> !previousStep.isCompleted())
                )
                .forEach(step -> step.setReady(true));
    }


    private Step getNextStep() {
        return steps.values()
                .stream()
                .filter(Step::isReady)
                .min(Comparator.comparing(Step::getId))
                .orElse(null);
    }


    public List<StepOrder> getStepOrderList() {
        return stepOrderList;
    }

    public Map<Character, Step> getSteps() {
        return steps;
    }

    public int getMaxElves() {
        return maxElves;
    }

    public int getMinute() {
        return minute;
    }

    public int getBaseTime() {
        return baseTime;
    }

    public void setMaxElves(int maxElves) {
        this.maxElves = maxElves;
    }

    public void setBaseTime(int baseTime) {
        this.baseTime = baseTime;
    }
}
