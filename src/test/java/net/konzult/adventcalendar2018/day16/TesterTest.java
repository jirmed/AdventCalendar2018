package net.konzult.adventcalendar2018.day16;

import net.konzult.adventcalendar2018.FileParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.konzult.adventcalendar2018.day16.Instruction.*;
import static org.assertj.core.api.Assertions.*;

class TesterTest {
    @Test
    void testParse() throws Exception {
        List<String> strings = FileParser.readStringListFile("Day16_1test.txt");
        Tester tester = Tester.parse(strings);

        assertThat(tester.getSamples()).hasSize(5);

    }

    @Test
    void testGetValidInstructionList() throws Exception {
        Tester.Sample sample = new Tester.Sample(new int[]{3, 2, 1, 1},
                new int[]{9, 2, 1, 2}, new int[]{3, 2, 2, 1}
        );
        assertThat(Tester.getValidInstructionList(sample))
        .containsOnly(MULR, ADDI, SETI);
    }

    @Test
    void testCountSamplesWithValidInstructions() throws Exception {
        List<String> strings = FileParser.readStringListFile("Day16_1test.txt");
        Tester tester = Tester.parse(strings);
        assertThat(tester.countSamplesWithValidInstructions(3)).isEqualTo(3);
    }

    @Test
    void testCreateInstructionMap() throws Exception {
        List<String> strings = FileParser.readStringListFile("Day16_1.txt");
        Tester tester = Tester.parse(strings);
        assertThat(tester.createInstructionMap()).hasSize(values().length);
    }
}