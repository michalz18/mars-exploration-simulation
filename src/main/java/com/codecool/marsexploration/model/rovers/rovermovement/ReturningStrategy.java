package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.MapDisplay;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReturningStrategy implements MovementStrategy {
    private final Random random = new Random();
    private MapDisplay mapDisplay;
    private Map<Coordinate, TileType> commonMarsMap = new HashMap<>();


    @Override
    public Coordinate decideWhereToGo(Rover rover, SimulationContext simulationContext) {
        MarsMap memory = rover.getMemory();
        Coordinate basePosition = findPosition(memory.getMap(), TileType.SPACESHIP);

        for (Rover rover1 : simulationContext.getRovers()) {
            Map<Coordinate, TileType> memo = rover1.getMemory().getMap();

            commonMarsMap.putAll(memo);

        }
        commonMarsMap.put(rover.getCurrentPosition(), TileType.ROVER);

        simulationContext.setCommonMarsMap(commonMarsMap);

        MarsMap marsMap1 = new MarsMap(commonMarsMap, memory.getMapDimension());

        if (mapDisplay == null) {
            mapDisplay = MapDisplay.getInstance(marsMap1);
            mapDisplay.updateMap(marsMap1);

        }
        mapDisplay.closeOldMap();
        mapDisplay.updateMap(marsMap1);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        return findEmptySpot(memory, basePosition);
    }

    private Coordinate findEmptySpot(MarsMap memory, Coordinate basePosition) {
        Coordinate newCoordinate;
        do {

            newCoordinate = getNewRandomCoordinate(basePosition);
        } while (!fieldIsValid(memory.getMap(), newCoordinate));

        return newCoordinate;
    }

    private boolean fieldIsValid(Map<Coordinate, TileType> map, Coordinate newCoordinate) {
        TileType tileType = map.get(newCoordinate);
        return tileType != null && tileType.equals(TileType.EMPTY);
    }

    private Coordinate getNewRandomCoordinate(Coordinate basePosition) {
        int newX = basePosition.x() + random.nextInt(0, 2);
        int newY = basePosition.y() + random.nextInt(0, 2);

        return new Coordinate(newX, newY);
    }

    private Coordinate findPosition(Map<Coordinate, TileType> map, TileType tileType) {
        return map.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(tileType))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
