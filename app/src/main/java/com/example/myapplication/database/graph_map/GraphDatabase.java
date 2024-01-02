package com.example.myapplication.database.graph_map;

import androidx.lifecycle.MutableLiveData;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GraphDatabase {

    private Map<Integer, Node> nodes = new HashMap<>();
    private Map<Integer, Map<Integer, Integer>> adjacencyList = new HashMap<>();
    private Map<Integer, Integer> previousNodes = new HashMap<>();
    private MutableLiveData<List<Node>> routeLiveData = new MutableLiveData<>();
    private Map<Integer, Integer> distancesFromSource = new HashMap<>();
    private Map<Integer, List<Node>> shortestRoutes = new HashMap<>();

    private static GraphDatabase instance;

    private GraphDatabase() {
        initializeData();
        dijkstra(1);
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

    public Map<Integer, Map<Integer, Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public Map<Integer, Integer> getPreviousNodes() {
        return previousNodes;
    }

    public MutableLiveData<List<Node>> getRouteLiveData() {
        return routeLiveData;
    }

    public Map<Integer, Integer> getDistancesFromSource() {
        return distancesFromSource;
    }

    public Map<Integer, List<Node>> getShortestRoutes() {
        return shortestRoutes;
    }

    private void initializeData() {
        // Manually add nodes
        addNode(new Node(1, "My House", 1, 50, 50));
        addNode(new Node(2, "Location 1", 1, 100, 200));
        addNode(new Node(3, "Location 2", 1, 300, 100));
        addNode(new Node(4, "Cinema A - Vo Van Ngan", 0, 300, 100));
        addNode(new Node(5, "Cinema B - Dien Bien Phu", 0, 300, 150));
        addNode(new Node(6, "Tao Dan Park", 1, 0, 0));
        addNode(new Node(7, "ABC School", 1, 0, 0));
        addNode(new Node(8, "Z Sport Hall", 1, 0, 0));
        addNode(new Node(9, "Cinema C - Huynh Van Banh", 0, 0, 0));
        addNode(new Node(10, "Cinema B - Le Thanh Ton", 0, 0, 0));
        addNode(new Node(11, "Cinema C - Ham Nghi", 0, 0, 0));
        addNode(new Node(12, "Cinema D - Nguyen Trai", 0, 0, 0));
        addNode(new Node(13, "International University", 1, 0, 0));
        addNode(new Node(14, "Cinema D - Le Duan", 0, 0, 0));

        // Manually add edges
        addEdge(1, 2, 2);
        addEdge(1, 3, 1);
        addEdge(1, 5, 7);
        addEdge(2, 5, 1);
        addEdge(2, 4, 25);
        addEdge(2, 12, 16);
        addEdge(3, 1, 6);
        addEdge(6, 7, 40);
        addEdge(4, 9, 12);
        addEdge(1, 8, 20);
        addEdge(5, 11, 3);
        addEdge(1, 7, 50);
        addEdge(10, 6, 4);
        addEdge(12, 2, 10);
        addEdge(1, 4, 19);
        addEdge(2, 4, 4);
    }

    public void addNode(Node node) {
        int nodeId = node.getId();
        nodes.put(nodeId, node);
        adjacencyList.put(nodeId, new HashMap<>());
    }

    public void addEdge(int srcId, int dstId, int weight) {
        adjacencyList.get(srcId).put(dstId, weight);
        adjacencyList.get(dstId).put(srcId, weight);
    }

    public Map<Integer, Integer> dijkstra(int startNodeId) {
        if (!distancesFromSource.isEmpty()) {
            return distancesFromSource;
        }

        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistancePair> minHeap = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        initializeDijkstra(distances, startNodeId, minHeap);

        while (!minHeap.isEmpty()) {
            exploreNeighbors(minHeap, visited, distances, startNodeId);
        }

        notifyRouteChange(startNodeId);

        distancesFromSource = distances;
        return distances;
    }

    private void initializeDijkstra(Map<Integer, Integer> distances, int startNodeId, PriorityQueue<NodeDistancePair> minHeap) {
        for (int nodeId : nodes.keySet()) {
            distances.put(nodeId, Integer.MAX_VALUE);
            previousNodes.put(nodeId, Integer.MIN_VALUE);
        }
        distances.put(startNodeId, 0);
        minHeap.add(new NodeDistancePair(startNodeId, 0));
    }

    private void exploreNeighbors(PriorityQueue<NodeDistancePair> minHeap, Set<Integer> visited, Map<Integer, Integer> distances, int startNodeId) {
        int currentNodeId;  // Declare currentNodeId outside the loop

        while (!minHeap.isEmpty()) {
            NodeDistancePair current = minHeap.poll();
            currentNodeId = current.getNodeId();  // Initialize currentNodeId here
            int currentDistance = current.getDistance();

            visited.add(currentNodeId);

            for (Entry<Integer, Integer> neighborEntry : adjacencyList.getOrDefault(currentNodeId, Collections.emptyMap()).entrySet()) {
                int neighborNodeId = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                int newDistance = currentDistance + weight;

                updateDistanceAndRoute(minHeap, visited, distances, startNodeId, currentNodeId, neighborNodeId, newDistance);
            }
        }
    }

    private void updateDistanceAndRoute(PriorityQueue<NodeDistancePair> minHeap, Set<Integer> visited, Map<Integer, Integer> distances, int startNodeId, int currentNodeId, int neighborNodeId, int newDistance) {
        if (newDistance < distances.get(neighborNodeId) && !visited.contains(neighborNodeId)) {
            distances.put(neighborNodeId, newDistance);
            previousNodes.put(neighborNodeId, currentNodeId);
            minHeap.add(new NodeDistancePair(neighborNodeId, newDistance));
        }
    }

    private void notifyRouteChange(int startNodeId) {
        for (int nodeId : nodes.keySet()) {
            if (nodeId != startNodeId) {
                routeLiveData.postValue(shortestRoutes.get(nodeId));
            }
        }
    }

    public List<Node> getNodesByLabelContains(String searchString) {
        return nodes.values().stream()
                .filter(node -> node.getLabel().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
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
