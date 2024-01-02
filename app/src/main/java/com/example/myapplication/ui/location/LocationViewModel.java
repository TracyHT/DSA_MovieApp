package com.example.myapplication.ui.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.database.graph_map.GraphDatabase;
import com.example.myapplication.database.graph_map.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LocationViewModel extends ViewModel {

    private static final int START_NODE_ID = 1;

    private final GraphDatabase graphDatabase;
    private final MutableLiveData<List<Node>> locationListLiveData;
    private final MutableLiveData<Map<Integer, Integer>> shortestDistancesLiveData;
    private final MutableLiveData<List<Node>> routeLiveData;

    public LocationViewModel() {
        graphDatabase = GraphDatabase.getInstance();

        locationListLiveData = new MutableLiveData<>();
        shortestDistancesLiveData = new MutableLiveData<>();
        routeLiveData = graphDatabase.getRouteLiveData();

        // Load initial data
        loadInitialData();
    }

    private void loadInitialData() {
        List<Node> initialData = new ArrayList<>(graphDatabase.getNodes().values());
        locationListLiveData.setValue(initialData);

        graphDatabase.dijkstra(START_NODE_ID);
    }

    public LiveData<List<Node>> getLocationListLiveData() {
        return locationListLiveData;
    }

    public LiveData<Map<Integer, Integer>> getShortestDistancesLiveData() {
        return shortestDistancesLiveData;
    }

    public LiveData<List<Node>> getRouteLiveData() {
        return routeLiveData;
    }

    public void setSelectedCinema(String cinemaName) {
        // Get all nodes containing the cinemaName
        List<Node> facilities = graphDatabase.getNodesByLabelContains(cinemaName);

        Set<Integer> cinemaNodeIds = facilities.stream().map(Node::getId).collect(Collectors.toSet());

        Map<Integer, Integer> cachedDistances = graphDatabase.getDistancesFromSource();
        Map<Integer, Integer> shortestDistances = sortNodesByShortestDistance(facilities, cachedDistances);

        shortestDistancesLiveData.setValue(shortestDistances);

        // Get all nodes and filter based on facilities
        List<Node> filteredNodes = graphDatabase.getNodes().values().stream()
                .filter(node -> facilities.contains(node))
                .sorted(Comparator.comparingInt(node -> shortestDistances.getOrDefault(node.getId(), 0)))
                .collect(Collectors.toList());

        // Notify observers with the filtered nodes
        locationListLiveData.setValue(filteredNodes);
    }

    private Map<Integer, Integer> sortNodesByShortestDistance(List<Node> nodes, Map<Integer, Integer> distances) {
        return distances.entrySet().stream()
                .filter(entry -> nodes.contains(graphDatabase.getNodes().get(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
