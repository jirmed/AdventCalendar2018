package net.konzult.adventcalendar2018.Day22;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;

public class Node {

    private final Coordinates position;
    private final Equipment equipment;

//    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node( Coordinates position, Equipment equipment) {
        this.position = position;
        this.equipment = equipment;
    }

    public Coordinates getPosition() {
        return position;
    }

    public Equipment getEquipment() {
        return equipment;
    }

//    public List<Node> getShortestPath() {
//        return shortestPath;
//    }
//
//    public void setShortestPath(List<Node> shortestPath) {
//        this.shortestPath = shortestPath;
//    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return position.equals(node.position) &&
                equipment == node.equipment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, equipment);
    }

    @Override
    public String toString() {
        return "Node{" +
                "position=" + position +
                ", equipment=" + equipment +
                '}';
    }
}