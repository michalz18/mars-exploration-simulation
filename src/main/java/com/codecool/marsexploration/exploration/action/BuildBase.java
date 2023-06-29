package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

public class BuildBase implements Action {
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        rover.getBase().setStatus(Status.BUILD);
        rover.emptyInventory();

        simulationContext.getMarsMap().setTileType(rover.getBase().getPosition(), TileType.BASE);
    }
}
