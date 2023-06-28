package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.outcome.OutcomeAnalyser;

import java.util.Optional;

public class AnaliseExploration implements Action{

    private final OutcomeAnalyser outcomeAnalyser;

    public AnaliseExploration(OutcomeAnalyser outcomeAnalyser) {
        this.outcomeAnalyser = outcomeAnalyser;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        rover.setCurrentMovementStrategyType(MovementStrategyType.EXPLORING);
        Optional<ExplorationOutcome> outcome = outcomeAnalyser.findOutcomeIfPresent(rover, simulationContext);
        outcome.ifPresent(rover::setExplorationOutcome);
    }
}
