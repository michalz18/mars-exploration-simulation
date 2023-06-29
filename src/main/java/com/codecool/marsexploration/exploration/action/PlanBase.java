package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.ClosestResourceCalculator;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class PlanBase implements Action {
    ClosestResourceCalculator resourceFinder;

    public PlanBase(ClosestResourceCalculator resourceFinder) {
        this.resourceFinder = resourceFinder;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {

        Coordinate suitableCoordinate = resourceFinder.findClosestResource(rover, TileType.WATER);
        Map<Coordinate, TileType> mapWithNewBase = buildBase(rover, suitableCoordinate);

        int oldDimension = simulationContext.getMarsMap().getMapDimension();
        simulationContext.setMarsMap(new MarsMap(mapWithNewBase, oldDimension));

        Base newBase = createNewBase(suitableCoordinate);
        rover.setBase(newBase);

    }

    private Base createNewBase(Coordinate suitableCoordinate) {
        return new Base(Status.PLANNED, suitableCoordinate);
    }

    private Map<Coordinate, TileType> buildBase(Rover rover, Coordinate suitableCoordinate) {
        Map<Coordinate, TileType> map = rover.getMemory().getMap();
        map.put(suitableCoordinate, TileType.BASE);
        return map;
    }

}
