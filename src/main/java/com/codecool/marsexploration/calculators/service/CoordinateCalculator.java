package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;

import java.util.List;

public interface CoordinateCalculator {
    Coordinate getRandomCoordinate(int dimension);
    List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
    List<Coordinate> getAdjacentCoordinates(List<Coordinate> coordinates, int dimension);

    List<Coordinate> getAllPossibleCoordinates(int dimension);

    List<Coordinate> getCoordinatesInRadius(Coordinate coordinate, MarsMap marsMap, int radius);

    List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, MarsMap marsMap);
}
