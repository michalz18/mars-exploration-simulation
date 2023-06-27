package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.Rover;

public class MovingToADestinationCoordinate implements MovementStrategy {

    @Override
    public Coordinate decideWhereToGo(Rover rover) {
        // TODO
        // zwraca koordynat, na który ma przejść rover, żeby być bliżej swojego rover.destination
        // jeżeli na nim stoi to zwraca ten na którym się znajduje
        return null;
    }
}
