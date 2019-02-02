package net.konzult.adventcalendar2018;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {
    static final ClassLoader classLoader = FileParser.class.getClassLoader();

    public static List<Integer> readIntListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(getFile(fileName), "utf-8")
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> readStringListFile(String fileName) throws IOException {
        return FileUtils
                .readLines(getFile(fileName), "utf-8")
                .stream()
                .collect(Collectors.toList());
    }

    public static String readStringFile(String fileName) throws  IOException{
        return FileUtils
                .readFileToString(getFile(fileName), "utf-8");

    }

    private static File getFile(String fileName) {
        return new File(classLoader.getResource(fileName).getFile());
    }

}