package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.HashMap;
import java.util.Map;

public class CommonMarsMapBuilder {
    public Map<Coordinate, TileType> buildCommonMarsMap(Rover currentRover, SimulationContext simulationContext) {
        Map<Coordinate, TileType> commonMarsMap = new HashMap<>();

        for (Rover rover : simulationContext.getRovers()) {
            Map<Coordinate, TileType> memo = rover.getMemory().getMap();
            commonMarsMap.putAll(memo);
            if (rover.getId() != currentRover.getId()) {
                commonMarsMap.put(rover.getCurrentPosition(), TileType.ROVER);
            }
        }

        commonMarsMap.put(currentRover.getCurrentPosition(), TileType.ROVER);

        return commonMarsMap;
    }
}
