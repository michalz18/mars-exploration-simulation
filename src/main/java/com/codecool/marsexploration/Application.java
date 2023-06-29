package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.configuration.ConfigurationValidator;
import com.codecool.marsexploration.configuration.SimulationConfiguration;
import com.codecool.marsexploration.exploration.logic.CommandCenter;
import com.codecool.marsexploration.exploration.logic.ExplorationSimulator;
import com.codecool.marsexploration.exploration.logic.deployer.MissionDeployer;
import com.codecool.marsexploration.exploration.logic.deployer.RoverDeployer;
import com.codecool.marsexploration.logger.FileLogger;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.logger.MessageGeneratorImpl;
import com.codecool.marsexploration.maploader.MapLoaderImpl;
import com.codecool.marsexploration.model.rovers.rovermovement.*;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Set;

public class Application {
    private static final  String MAP_EXAMPLE_PATH = "src/main/resources/Map_0.map";
    private static final  String LOG_DIR = "src/main/resources/log/log.txt";

    public static void main(String[] args) {
        CoordinateCalculatorImpl coordinateCalculator = new CoordinateCalculatorImpl();
        MissionDeployer missionDeployer = new MissionDeployer(new RoverDeployer(coordinateCalculator));
        Logger logger = new FileLogger(LOG_DIR, new MessageGeneratorImpl());
        CommandCenter commandCenter = new CommandCenterDefaultGenerator().createCommandCenter(logger);
        ExplorationSimulator explorationSimulator = new ExplorationSimulator(logger, missionDeployer, new MapLoaderImpl(), new ConfigurationValidator(), commandCenter);
        java.util.Map<MovementStrategyType, MovementStrategy> movementStrategies = java.util.Map.of(
                MovementStrategyType.EXPLORING, new ExploringStrategy(coordinateCalculator, new CommonMarsMapBuilder()),
                MovementStrategyType.RETURNING, new ReturningStrategy(),
                MovementStrategyType.MOVING_TO_A_DESTINATION_COORDINATE, new MovingToADestinationCoordinate(new CommonMarsMapBuilder())
                );
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration(
                1000,
                MAP_EXAMPLE_PATH,
                Set.of(TileType.WATER, TileType.MINERAL),
                movementStrategies,
                3, 3);
        explorationSimulator.runSimulation(simulationConfiguration);
    }

}

