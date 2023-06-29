package com.codecool.marsexploration.model.base;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.ArrayList;
import java.util.List;

public class Base {
    private static long count = 0;
    private final Long id;
    private Coordinate position;

    private Status status;

    private final List<TileType> resources;

    public Base(Status status, Coordinate position) {
        this.id = count;
        this.status = status;
        this.position = position;
        resources = new ArrayList<>();
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

    public void setStatus(Status status) {
        this.status = status;
    }
    public Rover buildRover() {
        return new Rover(3, this, position);
    }

    public List<TileType> getResources() {
        return resources;
    }

}
