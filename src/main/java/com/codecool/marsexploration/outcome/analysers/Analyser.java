package com.codecool.marsexploration.outcome.analysers;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;

import java.util.Optional;

public interface Analyser {
    Optional<ExplorationOutcome> analise (Rover rover, SimulationContext context);
}
