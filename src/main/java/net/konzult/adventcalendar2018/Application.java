/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.Day22.Cave;
import net.konzult.adventcalendar2018.day1.CannotCalibrateException;
import net.konzult.adventcalendar2018.day1.Device;
import net.konzult.adventcalendar2018.day10.Message;
import net.konzult.adventcalendar2018.day11.Battery;
import net.konzult.adventcalendar2018.day12.Garden;
import net.konzult.adventcalendar2018.day13.TrackChart;
import net.konzult.adventcalendar2018.day14.ChocolateChart;
import net.konzult.adventcalendar2018.day15.BattleField;
import net.konzult.adventcalendar2018.day16.Instruction;
import net.konzult.adventcalendar2018.day16.Processor;
import net.konzult.adventcalendar2018.day16.Tester;
import net.konzult.adventcalendar2018.day18.CollectionArea;
import net.konzult.adventcalendar2018.day2.Box;
import net.konzult.adventcalendar2018.day3.Fabric;
import net.konzult.adventcalendar2018.day3.Rectangle;
import net.konzult.adventcalendar2018.day4.Guard;
import net.konzult.adventcalendar2018.day4.LogAnalyzer;
import net.konzult.adventcalendar2018.day4.LogItem;
import net.konzult.adventcalendar2018.day5.Polymer;
import net.konzult.adventcalendar2018.day6.Coordinate;
import net.konzult.adventcalendar2018.day6.World;
import net.konzult.adventcalendar2018.day7.AssemblyGuide;
import net.konzult.adventcalendar2018.day7.StepOrder;
import net.konzult.adventcalendar2018.day8.License;
import net.konzult.adventcalendar2018.day9.MarbleGame;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
//        day7();
//        day8();
//        day9();
//        day10();
//        day11();
//        day12();
//        day13();
//        day14();
//        day15();
//        day16();
//        day18();
//        day19();
        day22();


    }

    private static void day22() {
        System.out.println("Day 22");
        System.out.println("Task 1");
        int depth = 5616;
        Cave cave = new Cave(10, 785, depth);
        System.out.println(cave.getTotalRiskLevel());

        System.out.println("Task 2");
        System.out.println(cave.getDistanceToTarget());
    }

    private static void day19() {
        System.out.println("Day 19");
        List<String> strings = getStrings("day19.txt");
        Processor processor = new Processor(6);
        processor.parseProgram(strings);
        processor.run(false);
        System.out.println("Task 1");
        System.out.println(processor.getReg()[0]);

        System.out.println("Task 2");
        processor.setReg(new int[]{1, 0, 0, 0, 0, 0});
        processor.run(true);
        System.out.println(processor.getReg()[0]);

    }

    private static void day18() {
        System.out.println("Day 18");
        List<String> strings = getStrings("day18.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(strings);
        System.out.println("Task1");
        area.playMinutes(10);
        System.out.println(area.getResourceValue());

        System.out.println("Task 2");
        area.parseMap(strings);
        area.playMinutes(1000000000);
        System.out.println(area.getResourceValue());

    }

    private static void day16() {
        System.out.println("Day 16");
        List<String> stringsPart1 = getStrings("day16_1.txt");
        Tester tester = Tester.parse(stringsPart1);
        System.out.println("Task 1");
        System.out.println(tester.countSamplesWithValidInstructions(3));

        System.out.println("Task 2");
        List<String> stringsPart2 = getStrings("day16_2.txt");
        Map<Integer, Instruction> instructionMap = tester.createInstructionMap();
        Processor processor = new Processor();
        processor.setInstructionMap(instructionMap);
        processor.parseProgramRaw(stringsPart2);
        processor.run(-1, 0);
        System.out.println(processor.getReg()[0]);
    }

    private static List<String> getStrings(String fileName) {
        List<String> strings = null;
        try {
            strings = FileParser.readStringListFile(fileName);
        } catch (IOException e) {
            System.out.println("Cannot read file " + fileName);
        }
        return strings;
    }

    private static void day15() {
        System.out.println("Day 15");
        List<String> strings = null;
        try {
            strings = FileParser.readStringListFile("day15.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day15.txt");
        }
        BattleField field;
        field = BattleField.parse(strings);
        int rounds;

        System.out.println("Task1");
        field = BattleField.parse(strings);
        rounds = field.play(10000);
        System.out.println(rounds * field.getTotalHitpoints());

        System.out.println("Task2");
        field = BattleField.parse(strings);
        field.setElfAttack(BattleField.getMinElfAttackToSurviveAll(strings));
        rounds = field.play(10000);
        System.out.println(rounds * field.getTotalHitpoints());

    }

    private static void day14() {
        System.out.println("Day 14");
        System.out.println("Task 1");
        System.out.println(new ChocolateChart().scoreTenAfter(990941));

        System.out.println("Task 2");
        System.out.println(new ChocolateChart().findRecipe("990941"));
    }

    private static void day13() {
        System.out.println("Day 13");
        List<String> strings = null;
        try {
            strings = FileParser.readStringListFile("day13.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day13.txt");
        }
        TrackChart trackChart = new TrackChart();
        trackChart.parseTrack(strings);

        System.out.println("Task 1");
        System.out.println(trackChart.tickUntilCrash());

        System.out.println("Task 2");
        System.out.println(trackChart.tickUntilLastRemains());


    }

    private static void day12() {
        System.out.println("Day 12");

        Garden garden = null;
        try {
            garden = Garden.readGardenFile("day12.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day12.txt");
        }

        System.out.println("Task 2");
        System.out.println(garden.playGenerations(20l).getCheckSum());

        try {
            garden = Garden.readGardenFile("day12.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day12.txt");
        }

        System.out.println("Task 2");
        System.out.println(garden.getCheckumAfterManyGenerations(50000000000l));
    }

    private static void day11() {
        System.out.println("Day 11");

        Battery battery = new Battery(300, 2866);
        battery.init();

        System.out.println("Task 1");
        System.out.println(battery.getMaxPowerGrid(3).toString());

        System.out.println("Task 2");
        System.out.println(battery.getMaxPowerGrid().toString());

    }

    private static void day10() {
        System.out.println("Day 10");
        Message message = null;
        try {
            message = Message.readMessageFile("day10.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day10.txt");
        }

        System.out.println("Task 1");

        List<String> lines = message.getSmallestMessage();
        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("Task 2");
        System.out.println(message.getMinute() - 1);

    }

    private static void day9() {
        System.out.println("Day 9");
        MarbleGame marbleGame;
        System.out.println("Task 1");
        marbleGame = new MarbleGame(465);
        marbleGame.play(71940);
        System.out.println(marbleGame.getMaxScore());
        System.out.println("Task 2");
        marbleGame = new MarbleGame(465);
        marbleGame.play(71940 * 100);
        System.out.println(marbleGame.getMaxScore());
    }

    private static void day8() {
        System.out.println("Day 8");
        License license = null;
        try {
            license = License.readLicenseFile("day8.txt");
        } catch (IOException e) {
            System.out.println("Cannot read file day8.txt");
        }
        System.out.println("Task 1");
        System.out.println(license.getCheckSum());
        System.out.println("Task 2");
        System.out.println(license.getRootNode().getValue());


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
