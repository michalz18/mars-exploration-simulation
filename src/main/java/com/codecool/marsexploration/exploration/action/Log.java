package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class Log implements Action {
    private final Logger logger;

    public Log(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        int roverId = (int) rover.getId();
        Coordinate position = rover.getCurrentPosition();
        int currentSteps = simulationContext.getSteps();
        Map<Coordinate, TileType> resourcesFoundSoFar = rover.getMemory().getMap();
        MovementStrategyType strategy = rover.getCurrentMovementStrategyType();
        ExplorationOutcome outcome = rover.getExplorationOutcome();
        logger.logg(roverId, position, currentSteps, resourcesFoundSoFar, strategy, outcome);
    }
}
