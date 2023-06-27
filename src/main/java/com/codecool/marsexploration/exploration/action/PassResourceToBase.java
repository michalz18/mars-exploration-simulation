package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.model.rovers.Rover;

public class PassResourceToBase implements Action {

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        // TODO
        // usuń resource z inventory rovera i dodaj go do rover.base.resourcese
        // usuń zadanie z pamięci rovera
    }
}
