package com.example.myapplication.database.graph_map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {
    private Map<Integer, Node> nodes = new HashMap<>();
    private Map<Integer, Map<Integer, Integer>> adjacencyList = new HashMap<>();

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
        adjacencyList.put(node.getId(), new HashMap<>());
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
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((n1, n2) -> distances.get(n1) - distances.get(n2));

        for (int nodeId : adjacencyList.keySet()) {
            distances.put(nodeId, Integer.MAX_VALUE);
        }

        distances.put(startNodeId, 0);
        priorityQueue.add(startNodeId);

        while (!priorityQueue.isEmpty()) {
            int currentNodeId = priorityQueue.poll();
            if (visited.contains(currentNodeId)) {
                continue;
            }

            visited.add(currentNodeId);

            for (Map.Entry<Integer, Integer> neighbor : adjacencyList.get(currentNodeId).entrySet()) {
                int neighborNodeId = neighbor.getKey();
                int newDistance = distances.get(currentNodeId) + neighbor.getValue();

                if (newDistance < distances.get(neighborNodeId)) {
                    distances.put(neighborNodeId, newDistance);
                    priorityQueue.add(neighborNodeId);
                }
            }
        }

        return distances;
    }
}
