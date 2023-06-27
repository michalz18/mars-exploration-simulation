package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

public class PlanBase implements Action{
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        // coordinate = find suitable coordinate for new base
        // build base with status PLANNED on that coordinate
            // save in rover.base
    }
}
