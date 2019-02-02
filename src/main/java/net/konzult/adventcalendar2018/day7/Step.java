package net.konzult.adventcalendar2018.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Step {
    private final char id;

    public Step(char id) {
        this.id = id;
    }

    private boolean ready = true;
    private boolean completed = false;
    private boolean inProgress = false;
    private int finishMinute = 0;

    private Map<Character,Step> predecessors = new HashMap<Character,Step>();

    public char getId() {
        return id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public void setFinishMinute(int finishMinute) {
        this.finishMinute = finishMinute;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public int getFinishMinute() {
        return finishMinute;
    }

    public Map<Character, Step> getPredecessors() {
        return predecessors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return id == step.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", ready=" + ready +
                ", completed=" + completed +
                '}';
    }
}
