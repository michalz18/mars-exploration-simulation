package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

public class AnaliseBaseBuild implements Action {

    private final Action baseBuilding;

    public AnaliseBaseBuild(Action baseBuilding) {
        this.baseBuilding = baseBuilding;
    }


    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        // TODO

        // setMovingStrategy to moving-to-a-destination-coordinate(my-base)
        // if (jestem na koordynacie baza)
        //  -> nadaj roverovi pamięć na currentActivityAssingn na budowanie bazy
        // else
        //  -> ustaw zadanie na null
    }
}
