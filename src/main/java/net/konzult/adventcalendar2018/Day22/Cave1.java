package net.konzult.adventcalendar2018.Day22;

import java.util.*;

public class Cave1 {

    private class Node {
        private final int x, y, equipment;
        private int distance;

        private Node(int x, int y, int equipment, int distance) {
            this.x = x;
            this.y = y;
            this.equipment = equipment;
            this.distance = distance;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        int getEquipment() {
            return equipment;
        }

        int getDistance() {
            return distance;
        }

        void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y &&
                    equipment == node.equipment;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, equipment);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", equipment=" + equipment +
                    ", distance=" + distance +
                    '}';
        }

    }

    private final static int MOVE_TIME = 1;
    private final static int CHANGE_TIME = 7;
    private static final int GEO_INDEX_Y_CONST = 48271;
    private static final int GEO_INDEX_X_CONST = 16807;
    private static final int EROSION_LEVEL_CONST = 20183;
    private static final int CHANGE_EQUIPMENT_TIME = 7;


    private final int targetX, targetY;
    private final int depth;
    private int[][] terrainMap;


    public Cave1(int targetX, int targetY, int depth) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.depth = depth;
        this.terrainMap = initTerrainMap(getMaxDistance());
    }

    private int getMaxDistance() {

        return (targetX + targetY) * (MOVE_TIME + CHANGE_TIME) /2 ;
    }

    private int[][] initTerrainMap(int maxDistance) {
        int[][] terrainMap = new int[maxDistance][maxDistance];
        int[][] erosionLevelMap = new int[maxDistance][maxDistance];
        for (int x = 0; x < maxDistance; x++) {
            for (int y = 0; y < maxDistance; y++) {
                int geoIndex;
                if (x == this.targetX && y == this.targetY) geoIndex = 0;
                else if (x == 0) geoIndex = y * GEO_INDEX_Y_CONST;
                else if (y == 0) geoIndex = x * GEO_INDEX_X_CONST;
                else geoIndex = erosionLevelMap[x - 1][y] * erosionLevelMap[x][y - 1];
                erosionLevelMap[x][y] =
                        (geoIndex + depth) % EROSION_LEVEL_CONST;
                terrainMap[x][y] = erosionLevelMap[x][y] % 3;
            }
        }
        return terrainMap;
    }

    public int getTotalRiskLevel() {
        int totalRiskLevel = 0;
        for (int x = 0; x <= targetX; x++) {
            for (int y = 0; y <= targetY; y++) {
                totalRiskLevel += terrainMap[x][y];
            }
        }
        return totalRiskLevel;
    }

    public int getDistanceToTarget() {
        int maxDistance = getMaxDistance();

        Map<Node, Node> unsettledNodes = new HashMap<>();
        Set<Node> settledNodes = new HashSet<>();
        PriorityQueue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));

        Node node = new Node(0, 0, 0, 0);
        unsettledNodes.put(node, node);
        nodes.add(node);
        while (!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            getadjacentNodes(currentNode, unsettledNodes, settledNodes, maxDistance, nodes);
            unsettledNodes.remove(currentNode);
            settledNodes.add(currentNode);
        }

        return getDistance(settledNodes, targetX, targetY, 0);
    }

    private int getDistance(Set<Node> nodes, int targetX, int targetY, int equipment) {
        return nodes.stream()
                .filter(node -> (node.getX() == targetX && node.getY() == targetY && node.getEquipment() == equipment))
                .mapToInt(Node::getDistance)
                .findFirst()
                .orElse(Integer.MAX_VALUE);
    }

    private void getadjacentNodes(Node node, Map<Node, Node> unsettledNodes, Set<Node> settledNodes, int maxDistance,
                                  Queue<Node> nodes) {
        // change equipment
        for (int equipment = 0; equipment < 3; equipment++) {
            if ((equipment != node.equipment)
                    && isValidEquipment(node.x, node.y, equipment)) {
                Node adjacentNode = new Node(node.x, node.y, equipment, node.distance + CHANGE_EQUIPMENT_TIME);
                addNode(unsettledNodes, settledNodes, nodes, adjacentNode);
            }
        }

        // move to adjacent node
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x == 0) ^ (y == 0)) {
                    int newX = node.getX() + x;
                    int newY = node.getY() + y;
                    if (newX < 0 || newY < 0
                            || newX >= maxDistance || newY >= maxDistance) continue;
                    if (isValidEquipment(newX, newY, node.equipment)) {
                        int distance = node.distance + MOVE_TIME;
                        if (Math.abs(newX - targetX) + Math.abs(newY - targetY) + distance > maxDistance) continue;
                        Node adjacentNode = new Node(newX, newY, node.equipment, distance);
                        addNode(unsettledNodes, settledNodes, nodes, adjacentNode);
                    }
                }
            }
        }
    }

    private void addNode(Map<Node, Node> unsettledNodes, Set<Node> settledNodes, Queue<Node> nodes, Node node) {
        if (settledNodes.contains(node)) return;
        Node originalNode = unsettledNodes.get(node);
        if (originalNode != null) {
            if (originalNode.distance > node.distance)
                originalNode.setDistance(node.distance);
        } else {
            nodes.add(node);
            unsettledNodes.put(node, node);
        }
    }

    private boolean isValidEquipment(int x, int y, int equipment) {
        if (x < 0 || y < 0 || x >= terrainMap.length || y >= terrainMap[x].length)
            return false;
        int terrain = terrainMap[x][y];
        return ((terrain != 0) || (equipment != 2))  //rock and neither
                && ((terrain != 1) || (equipment != 0)) //wet and torch
                && ((terrain != 2) || (equipment != 1)); // narrow and climbing gear
    }

}