package com.codecool.marsexploration.model.base;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;

public class Base {
    private static long count = 0;
    private final Long id;
    private Coordinate position;

    private Status status;

    private List<TileType> resources;

    public Base(Status status, Coordinate position) {
        this.id = count;
        this.status = status;
        count++;
    }


    public Status getStatus() {
        return status;
    }

    public void addResources(TileType resource) {
        this.resources.add(resource);
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
