package com.codecool.marsexploration.maploader;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.tiletype.TileType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLoaderImpl implements MapLoader {
    @Override
    public MarsMap loadMap(String filePath) throws IllegalArgumentException, IOException {
        validateFileExists(filePath); //TODO przerzucić do validateConfig
        Map<Coordinate, TileType> mapData = new HashMap<>();
        int row = 0;
        List<String> lines = readAllLinesFromFile(filePath);
        for (String line : lines) {
            line = line.trim();
            addTilesToMapData(mapData, row, line);
            row++;
        }
        return new MarsMap(mapData, row);
    }

    private void addTilesToMapData(Map<Coordinate, TileType> mapData, int row, String line) {
        int col = 0;
        for (char symbol : line.toCharArray()) {
            Coordinate coordinate = new Coordinate(row, col);
            TileType tileType = getTileTypeBySymbol(symbol);
            mapData.put(coordinate, tileType);
            col++;
        }
    }

    private List<String> readAllLinesFromFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

private void validateFileExists(String filePath) { //TODO przerzucić do validateConfig
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found" + filePath);
        }
    }

    private TileType getTileTypeBySymbol(char symbol) {
        for (TileType tileType : TileType.values()) {
            if (tileType.getSymbol().charAt(0) == symbol) {
                return tileType;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }
}
