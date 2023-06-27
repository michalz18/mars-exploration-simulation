package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.configuration.ConfigurationValidator;
import com.codecool.marsexploration.configuration.SimulationConfiguration;
import com.codecool.marsexploration.exploration.action.*;
import com.codecool.marsexploration.exploration.logic.ExplorationSimulator;
import com.codecool.marsexploration.exploration.logic.deployer.MissionDeployer;
import com.codecool.marsexploration.exploration.logic.deployer.RoverDeployer;
import com.codecool.marsexploration.logger.FileLogger;
import com.codecool.marsexploration.logger.MessageGeneratorImpl;
import com.codecool.marsexploration.maploader.MapLoaderImpl;
import com.codecool.marsexploration.model.rovers.rovermovement.ExploringStrategy;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategy;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.model.rovers.rovermovement.ReturningStrategy;
import com.codecool.marsexploration.outcome.OutcomeAnalyser;
import com.codecool.marsexploration.outcome.analysers.MapAnalyser;
import com.codecool.marsexploration.outcome.analysers.ResourceAnalyser;
import com.codecool.marsexploration.outcome.analysers.StepAnalyser;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;
import java.util.Set;

public class Application {
    private static final  String MAP_EXAMPLE_PATH = "src/main/resources/Map_0.map";
    private static final  String LOG_DIR = "src/main/resources/log/log.txt";

    public static void main(String[] args) {
        CoordinateCalculatorImpl coordinateCalculator = new CoordinateCalculatorImpl();
        MissionDeployer missionDeployer = new MissionDeployer(new RoverDeployer(coordinateCalculator));
        FileLogger fileLogger = new FileLogger(LOG_DIR, new MessageGeneratorImpl());
        ExplorationSimulator explorationSimulator = new ExplorationSimulator(fileLogger, missionDeployer, new MapLoaderImpl(), new ConfigurationValidator());
        java.util.Map<MovementStrategyType, MovementStrategy> movementStrategies = java.util.Map.of(
                MovementStrategyType.EXPLORING, new ExploringStrategy(coordinateCalculator),
                MovementStrategyType.RETURNING, new ReturningStrategy());
        OutcomeAnalyser outcomeAnalyser =
                new OutcomeAnalyser(List.of(new ResourceAnalyser(), new MapAnalyser(0.75), new StepAnalyser()));
        List<Action> simulationSteps = List.of(new Scan(coordinateCalculator), new Move(), new AnaliseExploration(outcomeAnalyser), new Log(fileLogger));
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration(
                1000,
                MAP_EXAMPLE_PATH,
                Set.of(TileType.WATER, TileType.MINERAL),
                movementStrategies,
                3,
                simulationSteps);
        explorationSimulator.runSimulation(simulationConfiguration);
    }

}

