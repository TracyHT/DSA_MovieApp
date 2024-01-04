/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class represents a node in a graph, such as a location or point of interest, with attributes including a label, type, and coordinates (x, y). The class provides methods for getting and setting these attributes, including an additional method for specifying the node type.
*/
package com.example.myapplication.database.graph_map;

// Node.java
public class Node {

    private String label; // Use "label" instead of "nodeName"

    private int type;
    private double x; // Arbitrary X coordinate
    private double y; // Arbitrary Y coordinate

    private  int id;

    public Node(int id, String label, int type, double x, double y) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    // Getters and setters for label, x, and y
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type; //if type = 0, it is a movie theater location and 1 otherwise
    }
}

