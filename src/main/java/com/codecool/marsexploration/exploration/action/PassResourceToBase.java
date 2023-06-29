package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.rovers.Rover;

public class PassResourceToBase implements Action {

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        Base base = rover.getBase();
        if (rover.isInventoryFull() && base != null) {
            rover.passResourcesToBase();
        }
    }
}
