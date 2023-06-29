package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoTest {
    private Rover rover;
    private SimulationContext simulationContext;
    private Action currentActivity;
    private Do doAction;

    @BeforeEach
    public void setUp() {
        rover = Mockito.mock(Rover.class);
        simulationContext = Mockito.mock(SimulationContext.class);
        currentActivity = Mockito.mock(Action.class);

        when(rover.getCurrentActivityAssigned()).thenReturn(currentActivity);

        doAction = new Do();
    }

    @Test
    public void takeAction_callsAssignedActivityActionWhenNotNull() {
        doAction.takeAction(rover, simulationContext);

        verify(currentActivity, times(1)).takeAction(rover, simulationContext);
    }

    @Test
    public void takeAction_doesNotCallAssignedActivityActionWhenNull() {
        when(rover.getCurrentActivityAssigned()).thenReturn(null);

        doAction.takeAction(rover, simulationContext);

        verify(currentActivity, never()).takeAction(rover, simulationContext);
    }
}