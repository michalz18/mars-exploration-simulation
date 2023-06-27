package com.codecool.marsexploration.exploration.model;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategy;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;

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


    public SimulationContext(int steps, int timeoutSteps, List<Rover> rovers, Base spaceship, MarsMap marsMap, Set<TileType> resourcesToLookFor, Map<MovementStrategyType, MovementStrategy> movementStrategies) {
        this.steps = steps;
        this.timeoutSteps = timeoutSteps;
        this.rovers = rovers;
        this.spaceship = spaceship;
        this.marsMap = marsMap;
        this.resourcesToLookFor = resourcesToLookFor;
        this.movementStrategies = movementStrategies;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
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


    public Base getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Base spaceship) {
        this.spaceship = spaceship;
    }

    public MarsMap getMarsMap() {
        return marsMap;
    }

    public void setMarsMap(MarsMap marsMap) {
        this.marsMap = marsMap;
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

    public int getExploredMapSize(Rover rover) {
        return rover.getExploredMapSize();
    }

    public void changeRoverStrategyToReturning(Rover rover) {
        rover.setCurrentMovementStrategyType(MovementStrategyType.RETURNING);
    }
}
