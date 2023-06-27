package com.codecool.marsexploration.tiletype;

public enum TileType {

    EMPTY("-"),
    MOUNTAIN("^"),
    PIT("0"),
    WATER("#"),
    MINERAL("*"),
    ROVER("7"),
    SPACESHIP("S"),
    BASE("B");

    private final String symbol;

    TileType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static boolean isNotEmpty(TileType tileType) {
        return !tileType.equals(TileType.EMPTY);
    }

    public static boolean isEmpty(TileType tileType) {
        return tileType.equals(TileType.EMPTY);
    }
}
