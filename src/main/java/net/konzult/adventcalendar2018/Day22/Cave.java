package net.konzult.adventcalendar2018.Day22;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;

import javafx.util.Pair;

import static net.konzult.adventcalendar2018.Day22.Equipment.*;

public class Cave {

    private static final List<Pair<Integer, Equipment>> validEquipmentForRiskLevel =
            Arrays.asList(
                    new Pair<>(0, TORCH),
                    new Pair<>(0, GEAR),
                    new Pair<>(1, GEAR),
                    new Pair<>(1, NEITHER),
                    new Pair<>(2, TORCH),
                    new Pair<>(2, NEITHER
                    )
            );

    private static final Coordinates CAVE_MOUTH = Coordinates.ZERO;
    private static final int GEO_INDEX_Y_CONST = 48271;
    private static final int GEO_INDEX_X_CONST = 16807;
    private static final int EROSION_LEVEL_CONST = 20183;
    private static final int CHANGE_EQUIPMENT_TIME = 7;
    private static final int MOVE_TIME = 1;
    private final Coordinates target;
    private final int depth;

    private Graph graph;

    private Map<Coordinates, Integer> erosionLevelMap;

    public Cave(Coordinates target, int depth) {
        this.target = target;
        this.depth = depth;
    }


    public void init() {
        erosionLevelMap = new HashMap<>();
        for (int x = 0; x <= getMaxDimension(); x++) {
            for (int y = 0; y <= getMaxDimension(); y++) {
                Coordinates position = new Coordinates(x, y);
                int geoIndex;
                if (position.equals(target)) geoIndex = 0;
                else if (x == 0) geoIndex = y * GEO_INDEX_Y_CONST;
                else if (y == 0) geoIndex = x * GEO_INDEX_X_CONST;
                else geoIndex = erosionLevelMap.get(position.left())
                            * erosionLevelMap.get(position.up());
                erosionLevelMap.put(position,
                        (geoIndex + depth) % EROSION_LEVEL_CONST);
            }

        }
    }

    int getRiskLevel(Coordinates position) {
        if (erosionLevelMap.containsKey(position))
            return erosionLevelMap.get(position) % 3;
        else return -1;
    }

    public int getTotalRiskLevel(Coordinates position) {
        int result = 0;
        for (int x = CAVE_MOUTH.getX(); x <= position.getX(); x++) {
            for (int y = CAVE_MOUTH.getY(); y <= position.getY(); y++) {
                result += getRiskLevel(new Coordinates(x, y));
            }

        }
        return result;
    }

    private boolean canMoveTo(Coordinates newPosition, Equipment newEquipment) {
        return validEquipmentForRiskLevel.contains(new Pair<>(getRiskLevel(newPosition), newEquipment));
    }

    public int getShortestTimeToTarget() {
        int maxDimension = getMaxDimension();
        calculateGraph(maxDimension);
        return graph.getNode(new Node(target, TORCH)).getDistance();
    }

    private int getMaxDimension() {
//        return (target.getX() + target.getY()) * (CHANGE_EQUIPMENT_TIME + MOVE_TIME);
        return 1000;
    }

    private void calculateGraph(int maxDimension) {
        graph = new Graph();
        addAllValidNodes(maxDimension);
        addEdges();
        graph.calculateShortestPathFromSource(
                graph.getNode(new Node(CAVE_MOUTH, TORCH))
        );
    }

    private void addEdges() {
        for (Node node : graph.getNodes().keySet()) {
            for (Coordinates neighbour : Coordinates.ORTOGONAL_NEIGHBOURS) {
                for (Equipment equipment : Equipment.values()) {
                    Node neighbourNode = graph.getNode(new Node(node.getPosition().add(neighbour), equipment));
                    if (neighbourNode != null) {
                        node.adjacentNodes.put(neighbourNode,
                                (node.getEquipment().equals(equipment) ? MOVE_TIME : CHANGE_EQUIPMENT_TIME + MOVE_TIME));
                    }
                }
            }
        }
    }

    private void addAllValidNodes(int maxDimension) {
        for (int x = 0; x <= maxDimension; x++) {
            for (int y = 0; y <= maxDimension; y++) {
                for (Equipment equipment : Equipment.values()) {
                    Node node = new Node(new Coordinates(x, y), equipment);
                    if (canMoveTo(node.getPosition(), node.getEquipment()))
                        graph.addNode(node);
                }
            }
        }
    }
}
