package com.example.myapplication.database.graph_map;

public class Edge {
    private int srcId;  // Change the type to int
    private int dstId;  // Change the type to int
    private int weight;

    public int getSrcId() {
        return srcId;
    }

    public int getDstId() {
        return dstId;
    }

    public int getWeight() {
        return weight;
    }

    public Edge(int srcId, int dstId, int weight) {
        this.srcId = srcId;
        this.dstId = dstId;
        this.weight = weight;
    }

    public String toString() {
        return "Edge{" +
                "source=" + srcId +
                ", destination=" + dstId +
                ", weight=" + weight +
                '}' + "\n";
    }
}
