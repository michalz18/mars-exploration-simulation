package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

public class PickUpResource implements Action {
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        rover.setInventory(TileType.MINERAL);

        simulationContext.getMarsMap().setTileType(rover.getCurrentPosition(), TileType.EMPTY);

        rover.setCurrentActivityAssigned(null);
    }
}
