package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PickUpResourceTest {
    private Rover rover;
    private SimulationContext simulationContext;
    private MarsMap marsMap;
    private Coordinate roverPosition;
    private PickUpResource pickUpResource;

    @BeforeEach
    public void setUp() {
        rover = Mockito.mock(Rover.class);
        simulationContext = Mockito.mock(SimulationContext.class);
        marsMap = Mockito.mock(MarsMap.class);
        roverPosition = new Coordinate(9, 9);

        when(simulationContext.getMarsMap()).thenReturn(marsMap);
        when(rover.getCurrentPosition()).thenReturn(roverPosition);

        pickUpResource = new PickUpResource();
    }

    @Test
    public void takeAction_setsRoverInventoryToMineral() {
        pickUpResource.takeAction(rover, simulationContext);

        verify(rover, times(1)).setInventory(TileType.MINERAL);
    }

    @Test
    public void takeAction_setsTileTypeToEmptyAtCurrentRoverPosition() {
        pickUpResource.takeAction(rover, simulationContext);

        verify(marsMap, times(1)).setTileType(roverPosition, TileType.EMPTY);
    }
    @Test
    public void takeAction_setsRoverCurrentActivityToNull(){
        pickUpResource.takeAction(rover, simulationContext);

        verify(rover, times(1)).setCurrentActivityAssigned(null);
    }
    @Test
    void takeActionIntegrationTest() {
        PickUpResource pickUpResource = new PickUpResource();

        Map<Coordinate, TileType> map = new HashMap<>();
        MarsMap marsMap = new MarsMap((HashMap<Coordinate, TileType>) map);

        Base base = new Base(Status.SPACESHIP, new Coordinate(0, 0));

       Rover rover = new Rover(1, base, MovementStrategyType.EXPLORING);

        SimulationContext simulationContext = new SimulationContext(0, 0, Collections.singletonList(rover), base, marsMap, new HashSet<>(), new HashMap<>());

        Coordinate mineralPosition = new Coordinate(1,1);
        rover.setCurrentPosition(mineralPosition);

        simulationContext.getMarsMap().setTileType(mineralPosition, TileType.MINERAL);

        pickUpResource.takeAction(rover, simulationContext);

        assertEquals(TileType.MINERAL, rover.getInventory());

        assertEquals(TileType.EMPTY, simulationContext.getMarsMap().getTileType(mineralPosition));

        assertNull(rover.getCurrentActivityAssigned());
    }
}