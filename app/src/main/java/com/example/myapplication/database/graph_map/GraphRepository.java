package com.example.myapplication.database.graph_map;

// GraphRepository.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.ArrayList;

public class GraphRepository {
    private MutableLiveData<List<Node>> allNodes;
    private MutableLiveData<List<Edge>> allEdges;

    public GraphRepository() {
        allNodes = new MutableLiveData<>();
        allEdges = new MutableLiveData<>();
        loadData();
    }

    private void loadData() {
        // Load data from GraphDatabase
        GraphDatabase database = GraphDatabase.getInstance();
        List<Node> nodes = new ArrayList<>(database.getNodes().values());
        List<Edge> edges = database.getEdges();

        allNodes.setValue(nodes);
        allEdges.setValue(edges);
    }

    public LiveData<List<Node>> getAllNodes() {
        return allNodes;
    }

    public LiveData<List<Edge>> getAllEdges() {
        return allEdges;
    }
}

