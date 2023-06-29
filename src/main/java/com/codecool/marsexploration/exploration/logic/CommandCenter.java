package com.codecool.marsexploration.exploration.logic;

import com.codecool.marsexploration.exploration.logic.task.Task;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CommandCenter {

    private final List<Task> tasks;

    public CommandCenter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void runSimulationSteps(SimulationContext simulationContext, Logger logger) {
        System.out.println("start");
        boolean isSimulationRunning;
        boolean canRoversContinueTheirTasks;
        do {
            runRovers(simulationContext);
            runBases(simulationContext);
            canRoversContinueTheirTasks = simulationContext.getRovers().stream()
                    .allMatch(rover -> rover.getExplorationOutcome().equals(ExplorationOutcome.UNDEFINED) ||
                            rover.getExplorationOutcome().equals(ExplorationOutcome.RESOURCES_FOUND));
            isSimulationRunning = getBases(simulationContext)
                    .filter(base -> base.getStatus().equals(Status.OPERATING))
                    .count() <= simulationContext.getSuccessfulBasesThreshold();
        } while (canRoversContinueTheirTasks && isSimulationRunning && simulationContext.getSteps() < simulationContext.getTimeoutSteps());
        loggOutcome(simulationContext, logger, isSimulationRunning, canRoversContinueTheirTasks);
    }

    private void loggOutcome(SimulationContext simulationContext, Logger logger, boolean isSimulationRunning, boolean canRoversContinueTheirTasks) {
        if (!canRoversContinueTheirTasks) {
            logger.loggMarsUncolonizable();
        } else if (!isSimulationRunning) {
            logger.loggMissionSuccess(simulationContext.getSuccessfulBasesThreshold());
        } else {
            logger.logTimeout();
        }
    }

    private void runBases(SimulationContext simulationContext) {
        List<Base> bases = getBases(simulationContext)
                .filter(base -> base.getStatus().equals(Status.BUILD))
                .toList();
        for (Base base : bases) {
            simulationContext.addRover(base.buildRover());
            base.setStatus(Status.OPERATING);
        }
    }

    private void runRovers(SimulationContext simulationContext) {
        simulationContext.getRovers().forEach(rover -> {
                    Optional<Task> taskOpt = tasks.stream()
                            .filter(task -> task.shouldTaskBePerformed(rover))
                            .findFirst();
                    taskOpt
                            .ifPresent(task -> {
                                rover.setCurrentTask(task.getTaskName());
                                task.performTask(rover, simulationContext);});
    });
    }
    private Stream<Base> getBases(SimulationContext simulationContext) {
        return simulationContext.getRovers().stream()
                .map(Rover::getBase);
    }
}
