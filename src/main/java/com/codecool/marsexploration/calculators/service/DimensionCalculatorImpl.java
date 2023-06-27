package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator {

    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        return (int) (Math.ceil(Math.sqrt(size)) + dimensionGrowth);
    }
}
