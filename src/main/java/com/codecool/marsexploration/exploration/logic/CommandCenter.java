package com.codecool.marsexploration.exploration.logic;

import com.codecool.marsexploration.exploration.logic.task.Task;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CommandCenter {

    private final List<Task> tasks;

    public CommandCenter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void runSimulationSteps(SimulationContext simulationContext) {
        System.out.println("start");
        boolean isSimulationRunning = true;
        do {
            runRovers(simulationContext);
            runBases(simulationContext);
            isSimulationRunning = getBases(simulationContext)
                    .filter(base -> base.getStatus().equals(Status.OPERATING))
                    .count() <= 2;
        } while (isSimulationRunning && simulationContext.getSteps() < simulationContext.getTimeoutSteps());
        System.out.println("HURRAH!");
    }

    private void runBases(SimulationContext simulationContext) {
        getBases(simulationContext)
                .filter(base -> base.getStatus().equals(Status.BUILD))
                .forEach(base -> {
                    simulationContext.addRover(base.buildRover());
                    base.setStatus(Status.OPERATING);
                });
    }

    private void runRovers(SimulationContext simulationContext) {
        simulationContext.getRovers().forEach(rover -> {
                    Optional<Task> taskOpt = tasks.stream()
                            .filter(task -> task.shouldTaskBePerformed(rover))
                            .findFirst();
                    taskOpt
                            .ifPresent(task -> {
                                rover.setCurrentTask(task.getTaskName());
                                task.performTask(rover, simulationContext);
                            });
    });
    }
    private Stream<Base> getBases(SimulationContext simulationContext) {
        return simulationContext.getRovers().stream()
                .map(Rover::getBase);
    }
}
