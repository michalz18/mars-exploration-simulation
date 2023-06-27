package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

public class BuildBase implements Action {
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        // TODO
        // -> change rover.base.status to BUILD i wyrzuć resourceNeededToBuildBase z inventory
        // zapisz na mapie marsa że pod rover.base.coordinate jest BASE
    }
}
