package com.codecool.marsexploration.exploration.logic;

import com.codecool.marsexploration.exploration.logic.task.Task;
import com.codecool.marsexploration.exploration.model.SimulationContext;

import java.util.List;
import java.util.Optional;

public class CommandCenter {

    private final List<Task> tasks;

    public CommandCenter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void assignTask(SimulationContext simulationContext) {
        simulationContext.getRovers().forEach(rover -> {
            Optional<Task> taskOpt = tasks.stream()
                    .filter(task -> task.shouldTaskBePreformed(rover))
                    .findFirst();
            taskOpt
                    .ifPresent(task -> task.performTask(rover, simulationContext));
            // TODO
            // if not present

            // TODO
            // zbierz wszystkie bazy z roverów i każda która jest BUILD, tworzy nowego rovera
            // zapisuje go w kontekście
            // i zmienia status na operating
        });
    }
}
