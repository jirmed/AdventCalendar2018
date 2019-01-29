/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day4.LogItem;

import java.io.IOException;
import java.util.List;

/**
 * @author jiri21
 */
public class Application {

    public static void main(String[] args) throws IOException {
        day1task1();
        day1task2();
        day2task1();
        day2task2();
        day3();
        day4();

    }

    private static void day4() {
        System.out.println("Day 4");
        List<LogItem> logItems = null;
        try {
            logItems = FileParser.readLogItemListFile(".//Resources//day4.txt");
        } catch (IllegalAccessException e) {
            System.out.println("Invalid input");
            return;
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
        logItems.sort(LogItem::compareTo);
        System.out.println(logItems);

    }

    private static void day3() throws IOException {
        Fabric fabric = new Fabric(1000, 1000);
        List<Rectangle> rectangles = FileParser.readRectangleListFile(".//Resources//day3task1.txt");
        fabric.cutRectangles(rectangles);
        System.out.println("Day 3 Task 1");
        System.out.println(fabric.getDuplicateTiles().size());
        System.out.println("Day 3 Task 2");
        System.out.println(fabric.getWholeRectangles());
    }


    private static void day2task1() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 1");

        System.out.println(device.calculateChecksum(FileParser.readStringListFile(".//Resources//day2task1.txt")));
    }

    private static void day2task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 2");

        System.out.println(device.findSimilar(FileParser.readStringListFile(".//Resources//day2task1.txt")));
    }


    private static void day1task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 1 Task 2");
        try {
            System.out.println(device.calibrate(FileParser.readIntListFile(".//Resources//day1task2.txt")));
        } catch (CannotCalibrateException e) {
            System.out.println("Cannot calibrate");
        }
    }

    private static void day1task1() throws IOException, NumberFormatException {
        Device device = new Device();
        System.out.println("Day 1 Task 1");
//        List<Integer> intList = readFile(".//Resources//day1task1.txt");
        for (int line : FileParser.readIntListFile(".//Resources//day1task1.txt")) {
            device.adjust(line);
        }
        System.out.println(device);
    }


}
