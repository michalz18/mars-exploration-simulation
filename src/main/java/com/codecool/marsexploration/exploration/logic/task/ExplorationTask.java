package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;

import java.util.List;

public class ExplorationTask extends Task {

    public ExplorationTask(List<Action> taskSteps, String taskName) {
        super(taskSteps, taskName);
    }

    @Override
    public boolean shouldTaskBePerformed(Rover rover) {
        return rover.getExplorationOutcome().equals(ExplorationOutcome.UNDEFINED);
    }


}
