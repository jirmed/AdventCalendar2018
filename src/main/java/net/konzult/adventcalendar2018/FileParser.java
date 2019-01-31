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
    static final ClassLoader classLoader = FileParser.class.getClassLoader();

    public static List<Integer> readIntListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(new File(classLoader.getResource(fileName).getFile()), "utf-8")
                .stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    public static List<String> readStringListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(new File(classLoader.getResource(fileName).getFile()), "utf-8")
                .stream()
                .collect(Collectors.toList());
    }

}