package com.codecool.marsexploration.configuration;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategy;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigurationValidator {
    public boolean validateConfiguration(SimulationConfiguration configuration) {
        return isTimeoutStepsValid(configuration.getTimeoutSteps()) &&
                areResourcesToLookForValid(configuration.getResourcesToLookFor()) &&
                isFilePathValid(configuration.getFilePath()) &&
                isAllowedAttemptsToLandValid(configuration.getAllowedAttemptsToLand()) &&
                areMovementStrategiesValid(configuration.getMovementStrategies());
    }

    private boolean isAllowedAttemptsToLandValid(int allowedAttemptsToLand) {
        return allowedAttemptsToLand > 0;
    }

    private boolean areMovementStrategiesValid(Map<MovementStrategyType, MovementStrategy> movementStrategies) {
        return movementStrategies != null && !movementStrategies.isEmpty();
    }

    private boolean isFilePathValid(String filePath) {
        return filePath != null && !filePath.isEmpty();
    }

    private boolean areResourcesToLookForValid(Set<TileType> resourcesToLookFor) {
        return resourcesToLookFor != null && !resourcesToLookFor.isEmpty();
    }

    private boolean isTimeoutStepsValid(int timeoutSteps) {
        return timeoutSteps > 0;
    }
}
