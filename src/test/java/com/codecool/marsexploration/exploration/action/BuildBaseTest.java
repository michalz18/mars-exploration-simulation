package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuildBaseTest {
    private Base base;
    private Rover rover;
    private SimulationContext simulationContext;
    private MarsMap marsMap;
    private Coordinate basePosition;
    private BuildBase buildBase;

    @BeforeEach
    public void setUp() {
        base = Mockito.mock(Base.class);
        rover = Mockito.mock(Rover.class);
        simulationContext = Mockito.mock(SimulationContext.class);
        marsMap = Mockito.mock(MarsMap.class);
        basePosition = new Coordinate(10, 10);

        when(rover.getBase()).thenReturn(base);
        when(base.getPosition()).thenReturn(basePosition);
        when(simulationContext.getMarsMap()).thenReturn(marsMap);

        buildBase = new BuildBase();
    }

    @Test
    public void takeAction_changesBaseStatusAndRemovesInventoryAndSetsBaseOnMap() {
        buildBase.takeAction(rover, simulationContext);

        verify(base, times(1)).setStatus(Status.BUILD);
        verify(rover, times(1)).setInventory(null);
        verify(marsMap, times(1)).setTileType(basePosition, TileType.BASE);
    }
}