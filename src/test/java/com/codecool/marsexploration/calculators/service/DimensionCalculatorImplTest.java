package com.codecool.marsexploration.calculators.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DimensionCalculatorImplTest {
    private DimensionCalculatorImpl dimensionCalculator;

    @BeforeEach
    void init() {
        dimensionCalculator = new DimensionCalculatorImpl();
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void should_CalculateDimensions(int expected, int size, int dimensionGrowth) {
        int actual = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(0,-1,-1),
                Arguments.of(1,1,0),
                Arguments.of(2,1,1),
                Arguments.of(8,20,3),
                Arguments.of(4,16,0),
                Arguments.of(5,9,2),
                Arguments.of(6,10,2)
        );
    }
}