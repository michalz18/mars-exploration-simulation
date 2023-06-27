package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public class ResourceGatheringTask extends Task {
    public ResourceGatheringTask(List<Action> taskSteps) {
        super(taskSteps); // SCAN, ANALISE_RESOURCE_GATHERING, MOVE, DO, LOG
    }

    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // TODO
        // true -> rover.base.status = operating
        return false;
    }
}
