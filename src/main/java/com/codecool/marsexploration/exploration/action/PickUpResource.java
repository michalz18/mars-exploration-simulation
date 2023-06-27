package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

public class PickUpResource implements Action {
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        // TODO
        //  podnieś minerał na którym stoisz
        // jeżeli Ci się uda, to usuń zadanie z pamięci (rover.currentActivityAssigned)
    }
}
