package net.konzult.adventcalendar2018.Day22;

import net.konzult.adventcalendar2018.day13.Coordinates;
import org.junit.jupiter.api.Test;

import static net.konzult.adventcalendar2018.Day22.Equipment.TORCH;
import static org.assertj.core.api.Assertions.*;

class GraphTest {

    @Test
    void testCalculateShortestPathFromSource() throws Exception {
        Graph graph = new Graph();
        Node nodeA = new Node(new Coordinates(1,0), TORCH);
        Node nodeB = new Node(new Coordinates(2,0), TORCH);
        Node nodeC = new Node(new Coordinates(3,0), TORCH);
        Node nodeD = new Node(new Coordinates(4,0), TORCH);
        Node nodeE = new Node(new Coordinates(5,0), TORCH);
        Node nodeF = new Node(new Coordinates(6,0), TORCH);

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph.calculateShortestPathFromSource( nodeA);

        assertThat(graph.getNode(nodeE).getDistance()).isEqualTo(24);
    }
}