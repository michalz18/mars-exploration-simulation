package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

public class AnaliseBaseResourceCollection implements Action {

    private final Action pickUpResource;

    private final TileType resourceNeededToBuildBase;

    public AnaliseBaseResourceCollection(Action pickUpResource, TileType resourceNeededToBuildBase) {
        this.pickUpResource = pickUpResource;
        this.resourceNeededToBuildBase = resourceNeededToBuildBase;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        //TODO
        // 1. nie mam resourceNeededToBuildBase i nie stoje na nim
//            Coordinate wantedCoordinate = find closest resource (resourceNeededToBuildBase)
//            setMovingStrategy to moving-to-a-destination-coordinate(wantedCoordinate)
//            ustaw zadanie rovera na null
        // 2. nie mam resourceNeededToBuildBase i stoje na nim
//            nadaj roverovi pamięć, że ma podnieść resorce na którym stoisz
    }
}
