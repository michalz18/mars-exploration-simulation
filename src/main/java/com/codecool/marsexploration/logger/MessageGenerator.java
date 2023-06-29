package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public interface MessageGenerator {
    String generateLogEntry(int roverId,
                            Coordinate coordinate,
                            int amountOfStep,
                            String currentTask,
                            Status baseStatus,
                            TileType inventory,
                            Map<Coordinate, TileType> resourcesFindSoFar,
                            MovementStrategyType movementStrategy,
                            ExplorationOutcome explorationOutcome);

    String generateExplorationConfigurationErrorEntry();

    String generateExplorationFailToLandEntry();

    String generateMapFailedToLoadEntry();

    String generateLogForExplorationOutcome(ExplorationOutcome explorationOutcome);

    String generateMissionSuccessMessage(int bases);

    String generateMarsIsUncolonizable();

    String generateTimeoutMessage();
}
