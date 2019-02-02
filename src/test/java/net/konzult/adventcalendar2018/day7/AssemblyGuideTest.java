package net.konzult.adventcalendar2018.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AssemblyGuideTest {

    @Test
    void testInit() throws Exception {
        List<StepOrder> stepOrders = StepOrder.readStepOrderListFile("day7test.txt");
        AssemblyGuide assemblyGuide = new AssemblyGuide(stepOrders);
        assemblyGuide.init();

        assertThat(assemblyGuide.getSteps().size()).isEqualTo(6);
    }

    @Test
    void testGetStepSequence() throws Exception {
        List<StepOrder> stepOrders = StepOrder.readStepOrderListFile("day7test.txt");
        AssemblyGuide assemblyGuide = new AssemblyGuide(stepOrders);
        assemblyGuide.init();

        assertThat(assemblyGuide.calculateSimpleStepSequence()).isEqualTo("CABDFE");
    }

    @Test
    void testWork() throws Exception {
        List<StepOrder> stepOrders = StepOrder.readStepOrderListFile("day7test.txt");
        AssemblyGuide assemblyGuide = new AssemblyGuide(stepOrders);
        assemblyGuide.init();
        assemblyGuide.setMaxElves(2);
        assemblyGuide.setBaseTime(0);
        assemblyGuide.work();

        assertThat(assemblyGuide.getStepSequence()).isEqualTo("CABFDE");
        assertThat(assemblyGuide.getMinute()).isEqualTo(15);

    }
}