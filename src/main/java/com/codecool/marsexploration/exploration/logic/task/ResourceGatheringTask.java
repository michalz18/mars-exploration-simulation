package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public class ResourceGatheringTask extends Task {
    public ResourceGatheringTask(List<Action> taskSteps, String taskName) {
        super(taskSteps, taskName);
    }
    @Override
    public boolean shouldTaskBePerformed(Rover rover) {
        return rover.getBase().getStatus().equals(Status.OPERATING) && rover.getBase().getResources().size() < 2;
    }
}
