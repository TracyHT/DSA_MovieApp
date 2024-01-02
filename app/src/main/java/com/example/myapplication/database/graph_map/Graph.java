package com.example.myapplication.database.graph_map;
import java.util.*;

public class Graph {
    private Map<Integer, Node> nodes = new HashMap<>();
    private Map<Integer, Map<Integer, Integer>> adjacencyList = new HashMap<>();

    public void addNode(Node node) {
        int nodeId = node.getId();
        nodes.put(nodeId, node);
        adjacencyList.put(nodeId, new HashMap<>());
    }

    public List<Edge> getEdges() {
        List<Edge> edgeList = new ArrayList<>();

        for (Map.Entry<Integer, Map<Integer, Integer>> entry : adjacencyList.entrySet()) {
            int srcId = entry.getKey();

            for (Map.Entry<Integer, Integer> neighborEntry : entry.getValue().entrySet()) {
                int dstId = neighborEntry.getKey();
                int weight = neighborEntry.getValue();

                // Add the edge to the list
                edgeList.add(new Edge(srcId, dstId, weight));
            }
        }

        return edgeList;
    }

    public void addEdge(Edge edge) {
        int srcId = edge.getSrcId();
        int dstId = edge.getDstId();
        int weight = edge.getWeight();

        adjacencyList.get(srcId).put(dstId, weight);
        adjacencyList.get(dstId).put(srcId, weight);
    }

    public Map<Integer, Integer> dijkstra(int startNodeId) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistancePair> minHeap = new PriorityQueue<>();

        for (int nodeId : nodes.keySet()) {
            distances.put(nodeId, Integer.MAX_VALUE);
        }
        distances.put(startNodeId, 0);

        minHeap.add(new NodeDistancePair(startNodeId, 0));

        while (!minHeap.isEmpty()) {
            NodeDistancePair current = minHeap.poll();
            int currentNodeId = current.getNodeId();
            int currentDistance = current.getDistance();

            if (currentDistance > distances.get(currentNodeId)) {
                continue;
            }

            // Check if currentNodeId is present in adjacencyList
            for (Map.Entry<Integer, Integer> neighborEntry : adjacencyList.getOrDefault(currentNodeId, Collections.emptyMap()).entrySet()) {
                int neighborNodeId = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                int newDistance = currentDistance + weight;

                if (newDistance < distances.get(neighborNodeId)) {
                    distances.put(neighborNodeId, newDistance);
                    minHeap.add(new NodeDistancePair(neighborNodeId, newDistance));
                }
            }
        }

        return distances;
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

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
}
