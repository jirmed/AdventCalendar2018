package net.konzult.adventcalendar2018.day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PolymerTest {

    private Polymer polymer;

    @Test
    void testReduce() throws Exception {
        polymer = new Polymer("dabAcCaCBAcCcaDA");

        polymer.reduce();

        assertThat(polymer.getFormula()).isEqualTo("dabAaBAaDA");

    }

    @Test
    void testRemoveUnit() throws Exception {
        polymer = new Polymer("dabAcCaCBAcCcaDA");

        polymer.removeUnit('c');

        assertThat(polymer.getFormula()).isEqualTo("dbBD");
    }

    @Test
    void testReduceByRemovingAnyUnit() throws Exception {
        polymer = new Polymer("dabAcCaCBAcCcaDA");

        polymer.maxReduceByRemovingAnyUnit();

        assertThat(polymer.getFormula()).isEqualTo("daDA");
    }
}