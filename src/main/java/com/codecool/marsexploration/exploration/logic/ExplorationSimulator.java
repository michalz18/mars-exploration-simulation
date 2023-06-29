package com.codecool.marsexploration.exploration.logic;

import com.codecool.marsexploration.configuration.ConfigurationValidator;
import com.codecool.marsexploration.configuration.SimulationConfiguration;
import com.codecool.marsexploration.exploration.logic.deployer.MissionDeployer;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.logger.Logger;

import com.codecool.marsexploration.maploader.MapLoader;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExplorationSimulator {
    private final Logger logger;
    private final MissionDeployer missionDeployer;
    private final MapLoader mapLoader;
    private final ConfigurationValidator configurationValidator;

    private final CommandCenter commandCenter;

    public ExplorationSimulator(Logger logger, MissionDeployer missionDeployer, MapLoader mapLoader, ConfigurationValidator configurationValidator, CommandCenter commandCenter) {
        this.logger = logger;
        this.missionDeployer = missionDeployer;
        this.mapLoader = mapLoader;
        this.configurationValidator = configurationValidator;
        this.commandCenter = commandCenter;
    }

    public void runSimulation(SimulationConfiguration configuration) {
        boolean isConfigurationValid = configurationValidator.validateConfiguration(configuration);
        if (!isConfigurationValid) {
            logger.loggExplorationConfigurationError();
            return;
        }
        MarsMap marsMap;
        try {
            marsMap = mapLoader.loadMap(configuration.getFilePath());
        } catch (IOException e) {
            logger.loggMapFailedToLoad();
            return;
        }
        Base spaceship = new Base(Status.SPACESHIP, null);
        Rover rover = new Rover(2, spaceship, null);
        boolean isMissionDeployed = missionDeployer.deployMission(rover, spaceship, marsMap, configuration.getAllowedAttemptsToLand());
        if (!isMissionDeployed) {
            logger.loggExplorationFailToLand();
            return;
        }
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);
        SimulationContext simulationContext = init(configuration, rovers, spaceship, marsMap);
        commandCenter.runSimulationSteps(simulationContext, logger);
    }

    public SimulationContext init(SimulationConfiguration configuration, List<Rover> rovers, Base spaceship, MarsMap marsMap) {
        return
                new SimulationContext(
                        0,
                        configuration.getTimeoutSteps(),
                        rovers, spaceship, marsMap,
                        configuration.getResourcesToLookFor(),
                        configuration.getMovementStrategies(), configuration.getSuccessfulBasesThreshold());
    }

}
