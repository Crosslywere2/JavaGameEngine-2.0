package com.crossly.utils;

public class Coordinate {
    private int x, y;

    public Coordinate() {
        x = y = 0;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate that) {
        this.x = that.x;
        this.y = that.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
