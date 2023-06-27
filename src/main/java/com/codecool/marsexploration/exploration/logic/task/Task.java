package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;

public interface Task {

    boolean shouldTaskBePreformed(Rover rover);
    List<Action> performTask(Rover rover, SimulationContext simulationContext);
}
