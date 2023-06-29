package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.*;
import java.util.stream.Collectors;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private final Random random;

    public CoordinateCalculatorImpl() {
        this.random = new Random();
    }

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        int x = random.nextInt(dimension);
        int y = random.nextInt(dimension);
        return new Coordinate(x, y);
    }

    @Override
    public Optional<Coordinate> getRandomCoordinate(List<Coordinate> coordinates) {
        if (coordinates.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(coordinates.get(new Random().nextInt(coordinates.size())));
        }
    }

    @Override
    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> adjacentCoordinates = List.of(
                coordinate.getUp(), coordinate.getDown(), coordinate.getRight(), coordinate.getLeft());
        return adjacentCoordinates.stream()
                .filter(newCoordinate -> checkIfCoordinateFitsDimension(dimension, newCoordinate))
                .toList();
    }

    @Override
    public List<Coordinate> getAdjacentCoordinates(List<Coordinate> coordinates, int dimension) {
        return coordinates.stream()
                .flatMap(coordinate -> getAdjacentCoordinates(coordinate, dimension).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Coordinate> getAllPossibleCoordinates(int dimension) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                coordinates.add(new Coordinate(row, column));
            }
        }
        return coordinates;
    }

    private boolean checkIfCoordinateFitsDimension(int dimension, Coordinate newCoordinate) {
        int x = newCoordinate.x();
        int y = newCoordinate.y();
        return x >= 0 && x < dimension && y >= 0 && y < dimension;
    }

    @Override
    public List<Coordinate> getCoordinatesInRadius(Coordinate coordinate, MarsMap marsMap, int radius) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int x = coordinate.x() - radius;
        int y = coordinate.y() - radius;
        int side = radius * 2 + 1;
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                coordinates.add(new Coordinate(x + i, y + j));
            }
        }
        return marsMap.getCoordinatesThatAreOnTheMap(coordinates);
    }

    @Override
    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, MarsMap marsMap) {
        List<Coordinate> possibleCoordinates = List.of(coordinate.getUp(), coordinate.getDown(), coordinate.getLeft(), coordinate.getRight());
        return marsMap.getCoordinatesThatAreOnTheMap(possibleCoordinates);
    }

    @Override
    public List<Coordinate> getOnlyEmptyCoordinates(List<Coordinate> coordinates, MarsMap marsMap) {
        return coordinates.stream().filter(coordinate -> marsMap.getMap().get(coordinate).equals(TileType.EMPTY)).toList();
    }
}
