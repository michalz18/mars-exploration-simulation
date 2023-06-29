package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.CommonMarsMapBuilder;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovingToADestinationCoordinateTest {
    MovingToADestinationCoordinate movingToADestinationCoordinate;
    Rover rover;

    @BeforeEach
    void setUp() {
        rover = mock(Rover.class);
        when(rover.getCurrentPosition()).thenReturn(new Coordinate(1, 1));
        when(rover.getDestination()).thenReturn(new Coordinate(5, 5));
        movingToADestinationCoordinate = new MovingToADestinationCoordinate(new CommonMarsMapBuilder());
    }

    @Test
    public void decideWhereToGoShouldReturnNewCoordinate() {
        Coordinate expected = new Coordinate(2, 2);


        Coordinate actual = movingToADestinationCoordinate.decideWhereToGo(rover, mock(SimulationContext.class));


        assertEquals(expected, actual);
    }

    @Test
    public void decideWhereToGoShouldReturnCurrentPositionIfDestinationEqualsCurrent() {
        when(rover.getDestination()).thenReturn(new Coordinate(1, 1));
        Coordinate expected = new Coordinate(1, 1);


        Coordinate actual = movingToADestinationCoordinate.decideWhereToGo(rover, mock(SimulationContext.class));


        assertEquals(expected, actual);

    }
}