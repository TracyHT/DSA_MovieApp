package com.example.myapplication.database.graph_map;

// GraphDatabase.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphDatabase {

    private Map<Integer, Node> nodes;
    private List<Edge> edges;

    private static GraphDatabase instance;

    private GraphDatabase() {
        nodes = new HashMap<>();
        edges = new ArrayList<>();
        // Manually import data
        initializeData();
    }

    public static synchronized GraphDatabase getInstance() {
        if (instance == null) {
            instance = new GraphDatabase();
        }
        return instance;
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void initializeData() {
        // Manually add nodes
        nodes.put(1,new Node(1, "My House", 50, 50));
        nodes.put(2,new Node(2, "My Company", 100, 200));
        nodes.put(3, new Node(3,"Theater A", 300, 100));
        nodes.put(4, new Node(4,"Theater B", 300, 150));

        // Manually add edges
        edges.add(new Edge(1, 3, 5));  // Replace 5 with actual distance
        edges.add(new Edge(1, 4, 12));
        edges.add(new Edge(2, 3, 19));
        edges.add(new Edge(2, 4, 4));
    }
    // Dijkstra's algorithm
    public Map<Integer, Integer> dijkstra(int startNodeId) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistancePair> minHeap = new PriorityQueue<>();

        // Initialize distances with infinity and the start node with distance 0
        for (int nodeId : nodes.keySet()) {
            distances.put(nodeId, Integer.MAX_VALUE);
        }
        distances.put(startNodeId, 0);

        // Add the start node to the priority queue
        minHeap.add(new NodeDistancePair(startNodeId, 0));

        // Dijkstra's algorithm
        while (!minHeap.isEmpty()) {
            NodeDistancePair current = minHeap.poll();
            int currentNodeId = current.getNodeId();
            int currentDistance = current.getDistance();

            if (currentDistance > distances.get(currentNodeId)) {
                // Skip outdated distance values
                continue;
            }

            // Relax edges
            for (Edge edge : edges) {
                if (edge.getSrcId() == currentNodeId) {
                    int neighborNodeId = edge.getDstId();
                    int newDistance = currentDistance + edge.getWeight();

                    if (newDistance < distances.get(neighborNodeId)) {
                        distances.put(neighborNodeId, newDistance);
                        minHeap.add(new NodeDistancePair(neighborNodeId, newDistance));
                    }
                }
            }
        }

        return distances;
    }
    // Helper class to represent a pair of node and its distance
    private static class NodeDistancePair implements Comparable<NodeDistancePair> {
        private final int nodeId;
        private final int distance;

        public NodeDistancePair(int nodeId, int distance) {
            this.nodeId = nodeId;
            this.distance = distance;
        }

        public int getNodeId() {
            return nodeId;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(NodeDistancePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    public int getNodeIdByName(String nodeName) {
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            if (entry.getValue().getLabel().equals(nodeName)) {
                return entry.getKey();
            }
        }
        return -1;  // Node with the given name not found
    }
    // Method to get the shortest path
    public List<Integer> getShortestPath(int startNodeId, int endNodeId) {
        // Run Dijkstra's algorithm to get distances
        Map<Integer, Integer> distances = dijkstra(startNodeId);

        // Check if the end node is reachable
        if (!distances.containsKey(endNodeId) || distances.get(endNodeId) == Integer.MAX_VALUE) {
            return Collections.emptyList(); // No path exists
        }

        // Reconstruct the shortest path
        List<Integer> shortestPath = new ArrayList<>();
        int currentNodeId = endNodeId;

        while (currentNodeId != startNodeId) {
            shortestPath.add(currentNodeId);
            int minDistance = Integer.MAX_VALUE;
            int nextNodeId = -1;

            // Find the predecessor with the minimum distance
            for (Edge edge : edges) {
                if (edge.getDstId() == currentNodeId) {
                    int predecessorNodeId = edge.getSrcId();
                    int distanceToPredecessor = distances.get(predecessorNodeId);

                    if (distanceToPredecessor < minDistance) {
                        minDistance = distanceToPredecessor;
                        nextNodeId = predecessorNodeId;
                    }
                }
            }

            currentNodeId = nextNodeId;
        }

        // Add the start node to the path
        shortestPath.add(startNodeId);

        // Reverse the list to get the correct order
        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
