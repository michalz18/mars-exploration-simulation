package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategy;

public class Move implements Action {


    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        MovementStrategy movementStrategy = simulationContext.getMovementStrategies().get(rover.getCurrentMovementStrategyType());
        Coordinate newCoordinate = movementStrategy.decideWhereToGo(rover,simulationContext);
        rover.setCurrentPosition(newCoordinate);
        simulationContext.incrementSteps();
    }
}
