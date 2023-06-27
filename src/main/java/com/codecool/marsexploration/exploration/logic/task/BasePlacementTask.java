package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public class BasePlacementTask implements Task {



    private final List<Action> taskSteps; // PLAN BAZY

    public BasePlacementTask(List<Action> taskSteps) {
        this.taskSteps = taskSteps;
    }


    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // todo
        // TRUE -> jeśli rover.base.status = SPACESHIP || OPERATING
        return false;
    }

    @Override
    public List<Action> performTask(Rover rover, SimulationContext simulationContext) {
        return taskSteps;
    }
}
