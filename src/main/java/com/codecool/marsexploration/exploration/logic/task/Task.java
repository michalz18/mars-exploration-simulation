package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public abstract class Task {
    private final List<Action> taskSteps;

    private final String taskName;

    public Task(List<Action> taskSteps, String taskName) {
        this.taskSteps = taskSteps;
        this.taskName = taskName;
    }

    public abstract boolean shouldTaskBePerformed(Rover rover);
    public void performTask(Rover rover, SimulationContext simulationContext) {
        for (Action step : taskSteps) {
            step.takeAction(rover, simulationContext);
        }
    }

    public String getTaskName() {
        return taskName;
    }
}
