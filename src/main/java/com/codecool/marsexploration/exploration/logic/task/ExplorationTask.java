package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;

import java.util.List;

public class ExplorationTask extends Task {
    private final Logger logger;

    public ExplorationTask(List<Action> taskSteps, Logger logger) {
        super(taskSteps); // MOVE, SCAN, ANALISE, LOG
        this.logger = logger;
    }

    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // TODO
        // ma zwracać true jeśli ExplorationOutcome rovera = Undefind
        return false;
    }



}
