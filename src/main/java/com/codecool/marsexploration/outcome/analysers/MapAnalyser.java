package com.codecool.marsexploration.outcome.analysers;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;

import java.util.Optional;

public class MapAnalyser implements Analyser {

    private final double percentOfMapExploredBeforeGivingUp;

    public MapAnalyser(double percentOfMapExploredBeforeGivingUp) {
        this.percentOfMapExploredBeforeGivingUp = percentOfMapExploredBeforeGivingUp;
    }

    @Override
    public Optional<ExplorationOutcome> analise(Rover rover, SimulationContext context) {
        int mapSize = context.getMarsMapSize();
        int mapExplored = rover.getExploredMapSize();
        boolean isReadyToGiveUp = ((double) mapExplored / (double) mapSize) > percentOfMapExploredBeforeGivingUp;
        return isReadyToGiveUp ? Optional.of(ExplorationOutcome.LACK_OF_RESOURCES) : Optional.empty();
    }
}
