package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.ClosestResourceCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.exploration.action.*;
import com.codecool.marsexploration.exploration.logic.CommandCenter;
import com.codecool.marsexploration.exploration.logic.task.*;
import com.codecool.marsexploration.logger.Logger;
import com.codecool.marsexploration.outcome.OutcomeAnalyser;
import com.codecool.marsexploration.outcome.analysers.MapAnalyser;
import com.codecool.marsexploration.outcome.analysers.ResourceAnalyser;
import com.codecool.marsexploration.outcome.analysers.StepAnalyser;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;

public class CommandCenterDefaultGenerator {

    public CommandCenter createCommandCenter(Logger logger) {
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();
        ClosestResourceCalculator resourceCalculator = new ClosestResourceCalculator();
        TileType resourceNeededToBuildBase = TileType.MINERAL;
        OutcomeAnalyser outcomeAnalyser =
                new OutcomeAnalyser(List.of(new ResourceAnalyser(), new MapAnalyser(0.75), new StepAnalyser()));
        Action analiseBaseBuild = new AnaliseBaseBuild(new BuildBase());
        Action analiseBaseResourceCollection = new AnaliseBaseResourceCollection(new PickUpResource(), resourceNeededToBuildBase, resourceCalculator);
        Action analiseExploration = new AnaliseExploration(outcomeAnalyser);
        Action analiseGatheringResources = new AnaliseGatheringResources(new PassResourceToBase(), TileType.WATER, resourceCalculator);
        Action doAction = new Do();
        Action log = new Log(logger);
        Action move = new Move();
        Action planBase = new PlanBase(new ClosestResourceCalculator(), coordinateCalculator);
        Action scan = new Scan(coordinateCalculator);
        ExplorationTask explorationTask = new ExplorationTask(List.of(scan, analiseExploration, log, move), "EXPLORATION");
        BasePlacementTask basePlacementTask = new BasePlacementTask(List.of(planBase), "BASE PLANNING");
        BaseResorceCollectionTask baseResorceCollectionTask = new BaseResorceCollectionTask(List.of(scan, analiseBaseResourceCollection, log, doAction, move), resourceNeededToBuildBase, "BASE RESOURCE COLLECTION");
        BaseBuildingTask baseBuildingTask = new BaseBuildingTask(List.of(scan, analiseBaseBuild, log, doAction, move), resourceNeededToBuildBase, "BASE BUILDING");
        ResourceGatheringTask resourceGatheringTask = new ResourceGatheringTask(List.of(scan, analiseGatheringResources, log, doAction, move), "RESOURCE GATHERING");
        return new CommandCenter(List.of(explorationTask, basePlacementTask, baseResorceCollectionTask, baseBuildingTask, resourceGatheringTask));
    }


}
