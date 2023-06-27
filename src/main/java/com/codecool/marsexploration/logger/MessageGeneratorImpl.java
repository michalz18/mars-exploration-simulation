package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageGeneratorImpl implements MessageGenerator {

    @Override
    public String generateLogEntry(int roverId,
                                   Coordinate coordinate,
                                   int amountOfStep,
                                   Map<Coordinate, TileType> resourcesFindSoFar,
                                   MovementStrategyType movementStrategy) {
        String resourcesString = resourcesFindSoFar.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> entry.getKey() + " amount: " + entry.getValue())
                .collect(Collectors.joining(", "));

        return String.format("[%d] %s: Rover at (%d, %d), Steps: %d, Resources Found: %s, Movement Strategy: %s",
                roverId, LocalDate.now(), coordinate.x(), coordinate.y(), amountOfStep, resourcesString,
                movementStrategy);
    }

    @Override
    public String generateExplorationConfigurationErrorEntry() {
        return "[ERROR]: Exploration configuration error!";
    }

    @Override
    public String generateExplorationFailToLandEntry() {
        return "[ERROR]: Failed to land during exploration!";
    }

    @Override
    public String generateMapFailedToLoadEntry() {
        return "[ERROR]: Failed to load the map!";
    }

    @Override
    public String generateLogForExplorationOutcome(ExplorationOutcome explorationOutcome) {
        return "Mission outcome: "+ explorationOutcome.toString();
    }
}
