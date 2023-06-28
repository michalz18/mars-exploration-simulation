package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClosestResourceCalculatorTest {
    private ClosestResourceCalculator closestResourceCalculator;
    private Rover rover;
    private HashMap<Coordinate, TileType> memoryMap;


    @BeforeEach
    void setUp() {
        closestResourceCalculator = new ClosestResourceCalculator();
        rover = new Rover(5, new Base(Status.SPACESHIP, new Coordinate(1, 1)), null);
        memoryMap = new HashMap<>();
    }

    @Test
    void testFindClosestResource() {
        Coordinate currentPosition = new Coordinate(0, 0);
        rover.setCurrentPosition(currentPosition);
        memoryMap.put(new Coordinate(2,2), TileType.MINERAL);
        memoryMap.put(new Coordinate(3,3), TileType.MINERAL);
        rover.setMemory(new MarsMap(memoryMap));

        Coordinate closestResource = closestResourceCalculator.findClosestResource(rover, TileType.MINERAL);

        assertEquals(new Coordinate(2,2), closestResource);
    }

    @Test
    void testFindClosestResourceWhenNoResourceFound() {
        Coordinate currentPosition = new Coordinate(0,0);
        rover.setCurrentPosition(currentPosition);
        memoryMap.put(new Coordinate(2,2), TileType.EMPTY);
        memoryMap.put(new Coordinate(3,3), TileType.EMPTY);

        Coordinate closestResource = closestResourceCalculator.findClosestResource(rover, TileType.MINERAL);

        assertNull(closestResource);
    }

}