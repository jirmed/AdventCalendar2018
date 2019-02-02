package net.konzult.adventcalendar2018.day8;

import net.konzult.adventcalendar2018.FileParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class License {
    private final int[] source;
    private final Node rootNode;
    private int checkSum = 0;
    private int index;

    public License(int[] source) {
        this.source = source;
        index = 0;
        rootNode = createNode();
    }

    private Node createNode() {
        Node node = new Node();
        int childCount = source[index++];
        int metaDataCount = source[index++];
        for (int i = 0; i < childCount; i++) {
            node.getChildren().add(createNode());
        }
        for (int i = 0; i < metaDataCount; i++) {
            int metadataValue = source[index++];
            checkSum += metadataValue;
            node.getMetadata().add(metadataValue);
        }
        return node;
    }

    public static License readLicenseFile(String fileName) throws IOException {
        String s = FileParser.readStringFile(fileName);
        String[] intStrings = s.split(" ");
        int[] source = new int[intStrings.length];
        for (int i = 0; i < intStrings.length; i++) {
            source[i] = Integer.parseInt(intStrings[i]);
        }
        return new License(source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Arrays.equals(source, license.source);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(source);
    }

    @Override
    public String toString() {
        return "License{" +
                "source=" + Arrays.toString(source) +
                '}';
    }

    public int[] getSource() {
        return source;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public static class Node {
        private List<Node> children = new ArrayList<>();
        private List<Integer> metadata = new ArrayList<>();

        public int getValue() {
            int result = 0;
            if (children.size() == 0)
                for (Integer metadataValue : metadata) {
                    result += metadataValue;
                }
            else
                for (Integer metadataValue : metadata) {
                    if (metadataValue <= children.size())
                        result += children.get(metadataValue - 1).getValue();
                }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(children, node.children) &&
                    Objects.equals(metadata, node.metadata);
        }

        @Override
        public int hashCode() {
            return Objects.hash(children, metadata);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "children=" + children +
                    ", metadata=" + metadata +
                    '}';
        }

        public List<Node> getChildren() {
            return children;
        }

        public List<Integer> getMetadata() {
            return metadata;
        }
    }
}
