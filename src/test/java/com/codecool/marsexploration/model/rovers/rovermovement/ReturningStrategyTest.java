package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class ReturningStrategyTest {
    private MarsMap marsMap;
    private ReturningStrategy returningStrategy;

    @BeforeEach
    void setUp() {
        Random random = new Random();
        returningStrategy = new ReturningStrategy();
        Map<Coordinate, TileType> map = new HashMap<>();
        map.put(new Coordinate(0, 0), TileType.SPACESHIP);
        map.put(new Coordinate(0, 1), TileType.ROVER);
        map.put(new Coordinate(0, 2), TileType.WATER);
        map.put(new Coordinate(1, 1), TileType.EMPTY);

        marsMap = new MarsMap(map, 3);
        returningStrategy = new ReturningStrategy();

    }
//
//    @Test
//    public void decideWhereToGo() {
//        Coordinate coordinate = returningStrategy.decideWhereToGo();
//
//        Assertions.assertEquals(TileType.EMPTY, marsMap.getTileType(coordinate));
//        Assertions.assertNotNull(coordinate);
//        Assertions.assertTrue(marsMap.getMap().containsKey(coordinate));
//    }
}