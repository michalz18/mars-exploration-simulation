package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.tiletype.TileType;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapDisplay extends JFrame {
    private static MapDisplay instance; // Singleton instance

    private static final int TILE_SIZE = 20;
    private static final int MAP_SIZE = 40; // Rozmiar wyświetlanej mapy (liczba kafelków)

    private MarsMap marsMap;
    private MapVisualizer mapVisualizer;

    private Map<Coordinate, TileType> exploredMap;

    private MapDisplay(MarsMap marsMap) {
        this.marsMap = marsMap;
        this.exploredMap = marsMap.getMap();
        this.mapVisualizer = new MapVisualizer();

        setTitle("Rover Map Display");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setContentPane(mapVisualizer);
        pack();
        setVisible(true); // Wyświetlenie okna
    }
    // Method to get the singleton instance
    public static MapDisplay getInstance(MarsMap marsMap) {
        if (instance == null) {
            instance = new MapDisplay(marsMap);
        }
        return instance;
    }

    public void updateMap(MarsMap marsMap) {
        this.marsMap = marsMap;
        this.exploredMap = marsMap.getMap();
        mapVisualizer.repaint();
    }
    public void closeOldMap(){
        mapVisualizer.repaint();
    }

    private class MapVisualizer extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawMap(g);
        }

        private void drawMap(Graphics g) {
            for (int x = 0; x < MAP_SIZE; x++) {
                for (int y = 0; y < MAP_SIZE; y++) {
                    Coordinate coordinate = new Coordinate(x, y);
                    TileType tileType = exploredMap.getOrDefault(coordinate, TileType.EMPTY);

                    int xPos = x * TILE_SIZE;
                    int yPos = y * TILE_SIZE;

                    Color color = getColorForTileType(tileType);
                    g.setColor(color);
                    g.fillRect(xPos, yPos, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        private Color getColorForTileType(TileType tileType) {
            switch (tileType) {
                case EMPTY:
                    return Color.WHITE;
                case MOUNTAIN:
                    return Color.GRAY;
                case PIT:
                    return Color.BLACK;
                case WATER:
                    return Color.BLUE;
                case MINERAL:
                    return Color.YELLOW;
                case ROVER:
                    return Color.GREEN;
                case BASE:
                    return Color.RED;
                case SPACESHIP:
                    return Color.RED;
                default:
                    return Color.WHITE;
            }
        }

        @Override
        public Dimension getPreferredSize() {
            int width = MAP_SIZE * TILE_SIZE;
            int height = MAP_SIZE * TILE_SIZE;
            return new Dimension(width, height);
        }
    }
}
