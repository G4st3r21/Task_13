package ru.vsu.cs.PoryadinAV;

import java.awt.*;

public class Cell {
    private final int cordX;
    private final int cordY;
    private boolean recolored = false;
    private Color color;

    public Cell(int cordX, int cordY, Color color) {
        this.cordX = cordX;
        this.cordY = cordY;
        this.color = color;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isRecolored() {
        return recolored;
    }

    public void setRecolored() {
        this.recolored = !recolored;
    }
}
