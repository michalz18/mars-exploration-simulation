package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

public class AnaliseGatheringResources implements Action {

    private final TileType resourceToGatherInBase;

    public AnaliseGatheringResources(TileType resourceToGatherInBase) {
        this.resourceToGatherInBase = resourceToGatherInBase;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        //TODO
        // 1. nie mam resourceToGatherInBase i nie stoje na nim
//            Coordinate wantedCoordinate = find closest resource (resourceToGatherInBase)
//            setMovingStrategy to moving-to-a-destination-coordinate(wantedCoordinate)
//            ustaw zadanie rovera na null
        // 2. nie mam resourceToGatherInBase i stoje na nim
//            nadaj roverovi pamięć, że ma podnieść resorce na którym stoisz
        //3. mam resourceToGatherInBase i nie jestem "przy bazie"
        // setMovingStrategy to moving-to-a-destination-coordinate(status.base.coordinate)
//        ustaw zadanie rovera na null

//        4. mam resourceToGatherInBase i jestem "przy bazie"
                // nadaj roverovi zadanie oddania resourcu bazie

    }
}
