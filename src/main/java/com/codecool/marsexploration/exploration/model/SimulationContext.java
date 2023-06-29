package com.codecool.marsexploration.exploration.model;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategy;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimulationContext {
    private int steps;
    private final int timeoutSteps;
    private List<Rover> rovers;
    private Base spaceship;
    private MarsMap marsMap;
    private final Set<TileType> resourcesToLookFor;

    private final Map<MovementStrategyType, MovementStrategy> movementStrategies;
    private final int successfulBasesThreshold;
    Map<Coordinate, TileType> commonMarsMap = new HashMap<>();

    public void setCommonMarsMap(Map<Coordinate, TileType> commonMarsMap) {
        this.commonMarsMap = commonMarsMap;
    }

    public Map<Coordinate, TileType> getCommonMarsMap() {
        return commonMarsMap;
    }

    public SimulationContext(int steps, int timeoutSteps, List<Rover> rovers, Base spaceship, MarsMap marsMap, Set<TileType> resourcesToLookFor, Map<MovementStrategyType, MovementStrategy> movementStrategies, int successfulBasesThreshold) {
        this.steps = steps;
        this.timeoutSteps = timeoutSteps;
        this.rovers = rovers;
        this.spaceship = spaceship;
        this.marsMap = marsMap;
        this.resourcesToLookFor = resourcesToLookFor;
        this.movementStrategies = movementStrategies;
        this.successfulBasesThreshold = successfulBasesThreshold;
    }

    public int getSteps() {
        return steps;
    }

    public int getTimeoutSteps() {
        return timeoutSteps;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public void setRovers(List<Rover> rovers) {
        this.rovers = rovers;
    }
    public MarsMap getMarsMap() {
        return marsMap;
    }
    public Set<TileType> getResourcesToLookFor() {
        return resourcesToLookFor;
    }

    public Map<MovementStrategyType, MovementStrategy> getMovementStrategies() {
        return movementStrategies;
    }

    public void incrementSteps() {
        steps++;
    }

    public int getMarsMapSize() {
        return marsMap.getSize();
    }
    public void addRover(Rover rover) {
        rovers.add(rover);
    }

    public int getSuccessfulBasesThreshold() {
        return successfulBasesThreshold;
    }
}
