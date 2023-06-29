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
    private MarsMap commonMap;

    private String currentTask;

    public void setDestination(Coordinate destination) {
        this.destination = destination;
    }

    private Coordinate destination;
    private Action currentActivityAssigned;

    public Rover(int sight, Base base, Coordinate currentPosition) {
        this.id = count;
        this.sight = sight;
        this.base = base;
        this.currentPosition = currentPosition;
        memory = new MarsMap(new HashMap<>());
        explorationOutcome = ExplorationOutcome.UNDEFINED;
        inventory = TileType.EMPTY;
        count++;
        this.commonMap = new MarsMap(new HashMap<>());
    }

    public Coordinate getDestination() {
        return destination;
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

    public void setCurrentActivityAssigned(Action currentActivityAssigned) {
        this.currentActivityAssigned = currentActivityAssigned;
    }

    public Action getCurrentActivityAssigned() {
        return currentActivityAssigned;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public void emptyInventory() {
        inventory = TileType.EMPTY;
    }

    public boolean isInventoryFull() {
        return !inventory.equals(TileType.EMPTY);
    }

    public void passResourcesToBase() {
        base.addResources(inventory);
        emptyInventory();
    }
}
