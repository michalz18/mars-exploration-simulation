package com.codecool.marsexploration.maploader;

import com.codecool.marsexploration.model.map.MarsMap;

import java.io.IOException;

public interface MapLoader {
    public MarsMap loadMap(String filePath) throws IllegalArgumentException, IOException;
}
