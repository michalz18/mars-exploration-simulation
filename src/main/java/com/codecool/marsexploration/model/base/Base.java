package com.codecool.marsexploration.model.base;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.tiletype.TileType;

import javax.swing.text.Position;
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

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }
}
