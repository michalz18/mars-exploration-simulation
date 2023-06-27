package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

public class PassResourceToBase implements Action {

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        // TODO
        // usuń resource z inventory rovera i dodaj go do rover.base.resourcese
        // usuń zadanie z pamięci rovera
        TileType inventory = rover.getInventory();
        Base base = rover.getBase();
        if (inventory != null && base != null) {
            base.addResources(inventory);
            rover.setInventory(null);
        }
    }
}
