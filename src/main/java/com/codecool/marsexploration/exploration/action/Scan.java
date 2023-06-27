package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scan implements Action {
    private final CoordinateCalculator coordinateCalculator;

    public Scan(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        Map<Coordinate, TileType> partOfMapThatIsInRoverSight = getPartOfMapThatIsInRoverSight(rover, simulationContext.getMarsMap());
        saveNewPartOfMapInRoversMemory(rover, partOfMapThatIsInRoverSight);
    }

    private void saveNewPartOfMapInRoversMemory(Rover rover, Map<Coordinate, TileType> exploredMap) {
        Map<Coordinate, TileType> roverMemomoryMap = rover.getMemory().getMap();
        for (Coordinate coordinate : exploredMap.keySet()) {
            roverMemomoryMap.put(coordinate, exploredMap.get(coordinate));
        }
    }

    private Map<Coordinate, TileType> getPartOfMapThatIsInRoverSight(Rover rover, MarsMap marsMap) {
        List<Coordinate> coordinatesInRadius = coordinateCalculator.getCoordinatesInRadius(rover.getCurrentPosition(), marsMap, rover.getSight());
        HashMap<Coordinate, TileType> exploredMap = new HashMap<>();
        for (Coordinate coordinate : coordinatesInRadius) {
            exploredMap.put(coordinate, marsMap.getMap().get(coordinate));
        }
        return exploredMap;
    }

}
