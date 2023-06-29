package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;

import java.util.List;

public class BasePlacementTask extends Task {

    public BasePlacementTask(List<Action> taskSteps, String taskName) {
        super(taskSteps, taskName); }

    @Override
    public boolean shouldTaskBePerformed(Rover rover) {
        return rover.getExplorationOutcome().equals(ExplorationOutcome.RESOURCES_FOUND) &&
                !rover.getBase().getStatus().equals(Status.PLANNED) && rover.getCurrentTask().equals("EXPLORATION");
    }

}
