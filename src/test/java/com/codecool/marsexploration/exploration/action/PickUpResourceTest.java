package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class PickUpResourceTest {
    @Test
    public void testTakeAction() {
        // creat mocks
        Rover rover = mock(Rover.class);
        SimulationContext simulationContext = mock(SimulationContext.class);
        MarsMap marsMap = mock(MarsMap.class);
        Coordinate coordinate = mock(Coordinate.class);

        // set mocks
        when(rover.getCurrentPosition()).thenReturn(coordinate);
        when(simulationContext.getMarsMap()).thenReturn(marsMap);

        // creat new PickUpResource
        PickUpResource pickUpResource = new PickUpResource();

        // call method
        pickUpResource.takeAction(rover, simulationContext);

        // then
        verify(rover, times(1)).setInventory(TileType.MINERAL);
        verify(marsMap, times(1)).setTileType(coordinate, TileType.EMPTY);
        verify(rover, times(1)).setCurrentActivityAssigned(null);
    }
}