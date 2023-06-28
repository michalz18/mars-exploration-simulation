package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

public class Do implements Action {
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        if (rover.getCurrentActivityAssigned() != null){
            rover.getCurrentActivityAssigned().takeAction(rover, simulationContext);
        }
    }
}
