package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public class BasePlacementTask extends Task {

    public BasePlacementTask(List<Action> taskSteps) {
        super(taskSteps); // PLAN BAZY
    }

    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        return rover.getBase().getStatus().equals(Status.SPACESHIP) || rover.getBase().getStatus().equals(Status.OPERATING);
    }

}
