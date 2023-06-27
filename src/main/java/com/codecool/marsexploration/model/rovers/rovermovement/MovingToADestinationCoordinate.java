package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.Rover;

public class MovingToADestinationCoordinate implements MovementStrategy {

    @Override
    public Coordinate decideWhereToGo(Rover rover) {
        Coordinate destination = rover.getDestination();
        Coordinate currentPosition = rover.getCurrentPosition();
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
