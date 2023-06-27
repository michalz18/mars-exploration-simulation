package com.codecool.marsexploration.outcome;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.analysers.Analyser;

import java.util.List;
import java.util.Optional;

public class OutcomeAnalyser {

    private final List<Analyser> analysers;

    public OutcomeAnalyser(List<Analyser> analysers) {
        this.analysers = analysers;
    }

    public Optional<ExplorationOutcome> findOutcomeIfPresent(Rover rover, SimulationContext context) {
        return analysers.stream()
                .map(analyser -> analyser.analise(rover, context))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }
}
