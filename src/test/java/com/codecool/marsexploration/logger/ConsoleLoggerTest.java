package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleLoggerTest {
    private ConsoleLogger logger;
    private ByteArrayOutputStream outputStream;

//    @BeforeEach
//    void setUp() {
//        logger = new ConsoleLogger(messageGenerator);
//        outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//    }
//
//    @Test
//    public void testLogg() {
//        ConsoleLogger logger = new ConsoleLogger(messageGenerator);
//        Map<Coordinate, TileType> res = new HashMap<>();
//        res.put(new Coordinate(3, 4), TileType.MINERAL);
//        res.put(new Coordinate(5, 6), TileType.WATER);
//
//        String expected = "[1] " + LocalDate.now() + ": Rover at (1, 2), Steps: 2, Resources Found: WATER amount: 1, MINERAL amount: 1, Movement Strategy: EXPLORING, Outcome: UNDEFINED";
//
//        logger.logg(1, new Coordinate(1, 2), 2, res, MovementStrategyType.EXPLORING, ExplorationOutcome.UNDEFINED);
//
//        String actual = outputStream.toString().trim();
//
//        assertEquals(expected, actual);
//
//    }
}