package com.codecool.marsexploration.outcome.analysers;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Optional;
import java.util.Set;

public class ResourceAnalyser implements Analyser {

    @Override
    public Optional<ExplorationOutcome> analise (Rover rover, SimulationContext context) {
        Set<TileType> resourcesToLookFor = context.getResourcesToLookFor();
        boolean resourcesFound = resourcesToLookFor.stream()
                .map(rover.getMemory()::getNumberOfResourcesInMap)
                .allMatch(count -> count > 2);
        return resourcesFound ? Optional.of(ExplorationOutcome.RESOURCES_FOUND) : Optional.empty();
    }
}
