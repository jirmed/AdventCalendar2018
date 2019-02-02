package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day2.Box;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoxTest {

    @Test
    void testFindFirstSimilarId() {
        Box box = new Box("abcde");
        List<String> ids = Arrays.asList(
                "jsklds","abded","axcde","slkjfs"
                );
        assertThat(box.findFirstSimilarId(ids)).isEqualTo("axcde");
    }

    @Test
    void testFindCommonPart() {
        Box box = new Box("abcde");
        assertThat(box.findCommonPart("axcde")).isEqualTo("acde");
    }
}