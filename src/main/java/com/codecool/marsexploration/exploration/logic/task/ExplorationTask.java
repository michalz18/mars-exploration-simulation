package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;

import java.util.List;

public class ExplorationTask implements Task {

    private final List<Action> taskSteps; // MOVE, SCAN, ANALISE, LOG
    private final Logger logger;

    public ExplorationTask(List<Action> taskSteps, Logger logger) {
        this.taskSteps = taskSteps;
        this.logger = logger;
    }

    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // TODO
        // ma zwracać true jeśli ExplorationOutcome rovera = Undefind
        return false;
    }

    @Override
    public List<Action> performTask(Rover rover, SimulationContext simulationContext) {
        return taskSteps;
    }


}
