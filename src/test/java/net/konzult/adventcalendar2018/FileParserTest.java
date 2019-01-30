package net.konzult.adventcalendar2018;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileParserTest {

    @Test
    void parseRectangle() {
        assertThat(FileParser.parseRectangle("#1 @ 509,796: 18x15"))
                .isEqualTo(new Rectangle(1, 509, 796, 18, 15));
    }
}