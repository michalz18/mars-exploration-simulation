package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CoordinateCalculatorImplTest {
    @Test
    public void getRandomCoordinate_ShouldReturnCoordinateWithinDimension() {
        //given
        int dimension = 5;
        CoordinateCalculator calculator = new CoordinateCalculatorImpl();

        //when
        Coordinate randomCoordinate = calculator.getRandomCoordinate(dimension);

        //then
        Assertions.assertThat(randomCoordinate.x()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(randomCoordinate.x()).isLessThan(dimension);

        Assertions.assertThat(randomCoordinate.y()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(randomCoordinate.y()).isLessThan(dimension);
    }

    @Test
    public void getAdjacentCoordinates_ShouldReturnAdjacentCoordinatesForSingleCoordinate() {
        //given
        int dimension = 5;
        Coordinate coordinate = new Coordinate(2, 2);
        CoordinateCalculator calculator = new CoordinateCalculatorImpl();

        //when
        List<Coordinate> adjacentCoordinates = calculator.getAdjacentCoordinates(coordinate, dimension);

        //then
        List<Coordinate> expectedCoordinates = Arrays.asList(
                new Coordinate(1, 2), // left
                new Coordinate(3, 2), // right
                new Coordinate(2, 1), // up
                new Coordinate(2, 3)  // down
        );

        Assertions.assertThat(adjacentCoordinates).containsExactlyInAnyOrderElementsOf(expectedCoordinates);
    }

    @Test
    public void getAdjacentCoordinates_ShouldReturnAdjacentCoordinatesForListOfCoordinates() {
        //given
        int dimension = 5;
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(2, 2),
                new Coordinate(0, 0),
                new Coordinate(4, 4)
        );
        CoordinateCalculator calculator = new CoordinateCalculatorImpl();

        //when
        List<Coordinate> adjacentCoordinates = calculator.getAdjacentCoordinates(coordinates, dimension);

        //then
        List<Coordinate> expectedCoordinates = Arrays.asList(
                new Coordinate(1, 2), // left of (2, 2)
                new Coordinate(3, 2), // right of (2, 2)
                new Coordinate(2, 1), // up of (2, 2)
                new Coordinate(2, 3), // down of (2, 2)
                new Coordinate(0, 1), // right of (0, 0)
                new Coordinate(1, 0), // down of (0, 0)
                new Coordinate(3, 4), // left of (4, 4)
                new Coordinate(4, 3)  // up of (4, 4)
        );

        Assertions.assertThat(adjacentCoordinates).containsExactlyInAnyOrderElementsOf(expectedCoordinates);
    }

    @Test
    public void getAdjacentCoordinates_ShouldReturnAdjacentCoordinatesForCornerCoordinates() {
        // Given
        int dimension = 5;
        List<Coordinate> cornerCoordinates = Arrays.asList(
                new Coordinate(0, 0), // bottom-left corner
                new Coordinate(0, dimension - 1), // top-left corner
                new Coordinate(dimension - 1, 0), // bottom-right corner
                new Coordinate(dimension - 1, dimension - 1) // top-right corner
        );
        CoordinateCalculator calculator = new CoordinateCalculatorImpl();

        // When
        List<Coordinate> adjacentCoordinates = calculator.getAdjacentCoordinates(cornerCoordinates, dimension);

        // Then
        List<Coordinate> expectedCoordinates = Arrays.asList(
                new Coordinate(0, 1), // up of bottom-left corner
                new Coordinate(1, 0), // right of bottom-left corner
                new Coordinate(1, dimension - 1), // right of top-left corner
                new Coordinate(0, dimension - 2), // down of top-left corner
                new Coordinate(dimension - 2, dimension - 1), // left of top-right corner
                new Coordinate(dimension - 1, dimension - 2), // down of top-right corner
                new Coordinate(dimension - 1, 1), // up of bottom-right corner
                new Coordinate(dimension - 2, 0) // left of bottom-right corner
        );

        Assertions.assertThat(adjacentCoordinates).containsExactlyInAnyOrderElementsOf(expectedCoordinates);
    }

    @Test
    public void getPossibleCoordinates_ShouldReturnAllCoordinatesForGivenDimension() {
        // Given
        int dimension = 3;
        CoordinateCalculator calculator = new CoordinateCalculatorImpl();

        // When
        List<Coordinate> possibleCoordinates = calculator.getAllPossibleCoordinates(dimension);

        // Then
        List<Coordinate> expectedCoordinates = Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 2),
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(2, 2)
        );

        Assertions.assertThat(possibleCoordinates).containsExactlyInAnyOrderElementsOf(expectedCoordinates);
    }

}