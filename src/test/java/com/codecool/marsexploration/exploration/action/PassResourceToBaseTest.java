package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassResourceToBaseTest {
    PassResourceToBase passResourceToBase;
    Rover rover;
    SimulationContext simulationContext;
    Base base;


    @BeforeEach
    void setUp() {
        passResourceToBase = new PassResourceToBase();
        rover = mock(Rover.class);
        simulationContext = mock(SimulationContext.class);
        base = mock(Base.class);
    }

    @Test
    public void takeActionShouldAddResourcesToBaseAndResetInventory() {
        when(rover.getInventory()).thenReturn(TileType.MINERAL);
        when(rover.getBase()).thenReturn(base);


        passResourceToBase.takeAction(rover, simulationContext);


        verify(base, times(1)).addResources(TileType.MINERAL);
        verify(rover, times(1)).setInventory(null);
    }


}