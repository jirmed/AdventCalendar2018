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
    void testParseProgramRaw() throws Exception {
        List<String> strings = FileParser.readStringListFile("day16_2.txt");
        Processor processor = new Processor();
        processor.parseProgramRaw(strings);
        assertThat(processor.getProgram()).hasSize(898);
    }

    @Test
    void testParseProgram() throws Exception {
        List<String> strings = FileParser.readStringListFile("day19test.txt");
        Processor processor = new Processor();
        processor.parseProgram(strings);
        assertThat(processor.getProgram()).hasSize(7);
        assertThat(processor.getIpRegister()).isEqualTo(0);

    }

    @Test
    void testRunWithIpRegister() throws Exception {
        List<String> strings = FileParser.readStringListFile("day19test.txt");
        Processor processor = new Processor(6);
        processor.parseProgram(strings);
        processor.run(-1, 0);
        assertThat(processor.getReg()[0]).isEqualTo(7);
    }

    @Test
    void testRunWithRealInput() throws Exception {
        List<String> strings = FileParser.readStringListFile("day19.txt");
        Processor processor = new Processor(6);
        processor.parseProgram(strings);
        processor.run(true);
        assertThat(processor.getReg()[0]).isEqualTo(1728);
    }


    @Test
    void testGetSumOfDividers() throws Exception {
        assertThat(Processor.getSumOfDividers(994)).isEqualTo(1728);
    }

    @Test
    void testDay21() throws Exception {
        List<String> strings = FileParser.readStringListFile("day21.txt");
        Processor processor = new Processor(6);
        processor.parseProgram(strings);
        int maxCount = Integer.MAX_VALUE;
        int maxAtReg0 = -1;
        for (int i = 0; i < 1 ; i++) {
            processor.reset();
            processor.getReg()[0]=i;
            processor.run(-1,1000000000000l);
            if (processor.getReg()[5]<maxCount) {
                maxCount = processor.getReg()[5];
            }
        }
        assertThat(maxCount).isEqualTo(0);
    }
}