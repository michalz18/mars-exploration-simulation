package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public class ResourceGatheringTask implements Task {

    private final List<Action> taskSteps; // SCAN, ANALISE_RESOURCE_GATHERING, MOVE, DO, LOG

    public ResourceGatheringTask(List<Action> taskSteps) {
        this.taskSteps = taskSteps;
    }

    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // TODO
        // true -> rover.base.status = operating
        return false;
    }

    @Override
    public List<Action> performTask(Rover rover, SimulationContext simulationContext) {
        return null;
    }
}
