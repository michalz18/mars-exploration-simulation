package com.codecool.marsexploration.model.rovers;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.action.Action;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.HashMap;

public class Rover {

    private static long count = 0;
    private final long id;
    private Coordinate currentPosition;
    private final int sight;
    private MarsMap memory;
    private MovementStrategyType currentMovementStrategyType;
    private Base base;
    private ExplorationOutcome explorationOutcome;
    private TileType inventory;
    private Coordinate destination;
    private Action currentActivityAssigned;


    public Rover(int sight, Base base, MovementStrategyType movementStrategyType) {
        this.id = count;
        this.sight = sight;
        this.base = base;
        this.currentMovementStrategyType = movementStrategyType;
        memory = new MarsMap(new HashMap<>());
        explorationOutcome = ExplorationOutcome.UNDEFINED;
    }

    public TileType getInventory() {
        return inventory;
    }

    public void setInventory(TileType inventory) {
        this.inventory = inventory;
    }

    public long getId() {
        return id;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getSight() {
        return sight;
    }

    public MarsMap getMemory() {
        return memory;
    }

    public ExplorationOutcome getExplorationOutcome() {
        return explorationOutcome;
    }

    public void setExplorationOutcome(ExplorationOutcome explorationOutcome) {
        this.explorationOutcome = explorationOutcome;
    }

    public MovementStrategyType getCurrentMovementStrategyType() {
        return currentMovementStrategyType;
    }

    public boolean hasExplorationEnded() {
        return explorationOutcome != ExplorationOutcome.UNDEFINED;
    }

    public void setMemory(MarsMap memory) {
        this.memory = memory;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public void setCurrentMovementStrategyType(MovementStrategyType currentMovementStrategyType) {
        this.currentMovementStrategyType = currentMovementStrategyType;
    }

    public int getExploredMapSize() {
        return memory.getSize();
    }

}
