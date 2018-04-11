package com.anime;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private final int index;
    private final String name;
    private final int height;
    private final int weight;

    public Pokemon(int index, String name, int height, int weight) {
        this.index = index;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return String.format("%d, %s, %d, %d", index, name, height, weight);
    }
}
