package com.codecool.marsexploration.exploration.logic.deployer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class MissionDeployer {

    private RoverDeployer roverDeployer;

    public MissionDeployer(RoverDeployer roverDeployer) {
        this.roverDeployer = roverDeployer;
    }

    public boolean deployMission (Rover rover, Base base, MarsMap marsMap, int allowedAttemptsToLand) {
        boolean isBaseLanded = landBase(base, marsMap, allowedAttemptsToLand);
        if (!isBaseLanded) {
            return false;
        }
        return roverDeployer.landRover(marsMap, base, rover);
    }

    private boolean landBase(Base base, MarsMap marsMap, int allowedAttemptsToLand) {
        boolean success = false;
        Map<Coordinate, TileType> map = marsMap.getMap();
        for (int i = 0; i < allowedAttemptsToLand; i++) {
            Coordinate randomCoordinate = marsMap.getRandomCoordinate();
            if (map.get(randomCoordinate).equals(TileType.EMPTY)) {
                map.put(randomCoordinate, TileType.SPACESHIP);
                base.setPosition(randomCoordinate);
                success = true;
                break;
            }
        }
        return success;
    }
}
