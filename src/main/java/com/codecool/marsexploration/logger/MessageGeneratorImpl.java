package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.base.Status;
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
                                   String currentTask,
                                   Status baseStatus,
                                   TileType inventory,
                                   Map<Coordinate, TileType> resourcesFindSoFar,
                                   MovementStrategyType movementStrategy,
                                   ExplorationOutcome explorationOutcome) {
        String resourcesString = resourcesFindSoFar.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> entry.getKey() + " amount: " + entry.getValue())
                .collect(Collectors.joining(", "));

        return String.format("[%d] %s: Rover at (%d, %d), Steps: %d, Current task: %s, Base status: %s, Inventory: %s, Movement Strategy: %s, Outcome: %s",
                roverId, LocalDate.now(), coordinate.x(), coordinate.y(), amountOfStep, currentTask, baseStatus, inventory,
                movementStrategy, explorationOutcome);
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
    @Override
    public String generateMissionSuccessMessage(int bases) {
        return "Mission outcome: " + bases + " bases were successfully created";
    }

    @Override
    public String generateMarsIsUncolonizable() {
        return "Mission outcome: Mars is uncolonizable";
    }

    @Override
    public String generateTimeoutMessage() {
        return "Mission outcome: Timeout has been reached";
    }
}
