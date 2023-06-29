package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.tiletype.TileType;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapVisualizer extends JPanel {

    private Map<Coordinate, TileType> map;
    private int tileSize;
    private int width;
    private int height;

    public MapVisualizer(int tileSize, int width, int height) {
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(tileSize * width, tileSize * height)); // Ustawia preferowany rozmiar panelu
    }

    public void setMap(Map<Coordinate, TileType> map) {
        this.map = map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (map != null) {
            // Rysowanie mapy
            for (Map.Entry<Coordinate, TileType> entry : map.entrySet()) {
                Coordinate coordinate = entry.getKey();
                TileType tileType = entry.getValue();
                int x = coordinate.x() * tileSize;
                int y = coordinate.y() * tileSize;

                // Rysowanie pojedynczego elementu mapy
                g.drawString(tileType.getSymbol(), x, y);
            }
        }
    }
}
