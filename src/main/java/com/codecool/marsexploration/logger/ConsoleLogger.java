package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class ConsoleLogger implements Logger {
    private final MessageGenerator messageGenerator;

    public ConsoleLogger(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void logg(int roverId,
                     Coordinate coordinate,
                     int amountOfStep,
                     Map<Coordinate, TileType> resourcesFindSoFar,
                     MovementStrategyType movementStrategy,
                     ExplorationOutcome explorationOutcome
    ) {
        String logEntry = messageGenerator.generateLogEntry(
                roverId,
                coordinate,
                amountOfStep,
                resourcesFindSoFar,
                movementStrategy);
        System.out.println(logEntry);
    }

    @Override
    public void loggExplorationConfigurationError() {
        String errorEntry = messageGenerator.generateExplorationConfigurationErrorEntry();
        System.out.println(errorEntry);
    }

    @Override
    public void loggExplorationFailToLand() {
        String errorEntry = messageGenerator.generateExplorationFailToLandEntry();
        System.out.println(errorEntry);
    }

    @Override
    public void loggMapFailedToLoad() {
        String errorEntry = messageGenerator.generateMapFailedToLoadEntry();
        System.out.println(errorEntry);
    }

    @Override
    public void loggExplorationOutcome(ExplorationOutcome explorationOutcome) {
        String errorEntry = messageGenerator.generateLogForExplorationOutcome(explorationOutcome);
        System.out.println(errorEntry);
    }
}
