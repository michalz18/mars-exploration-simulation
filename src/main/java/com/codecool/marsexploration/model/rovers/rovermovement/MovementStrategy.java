package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

public interface MovementStrategy {

    Coordinate decideWhereToGo(Rover rover, SimulationContext simulationContext);
}
