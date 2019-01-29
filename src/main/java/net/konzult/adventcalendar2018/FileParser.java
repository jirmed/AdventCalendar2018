package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day4.LogItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {
    static List<Integer> readIntListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(new File(fileName), "utf-8")
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    static List<String> readStringListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(new File(fileName), "utf-8")
                .stream()
                .collect(Collectors.toList());
    }

    static List<Rectangle> readRectangleListFile(String fileName) throws IOException {
        List result = new ArrayList<>();
        List<String> strings = readStringListFile(fileName);
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            result.add(parseRectangle(s));
        }
        return result;
    }

    public static Rectangle parseRectangle(String stringToParse) {
        if (!stringToParse.matches("^#\\d+\\s+@\\s+\\d+,\\d+:\\s+\\d+x\\d+$"))
            throw new InvalidParameterException("Invalid format");
        String s = stringToParse.replaceAll("[#@:]", "");
        String[] parts = s.split("\\s+");
        String[] corner = parts[1].split(",");
        String[] size = parts[2].split("x");
        return new Rectangle(
                Integer.parseInt(parts[0]),
                Integer.parseInt(corner[0]),
                Integer.parseInt(corner[1]),
                Integer.parseInt(size[0]),
                Integer.parseInt(size[1]));
    }

    public static List<LogItem> readLogItemListFile(String fileName) throws IllegalAccessException, IOException {
        List result = new ArrayList<>();
        List<String> strings = readStringListFile(fileName);
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            result.add(LogItem.factory(s));
        }
        return result;

    }
}