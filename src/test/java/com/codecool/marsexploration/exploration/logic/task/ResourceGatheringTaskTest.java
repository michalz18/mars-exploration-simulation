package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.ClosestResourceCalculator;
import com.codecool.marsexploration.exploration.action.*;
import com.codecool.marsexploration.logger.FileLogger;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.OutcomeAnalyser;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceGatheringTaskTest {

    ResourceGatheringTask resourceGatheringTask;
    Rover rover;
    Base base;

    @BeforeEach
    void setUp() {
        resourceGatheringTask = new ResourceGatheringTask(
                List.of(
                        new Scan(mock(CoordinateCalculator.class)),
                        new Move(), new AnaliseExploration(mock(OutcomeAnalyser.class)),
                        new Log(mock(FileLogger.class)),
                        new Do(),
                        new AnaliseGatheringResources(new PassResourceToBase(), TileType.MINERAL, mock(ClosestResourceCalculator.class))
                )
        , "");
        rover = mock(Rover.class);
        base = mock(Base.class);

    }

    @Test
    public void shouldTaskBePerformedShouldReturnTrueForStatusOperating() {
        when(rover.getBase()).thenReturn(base);
        when(base.getStatus()).thenReturn(Status.OPERATING);


        boolean actual = resourceGatheringTask.shouldTaskBePerformed(rover);


        assertTrue(actual);
    }

    @Test
    public void shouldTaskBePerformedShouldReturnFalseForStatusDifferentThanOperating() {
        when(rover.getBase()).thenReturn(base);
        when(base.getStatus()).thenReturn(Status.BUILD);


        boolean actual = resourceGatheringTask.shouldTaskBePerformed(rover);


        assertFalse(actual);
    }

}