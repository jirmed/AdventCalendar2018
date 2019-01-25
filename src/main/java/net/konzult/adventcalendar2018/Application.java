/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

/**
 * @author jiri21
 */
public class Application {

    public static void main(String[] args) throws IOException {
//        day1task1();
//        day1task2();
        day2task1();
        day2task2();

    }

    private static void day2task1() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 2");

        System.out.println(device.calculateChecksum(readStringListFile(".//Resources//day2task1.txt")));
    }

    private static void day2task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 1");

        System.out.println(device.findSimilar(readStringListFile(".//Resources//day2task1.txt")));
    }


    private static void day1task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 1 Task 2");
        try {
            System.out.println(device.calibrate(readIntListFile(".//Resources//day1task2.txt")));
        } catch (CannotCalibrateException e) {
            System.out.println("Cannot calibrate");
        }
    }

    private static void day1task1() throws IOException, NumberFormatException {
        Device device = new Device();
        System.out.println("Day 1 Task 1");
//        List<Integer> intList = readFile(".//Resources//day1task1.txt");
        for (int line : readIntListFile(".//Resources//day1task1.txt")) {
            device.adjust(line);
        }
        System.out.println(device);
    }

    private static List<Integer> readIntListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(new File(fileName), "utf-8")
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    private static List<String> readStringListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(new File(fileName), "utf-8")
                .stream()
                .collect(Collectors.toList());
    }


}
