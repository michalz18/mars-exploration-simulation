package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public abstract class Task {
    private final List<Action> taskSteps;

    public Task(List<Action> taskSteps) {
        this.taskSteps = taskSteps;
    }

    public abstract boolean shouldTaskBePreformed(Rover rover);
    public void performTask(Rover rover, SimulationContext simulationContext) {
        for (Action step : taskSteps) {
            step.takeAction(rover, simulationContext);
        }
    }
}
