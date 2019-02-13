package net.konzult.adventcalendar2018.day16;

import net.konzult.adventcalendar2018.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.konzult.adventcalendar2018.day16.Instruction.ADDR;
import static net.konzult.adventcalendar2018.day16.Instruction.MULR;
import static org.assertj.core.api.Assertions.*;

class ProcessorTest {

    private Processor processor;

    @BeforeEach
    void setUp() {
        processor = new Processor();
    }

    @Test
    void testOperationADDR() throws Exception {
        processor.setReg(new int[]{1, 2, 3, 4});

        assertThat(processor.operation(ADDR, 0, 1, 3))
                .isEqualTo(new int[]{1, 2, 3, 3});
    }

    @Test
    void testOperationMULR() throws Exception {
        processor.setReg(new int[]{3, 2, 1, 1});

        assertThat(processor.operation(MULR, 2, 1, 2))
                .isEqualTo(new int[]{3, 2, 2, 1});
    }

    @Test
    void testParseProgram() throws Exception {
        List<String> strings = FileParser.readStringListFile("day16_2.txt");
        Processor processor = new Processor();
        processor.parseProgram(strings);
        assertThat(processor.getProgram()).hasSize(898);

    }
}