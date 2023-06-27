package com.codecool.marsexploration.model.map;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class MarsMapTest {
    private MarsMap marsMap;

    @BeforeEach
    public void setUp() {
        Map<Coordinate, TileType> map = new HashMap<>();
        map.put(new Coordinate(0, 0), TileType.EMPTY);
        map.put(new Coordinate(0, 1), TileType.MOUNTAIN);
        map.put(new Coordinate(1, 0), TileType.WATER);
        map.put(new Coordinate(1, 1), TileType.MOUNTAIN);
        marsMap = new MarsMap(map, 2);
    }

    @Test
    public void testGetMap() {
        // Act
        Map<Coordinate, TileType> map = marsMap.getMap();

        // Assert
        Assertions.assertEquals(4, map.size());
    }

    @Test
    public void testGetMapDimension() {
        // When
        int dimension = marsMap.getMapDimension();

        // Then
        Assertions.assertEquals(2, dimension);
    }

    @Test
    public void testGetTileType_ForThisCoordinateShouldReturnWater() {
        // Given
        Coordinate coordinate = new Coordinate(1, 0);

        // When
        TileType tileType = marsMap.getTileType(coordinate);

        // Then
        Assertions.assertEquals(TileType.WATER, tileType);
    }

    @Test
    public void testGetTileType_InvalidCoordinate() {
        // Given
        Coordinate coordinate = new Coordinate(2, 2);

        // When
        TileType tileType = marsMap.getTileType(coordinate);

        // Then
        Assertions.assertNull(tileType);
    }

    @Test
    public void testGetNumberOfResourcesInMap() {
        // Given
        TileType resource = TileType.MOUNTAIN;

        // When
        int count = marsMap.getNumberOfResourcesInMap(resource);

        // Then
        Assertions.assertEquals(2, count);
    }

    @Test
    public void testSetTileType() {
        // Given
        Coordinate coordinate = new Coordinate(0, 0);
        TileType tileType = TileType.ROVER;

        // When
        marsMap.setTileType(coordinate, tileType);
        TileType updatedTileType = marsMap.getTileType(coordinate);

        // Then
        Assertions.assertEquals(tileType, updatedTileType);
    }

    @Test
    public void testGetRandomCoordinate() {
        // When
        Coordinate coordinate = marsMap.getRandomCoordinate();

        // Then
        Assertions.assertNotNull(coordinate);
    }
}