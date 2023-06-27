package com.codecool.marsexploration.model.map;

import com.codecool.marsexploration.tiletype.TileType;
import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.*;

public class MarsMap {

    private final Map<Coordinate, TileType> map;
    private int dimension;

    public MarsMap(Map<Coordinate, TileType> map, int dimension) {
        this.map = map;
        this.dimension = dimension;
    }

    public MarsMap(HashMap<Coordinate, TileType> map) {
        this.map = map;
    }

    public Map<Coordinate, TileType> getMap() {
        return map;
    }

    public int getMapDimension() {
        return dimension;
    }

    public TileType getTileType(Coordinate coordinate) {
        return map.get(coordinate);
    }

    public int getNumberOfResourcesInMap(TileType resource) {
        long count = map.values().stream()
                .filter(tileType -> tileType == resource)
                .count();
        return (int) count;
    }

    public void setTileType(Coordinate coordinate, TileType tileType) {
        map.put(coordinate, tileType);
    }

    public Coordinate getRandomCoordinate() {
        Set<Coordinate> coordinates = map.keySet();
        List<Coordinate> list = new ArrayList<>(coordinates);
        int randomIndex = new Random().nextInt(list.size());

        return list.get(randomIndex);
    }

    public int getSize() {
        return map.size();
    }

    public List<Coordinate> getCoordinatesThatAreOnTheMap(List<Coordinate> possibleCoordinates) {
        Set<Coordinate> coordinatesOnMap = map.keySet();
        return possibleCoordinates.stream().filter(coordinatesOnMap::contains).toList();
    }
}
