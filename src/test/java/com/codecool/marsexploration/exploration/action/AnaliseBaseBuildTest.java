package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnaliseBaseBuildTest {
    private Rover rover;
    private Action baseBuilding;
    private AnaliseBaseBuild analiseBaseBuild;
    private SimulationContext simulationContext;
    private Base base;

    @BeforeEach
    void setUp() {
        rover = mock(Rover.class);
        base = mock(Base.class);
        baseBuilding = mock(Action.class);
        analiseBaseBuild = new AnaliseBaseBuild(baseBuilding);
        simulationContext = mock(SimulationContext.class);
    }

    @Test
    void shouldSetBaseBuildingAsCurrentActivityWhenAtBasePosition() {
        Base base = mock(Base.class);
        Coordinate coordinate = new Coordinate(0, 0);
        when(rover.getCurrentPosition()).thenReturn(coordinate);
        when(rover.getBase()).thenReturn(base);
        when(base.getPosition()).thenReturn(coordinate);

        analiseBaseBuild.takeAction(rover, simulationContext);

        verify(rover).setCurrentActivityAssigned(baseBuilding);
    }

    @Test
    void shouldSetNullAsCurrentActivityWhenNotAtBasePosition() {
        Base base = mock(Base.class);
        when(rover.getCurrentPosition()).thenReturn(new Coordinate(1, 1));
        when(rover.getBase()).thenReturn(base);
        when(base.getPosition()).thenReturn(new Coordinate(0, 0));

        analiseBaseBuild.takeAction(rover, simulationContext);

        verify(rover).setCurrentActivityAssigned(null);
    }
}