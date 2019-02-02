/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day1.CannotCalibrateException;
import net.konzult.adventcalendar2018.day1.Device;
import net.konzult.adventcalendar2018.day2.Box;
import net.konzult.adventcalendar2018.day3.Fabric;
import net.konzult.adventcalendar2018.day3.Rectangle;
import net.konzult.adventcalendar2018.day4.*;
import net.konzult.adventcalendar2018.day5.Polymer;
import net.konzult.adventcalendar2018.day6.Coordinate;
import net.konzult.adventcalendar2018.day6.World;
import net.konzult.adventcalendar2018.day7.AssemblyGuide;
import net.konzult.adventcalendar2018.day7.StepOrder;

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
//        day4();
//        day5();
//        day6();
        day7();
    }

    private static void day7() {
        System.out.println("day7");
        AssemblyGuide assemblyGuide = null;
        List<StepOrder> stepOrderList = null;
        try {
            stepOrderList = StepOrder.readStepOrderListFile("day7.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day7.txt");
        }
        System.out.println("Task 1");
        assemblyGuide = new AssemblyGuide(stepOrderList);
        assemblyGuide.init();
        System.out.println(assemblyGuide.calculateSimpleStepSequence());

        System.out.println("Task 2");
        assemblyGuide = new AssemblyGuide(stepOrderList);
        assemblyGuide.setMaxElves(5);
        assemblyGuide.setBaseTime(60);
        assemblyGuide.init();
        assemblyGuide.work();

        System.out.println(assemblyGuide.getStepSequence());
        System.out.println(assemblyGuide.getMinute());


    }

    private static void day6() {
        System.out.println("Day 6");
        World world = null;
        try {
            world = new World(Coordinate.readCoordinateListFile("day6.txt"));
        } catch (IOException e) {
            System.out.println("Cannot read file day6.txt");
        }
        System.out.println("Task 1");
        System.out.println(world.getMaxAreaCoordinate().getArea());
        System.out.println("Task 2");
        System.out.println(world.getAreaWithinTotalDistance(10000));
    }

    private static void day5() {
        System.out.println("Day 5");
        Polymer polymer = null;
        String formula = "";
        try {
            formula = FileParser.readStringFile("day5.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day5.txt");
        }

        System.out.println("Task 1");
        polymer = new Polymer(formula);
        polymer.reduce();
        System.out.println(polymer.getFormula().length());

        System.out.println("Task 2");
        polymer = new Polymer(formula);
        polymer.maxReduceByRemovingAnyUnit();
        System.out.println(polymer.getFormula().length());

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
        System.out.println(maxSleepGuard.getId() * maxSleepGuard.getMaxSleepCount().getMinute());


        System.out.println("Task 2");
        maxSleepGuard = logAnalyzer.getMaxSleepGuardByMaxMinute();
        System.out.println(maxSleepGuard);
        System.out.println(maxSleepGuard.getMaxSleepCount());
        System.out.println(maxSleepGuard.getId() * maxSleepGuard.getMaxSleepCount().getMinute());

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

        System.out.println(Box.calculateChecksum(FileParser.readStringListFile("day2.txt")));
    }

    private static void day2task2() throws IOException {
        Device device = new Device();
        System.out.println("Day 2 Task 2");

        System.out.println(Box.findSimilar(FileParser.readStringListFile("day2.txt")));
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
