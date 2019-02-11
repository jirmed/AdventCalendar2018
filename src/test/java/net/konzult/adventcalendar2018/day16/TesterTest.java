package net.konzult.adventcalendar2018.day16;

import net.konzult.adventcalendar2018.FileParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TesterTest {
    @Test
    void testParse() throws Exception {
        List<String> strings = FileParser.readStringListFile("Day16_1test.txt");
        Tester tester = Tester.parse(strings);

        assertThat(tester.getSamples()).hasSize(4);

    }
}