package com.codecool.marsexploration.model.base;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
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
        this.position = position;
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

    public Long getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Rover buildRover() {
        return new Rover(3, this, position);
    }
}
