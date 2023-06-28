package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class ClosestResourceCalculator {

    public Coordinate findClosestResource(Rover rover, TileType resourceNeededToBuildBase) {
        Coordinate currentPosition = rover.getCurrentPosition();
        int closestDistance = Integer.MAX_VALUE;
        Coordinate closestCoordinate = null;

        for (Map.Entry<Coordinate, TileType> entry : rover.getMemory().getMap().entrySet()) {
            if (entry.getValue() == resourceNeededToBuildBase) {
                int distance = calculateDistance(currentPosition, entry.getKey());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCoordinate = entry.getKey();
                }
            }
        }
        return closestCoordinate;
    }

    private int calculateDistance(Coordinate from, Coordinate to) {
        int dx = from.x() - to.x();
        int dy = from.y() - to.y();

        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}

