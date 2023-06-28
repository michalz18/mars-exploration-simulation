package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;

public class BaseResorceCollectionTask extends Task {
    private final TileType resourceNeededToBuildBase;

    public BaseResorceCollectionTask(List<Action> taskSteps, TileType resourceNeededToBuildBase, String taskName) {
        super(taskSteps, taskName); // SCAN, ANALISE_BASE_RESOURCE_COLLECTION, MOVE, DO, LOG
        this.resourceNeededToBuildBase = resourceNeededToBuildBase;
    }

    @Override
    public boolean shouldTaskBePerformed(Rover rover) {
        return rover.getBase().getStatus().equals(Status.PLANNED) && !rover.getInventory().equals(resourceNeededToBuildBase);
    }
}
