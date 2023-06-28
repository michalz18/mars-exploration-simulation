package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;

public class BaseBuildingTask extends Task {
    private final TileType resourceNeededToBuildBase;

    public BaseBuildingTask(List<Action> taskSteps, TileType resourceNeededToBuildBase) {
        super(taskSteps); // SCAN, ANALISE_BASE_BUILD, MOVE, DO, LOG
        this.resourceNeededToBuildBase = resourceNeededToBuildBase;
    }

    @Override
    public boolean shouldTaskBePerformed(Rover rover) {
        return rover.getBase().getStatus().equals(Status.PLANNED) && rover.getInventory().equals(resourceNeededToBuildBase);
    }

}
