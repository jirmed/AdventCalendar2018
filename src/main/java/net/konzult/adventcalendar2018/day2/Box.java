package net.konzult.adventcalendar2018.day2;

import java.util.List;

public class Box {
    private String id;

    public Box(String id) {
        this.id = id;
    }

    public static String findSimilar(List<String> ids) {
        for (int i = 0; i < ids.size(); i++) {
            Box box = new Box(ids.get(i));
            String similarId = box.findFirstSimilarId(ids);
            if (similarId != null) {
                return box.findCommonPart(similarId);
            }
        }
        return null;
    }

    public static int calculateChecksum(List<String> ids) {
        int twos = 0;
        int threes = 0;

        for (String id : ids) {
            for (char c : id.toCharArray()) {
                if (id.chars().filter(ch -> ch == c).count() == 2) {
                    twos++;
                    break;
                }
            }
            for (char c : id.toCharArray()) {
                if (id.chars().filter(ch -> ch == c).count() == 3) {
                    threes++;
                    break;
                }
            }
        }
        return twos * threes;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String findFirstSimilarId(List<String> ids) {
        for (int i = 0; i < ids.size(); i++) {
            String currentId = ids.get(i);
            if (id.equals(currentId))
                continue;
            if (id.length() != currentId.length())
                continue;
            int counter = 0;
            for (int position = 0; position < id.length(); position++) {
                if (id.charAt(position) != currentId.charAt(position)) {
                    counter++;
                    if (counter > 1) break;
                }
            }
            if (counter == 1) return currentId;
        }
        return null;
    }

    public String findCommonPart(String idToCompare) {
        String result = "";
        if (id.length() == idToCompare.length()) {
            for (int position = 0; position < id.length(); position++) {
                if (id.charAt(position)==idToCompare.charAt(position)) {
                    result += id.charAt(position);
                }
            }
        }
        return result;
    }
}
