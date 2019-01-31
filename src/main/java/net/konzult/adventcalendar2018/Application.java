/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day4.Guard;
import net.konzult.adventcalendar2018.day4.LogAnalyzer;
import net.konzult.adventcalendar2018.day4.LogItem;

import java.io.IOException;
import java.util.List;

/**
 * @author jiri21
 */
public class Application {

    public static void main(String[] args) throws IOException {
//        day1task1();
//        day1task2();
//        day2task1();
//        day2task2();
//        day3();
        day4();

    }

    private static void day4() {
        System.out.println("Day 4");
        List<LogItem> logItems = null;
        try {
            logItems = LogItem.readLogItemListFile("day4.txt");
        } catch (IllegalAccessException e) {
            System.out.println("Invalid input");
            return;
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
        logItems.sort(LogItem::compareTo);
        LogAnalyzer logAnalyzer = new LogAnalyzer(logItems);
        logAnalyzer.process();

        Guard maxSleepGuard;

        System.out.println("Task 1");
        maxSleepGuard = logAnalyzer.getMaxSleepGuardByTotalTime();
        System.out.println(maxSleepGuard);
        System.out.println(maxSleepGuard.getMaxSleepCount());
        System.out.println(maxSleepGuard.getId()*maxSleepGuard.getMaxSleepCount().getMinute());


        System.out.println("Task 2");
        maxSleepGuard = logAnalyzer.getMaxSleepGuardByMaxMinute();
        System.out.println(maxSleepGuard);
        System.out.println(maxSleepGuard.getMaxSleepCount());
        System.out.println(maxSleepGuard.getId()*maxSleepGuard.getMaxSleepCount().getMinute());

    }

    private static void day3() throws IOException {
        Fabric fabric = new Fabric(1000, 1000);
        List<Rectangle> rectangles = Rectangle.readRectangleListFile("day3.txt");
        fabric.cutRectangles(rectangles);
        System.out.println("Day 3 Task 1");
        System.out.println(fabric.getDuplicateTiles().size());
        System.out.println("Day 3 Task 2");
        System.out.println(fabric.getWholeRectangles());
    }


    private static void day2task1() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 1");

        System.out.println(device.calculateChecksum(FileParser.readStringListFile("day2.txt")));
    }

    private static void day2task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 2");

        System.out.println(device.findSimilar(FileParser.readStringListFile("day2.txt")));
    }


    private static void day1task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 1 Task 2");
        try {
            System.out.println(device.calibrate(FileParser.readIntListFile("day1.txt")));
        } catch (CannotCalibrateException e) {
            System.out.println("Cannot calibrate");
        }
    }

    private static void day1task1() throws IOException, NumberFormatException {
        Device device = new Device();
        System.out.println("Day 1 Task 1");
        for (int line : FileParser.readIntListFile("day1.txt")) {
            device.adjust(line);
        }
        System.out.println(device);
    }


}
