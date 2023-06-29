package com.codecool.marsexploration.configuration;

import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategy;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SimulationConfiguration {
    private final int timeoutSteps;
    private final String filePath;
    private final Set<TileType> resourcesToLookFor;
    private final Map<MovementStrategyType, MovementStrategy> movementStrategies;

    private final int allowedAttemptsToLand;

    private final int successfulBasesThreshold;


    public SimulationConfiguration(int timeoutSteps, String filePath, Set<TileType> resourcesToLookFor,
                                   Map<MovementStrategyType, MovementStrategy> movementStrategies, int allowedAttemptsToLand, int successfulBasesThreshold) {
        this.timeoutSteps = timeoutSteps;
        this.filePath = filePath;
        this.resourcesToLookFor = resourcesToLookFor;
        this.movementStrategies = movementStrategies;
        this.allowedAttemptsToLand = allowedAttemptsToLand;
        this.successfulBasesThreshold = successfulBasesThreshold;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SimulationConfiguration) obj;
        return this.timeoutSteps == that.timeoutSteps &&
                Objects.equals(this.filePath, that.filePath) &&
                Objects.equals(this.resourcesToLookFor, that.resourcesToLookFor) &&
                Objects.equals(this.movementStrategies, that.movementStrategies) &&
                this.allowedAttemptsToLand == that.allowedAttemptsToLand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeoutSteps, filePath, resourcesToLookFor, movementStrategies, allowedAttemptsToLand);
    }

    @Override
    public String toString() {
        return "SimulationConfiguration[" +
                "timeoutSteps=" + timeoutSteps + ", " +
                "filePath=" + filePath + ", " +
                "resourcesToLookFor=" + resourcesToLookFor + ", " +
                "movementStrategies=" + movementStrategies + ", " +
                "allowedAttemptsToLand=" + allowedAttemptsToLand + ']';
    }

    public int getTimeoutSteps() {
        return timeoutSteps;
    }

    public String getFilePath() {
        return filePath;
    }

    public Set<TileType> getResourcesToLookFor() {
        return resourcesToLookFor;
    }

    public Map<MovementStrategyType, MovementStrategy> getMovementStrategies() {
        return movementStrategies;
    }

    public int getAllowedAttemptsToLand() {
        return allowedAttemptsToLand;
    }

    public int getSuccessfulBasesThreshold() {
        return successfulBasesThreshold;
    }
}
