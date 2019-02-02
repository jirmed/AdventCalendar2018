package net.konzult.adventcalendar2018.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StepOrderTest {

    @Test
    void testReadStepOrderListFile() throws Exception {
        List<StepOrder> stepOrders = StepOrder.readStepOrderListFile("day7test.txt");

        assertThat(stepOrders.size()).isEqualTo(7);
    }
}