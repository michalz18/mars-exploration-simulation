package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;

import java.util.List;
import java.util.Optional;

public interface CoordinateCalculator {
    Coordinate getRandomCoordinate(int dimension);

    Optional<Coordinate> getRandomCoordinate(List<Coordinate> coordinates);

    List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
    List<Coordinate> getAdjacentCoordinates(List<Coordinate> coordinates, int dimension);

    List<Coordinate> getAllPossibleCoordinates(int dimension);

    List<Coordinate> getCoordinatesInRadius(Coordinate coordinate, MarsMap marsMap, int radius);

    List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, MarsMap marsMap);

    List<Coordinate> getOnlyEmptyCoordinates(List<Coordinate> adjacentCoordinates, MarsMap marsMap);
}
