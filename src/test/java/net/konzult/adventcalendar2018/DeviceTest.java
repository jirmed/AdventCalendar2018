/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import net.konzult.adventcalendar2018.day1.CannotCalibrateException;
import net.konzult.adventcalendar2018.day1.Device;
import net.konzult.adventcalendar2018.day2.Box;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author jiri21
 */
public class DeviceTest {


    public DeviceTest() {
    }

    private static Stream adjustData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 1, 1), 3),
                Arguments.of(Arrays.asList(1, 1, -2), 0),
                Arguments.of(Arrays.asList(-1, -2, -3), -6)
        );
    }

    @ParameterizedTest(name = "{index} => freqs={0}, expected={1}")
    @MethodSource("adjustData")
    public void testAdjust(List<Integer> freqs, int expectedFreq) {

        Device device = new Device();
        for (Integer freq : freqs) {
            device.adjust(freq);
        }
        assertThat(device.getFrequency()).isEqualTo(expectedFreq);


    }

    private static Stream calibrateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, -1), 0),
                Arguments.of(Arrays.asList(1, 1, -2), 0),
                Arguments.of(Arrays.asList(1, 2, 3), null), //should faile
                Arguments.of(Arrays.asList(1, -2), 0), //on second loop
                Arguments.of(Arrays.asList(3, 3, 4, -2, -4), 10),
                Arguments.of(Arrays.asList(-6, 3, 8, 5, -6), 5),
                Arguments.of(Arrays.asList(7, 7, -2, -7, -4), 14)
        );
    }


    @ParameterizedTest(name = "{index} =>  freqs={0}, expected={1}  ")
    @MethodSource("calibrateData")
    public void testCalibrate(List<Integer> freqs, Integer expectedFreq) throws Exception {
        Device device = new Device();
        if (expectedFreq != null) {
            device.calibrate(freqs);
            assertThat(device.getFrequency()).isEqualTo(expectedFreq);
            assertThat(device.isCalibrated()).isTrue();
        } else {
            assertThatExceptionOfType(CannotCalibrateException.class).isThrownBy(() -> {
                device.calibrate(freqs);
            });
        }

    }

    private static Stream calculateChecksumData() {
        return Stream.of(
                Arguments.of(Arrays.asList(""), 0),
                Arguments.of(Arrays.asList("saaadbb"), 1),
                Arguments.of(Arrays.asList(
                        "abcdef",
                        "bababc",
                        "abbcde",
                        "abcccd",
                        "aabcdd",
                        "abcdee",
                        "ababab"
                ), 12)
        );
    }

    @ParameterizedTest(name = "{index} =>  ids={0}, expected={1}  ")
    @MethodSource("calculateChecksumData")
    public void testCalculateChecksum(List<String> ids, Integer expected) {
        Device device = new Device();
        assertThat(Box.calculateChecksum(ids)).isEqualTo(expected);
    }

    @Test
    public void testFindSimilar () {
        Device device = new Device();
        List<String> ids = Arrays.asList(
                "jsklds","abcde","fufl","abded","axcde","slkjfs"
        );
        assertThat(Box.findSimilar(ids)).isEqualTo("acde");


    }


}
