package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day3.Rectangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileParserTest {

    @Test
    void parseRectangle() {
        assertThat(Rectangle.parseRectangle("#1 @ 509,796: 18x15"))
                .isEqualTo(new Rectangle(1, 509, 796, 18, 15));
    }
}