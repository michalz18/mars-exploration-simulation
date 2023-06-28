package com.codecool.marsexploration.exploration.logic.task;

import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.exploration.action.AnaliseExploration;
import com.codecool.marsexploration.exploration.action.Log;
import com.codecool.marsexploration.exploration.action.Move;
import com.codecool.marsexploration.exploration.action.Scan;
import com.codecool.marsexploration.logger.ConsoleLogger;
import com.codecool.marsexploration.logger.MessageGeneratorImpl;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.outcome.OutcomeAnalyser;
import com.codecool.marsexploration.outcome.analysers.MapAnalyser;
import com.codecool.marsexploration.outcome.analysers.ResourceAnalyser;
import com.codecool.marsexploration.outcome.analysers.StepAnalyser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExplorationTaskTest {
    ExplorationTask explorationTask;
    Rover rover;

    @BeforeEach
    void setUp() {
        explorationTask = new ExplorationTask(List.of(
                new Move(),
                new Scan(new CoordinateCalculatorImpl()),
                new AnaliseExploration(new OutcomeAnalyser(List.of(new ResourceAnalyser(), new MapAnalyser(0.75), new StepAnalyser()))),
                new Log(new ConsoleLogger(new MessageGeneratorImpl()))
        ), "EXPLORATION");
        rover = mock(Rover.class);
    }

    @Test
    public void shouldTaskBePreformedShouldReturnTrueForRoverOutcomeEqualsUNDEFINED() {
        when(rover.getExplorationOutcome()).thenReturn(ExplorationOutcome.UNDEFINED);


        boolean actual = explorationTask.shouldTaskBePerformed(rover);


        assertTrue(actual);

    }

    @Test
    public void shouldTaskBePreformedShouldReturnFalseForRoverOutcomeEqualsDifferentThatUNDEFINED() {
        when(rover.getExplorationOutcome()).thenReturn(ExplorationOutcome.TIMEOUT);


        boolean actual = explorationTask.shouldTaskBePerformed(rover);


        assertFalse(actual);

    }
}
