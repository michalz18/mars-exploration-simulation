package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.CommonMarsMapBuilder;
import com.codecool.marsexploration.MapDisplay;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class MovingToADestinationCoordinate implements MovementStrategy {
    private MapDisplay mapDisplay;
    CommonMarsMapBuilder commonMarsMapBuilder;

    public MovingToADestinationCoordinate(CommonMarsMapBuilder commonMarsMapBuilder) {
        this.commonMarsMapBuilder = commonMarsMapBuilder;
    }

    @Override
    public Coordinate decideWhereToGo(Rover rover, SimulationContext simulationContext) {
        Coordinate destination = rover.getDestination();
        Coordinate currentPosition = rover.getCurrentPosition();


        Map<Coordinate, TileType> buildCommonMarsMap = commonMarsMapBuilder.buildCommonMarsMap(rover, simulationContext);
        simulationContext.setCommonMarsMap(buildCommonMarsMap);

        MarsMap marsMap1 = new MarsMap(buildCommonMarsMap, rover.getMemory().getMapDimension());

        if (mapDisplay == null) {
            mapDisplay = MapDisplay.getInstance(marsMap1);
            mapDisplay.updateMap(marsMap1);

        }
        mapDisplay.updateMap(marsMap1);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (destination.equals(currentPosition)) {
            return currentPosition;
        }

        return calculateNewPosition(destination, currentPosition);
    }


    private Coordinate calculateNewPosition(Coordinate destination, Coordinate currentPosition) {
        int currentX = currentPosition.x();
        int currentY = currentPosition.y();
        int destinationX = destination.x();
        int destinationY = destination.y();

        int newX = calculateCoordinate(currentX, destinationX);
        int newY = calculateCoordinate(currentY, destinationY);

        return new Coordinate(newX, newY);
    }

    private int calculateCoordinate(int current, int destination) {
        if (current < destination) {
            return current + 1;
        } else if (current > destination) {
            return current - 1;
        }
        return current;
    }


}
