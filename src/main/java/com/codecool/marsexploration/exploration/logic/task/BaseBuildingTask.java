package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;

public class BaseBuildingTask implements Task {

    private final TileType resourceNeededToBuildBase;

    private final List<Action> taskSteps; // SCAN, ANALISE_BASE_BUILD, MOVE, DO, LOG

    public BaseBuildingTask(TileType resourceNeededToBuildBase, List<Action> taskSteps) {
        this.resourceNeededToBuildBase = resourceNeededToBuildBase;
        this.taskSteps = taskSteps;
    }
    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // TODO
        // true if -> // TRUE -> jeśli rover.base.status = PLANNED && rover ma resourceNeededToBuildBase na stanie
        return false;
    }

    @Override
    public List<Action> performTask(Rover rover, SimulationContext simulationContext) {
        return null;
    }
}
