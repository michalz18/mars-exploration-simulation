package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public class ResourceGatheringTask extends Task {
    public ResourceGatheringTask(List<Action> taskSteps) {
        super(taskSteps); // SCAN, ANALISE_RESOURCE_GATHERING, MOVE, DO, LOG
    }

    @Override
    public boolean shouldTaskBePerformed(Rover rover) {
        return rover.getBase().getStatus() == Status.OPERATING;
    }
}
