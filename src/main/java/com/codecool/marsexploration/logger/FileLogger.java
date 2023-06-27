package com.codecool.marsexploration.logger;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileLogger implements Logger {
    private final String filePath;
    private final MessageGenerator messageGenerator;

    public FileLogger(String filePath, MessageGenerator messageGenerator) {
        this.filePath = filePath;
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void logg(
            int roverId,
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
        writeToFile(logEntry);
    }


    private void writeToFile(String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(text);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loggExplorationConfigurationError() {
        String errorEntry = messageGenerator.generateExplorationConfigurationErrorEntry();
        writeToFile(errorEntry);
    }

    @Override
    public void loggExplorationFailToLand() {
        String errorEntry = messageGenerator.generateExplorationFailToLandEntry();
        writeToFile(errorEntry);
    }

    @Override
    public void loggMapFailedToLoad() {
        String errorEntry = messageGenerator.generateMapFailedToLoadEntry();
        writeToFile(errorEntry);
    }

    @Override
    public void loggExplorationOutcome(ExplorationOutcome explorationOutcome) {
        String message = messageGenerator.generateLogForExplorationOutcome(explorationOutcome);
        writeToFile(message);
    }
}
