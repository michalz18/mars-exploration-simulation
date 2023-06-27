package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;

public class BaseResorceCollectionTask extends Task {
    private final TileType resourceNeededToBuildBase;

    public BaseResorceCollectionTask(List<Action> taskSteps, TileType resourceNeededToBuildBase) {
        super(taskSteps); // SCAN, ANALISE_BASE_RESOURCE_COLLECTION, MOVE, DO, LOG
        this.resourceNeededToBuildBase = resourceNeededToBuildBase;
    }

    @Override
    public boolean shouldTaskBePreformed(Rover rover) {
        // todo
        // TRUE -> jeśli rover.base.status = PLANNED && rover nie ma resourceNeededToBuildBase na stanie
        return false;
    }
}
