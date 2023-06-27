package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public interface Logger {

    void logg(int roverId,
              Coordinate coordinate,
              int amountOfStep,
              Map<Coordinate, TileType> resourcesFindSoFar,
              MovementStrategyType movementStrategy,
              ExplorationOutcome explorationOutcome);



    void loggExplorationConfigurationError();


    void loggExplorationFailToLand();

    void loggMapFailedToLoad();

    void loggExplorationOutcome(ExplorationOutcome explorationOutcome);
}
