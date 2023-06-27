package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public interface MessageGenerator {
    String generateLogEntry(int roverId,
                            Coordinate coordinate,
                            int amountOfStep,
                            Map<Coordinate, TileType> resourcesFindSoFar,
                            MovementStrategyType movementStrategy);

    String generateExplorationConfigurationErrorEntry();

    String generateExplorationFailToLandEntry();

    String generateMapFailedToLoadEntry();

    String generateLogForExplorationOutcome(ExplorationOutcome explorationOutcome);
}
