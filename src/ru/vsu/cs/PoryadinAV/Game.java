package ru.vsu.cs.PoryadinAV;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final Random rnd = new Random();
    private int size;
    private Cell[][] field;
    private List<Cell> coloredCells = new ArrayList<>();
    private int countOfSteps = 0;
    private final Color[] colors = new Colors().getColors();

    public Game(int size) {
        this.size = size;
        this.field = new Cell[size][size];
        colorizeField();
        checkForNewCells(field[0][0].getColor(), coloredCells);
    }

    public Cell[][] getField() {
        return field;
    }

    public int getCountOfSteps() {
        return countOfSteps;
    }

    public void setCountOfSteps(int countOfSteps) { this.countOfSteps = countOfSteps; }

    public void setSize(int size) {
        this.size = size;
    }

    public void updateField(int size) {
        setSize(size);
        setCountOfSteps(0);
        field = new Cell[size][size];
        colorizeField();

        coloredCells = new ArrayList<>();
        coloredCells.add(field[0][0]);
        checkForNewCells(field[0][0].getColor(), coloredCells);
    }

    private void colorizeField() {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length; col++) {
                field[row][col] = new Cell(col, row, colors[rnd.nextInt(6)]);
            }
        }
        field[0][0].setRecolored();
        coloredCells.add(field[0][0]);
    }

    public boolean coloringCells(Color color) {
        boolean wonGame = false;
        if (coloredCells.get(0).getColor() != color) {
            wonGame = checkForNewCells(color, coloredCells);
            recolorColoredCells(color);
            countOfSteps++;
        }

        return wonGame;
    }

    private boolean checkForNewCells(Color color, List<Cell> nearCells) {
        int maxLength = nearCells.size();

        for (int cellNum = 0; cellNum < maxLength; cellNum++) {
            Cell cell = nearCells.get(cellNum);

            checkLeftCell(cell, color, nearCells);
            checkRightCell(cell, color, nearCells);
            checkDownCell(cell, color, nearCells);
            checkUpCell(cell, color, nearCells);
        }

        return nearCells.size() == size * size;
    }

    private void checkLeftCell(Cell cell, Color color, List<Cell> cordList) {
        try {
            if (field[cell.getCordY()][cell.getCordX() - 1].getColor().equals(color)
                    && !cordList.contains(field[cell.getCordY()][cell.getCordX() - 1]) &&
                    !field[cell.getCordY()][cell.getCordX() - 1].isRecolored()) {
                field[cell.getCordY()][cell.getCordX() - 1].setRecolored();

                List<Cell> tempList = new ArrayList<>(List.of(field[cell.getCordY()][cell.getCordX() - 1]));
                checkForNewCells(color, tempList);
                cordList.addAll(tempList);
            }
        } catch (Exception ignored) {}
    }

    private void checkRightCell(Cell cell, Color color, List<Cell> cordList) {
        try {
            if (field[cell.getCordY()][cell.getCordX() + 1].getColor().equals(color)
                    && !cordList.contains(field[cell.getCordY()][cell.getCordX() + 1]) &&
                    !field[cell.getCordY()][cell.getCordX() + 1].isRecolored()) {
                field[cell.getCordY()][cell.getCordX() + 1].setRecolored();

                List<Cell> tempList = new ArrayList<>(List.of(field[cell.getCordY()][cell.getCordX() + 1]));
                checkForNewCells(color, tempList);
                cordList.addAll(tempList);
            }
        } catch (Exception ignored) {}
    }

    private void checkDownCell(Cell cell, Color color, List<Cell> cordList) {
        try {
            if (field[cell.getCordY() + 1][cell.getCordX()].getColor().equals(color)
                    && !cordList.contains(field[cell.getCordY() + 1][cell.getCordX()]) &&
                    !field[cell.getCordY() + 1][cell.getCordX()].isRecolored()) {
                field[cell.getCordY() + 1][cell.getCordX()].setRecolored();

                List<Cell> tempList = new ArrayList<>(List.of(field[cell.getCordY() + 1][cell.getCordX()]));
                checkForNewCells(color, tempList);
                cordList.addAll(tempList);
            }
        } catch (Exception ignored) {}
    }

    private void checkUpCell(Cell cell, Color color, List<Cell> cordList) {
        try {
            if (field[cell.getCordY() - 1][cell.getCordX()].getColor().equals(color)
                    && !cordList.contains(field[cell.getCordY() - 1][cell.getCordX()]) &&
                    !field[cell.getCordY() - 1][cell.getCordX()].isRecolored()) {
                field[cell.getCordY() - 1][cell.getCordX()].setRecolored();

                List<Cell> tempList = new ArrayList<>(List.of(field[cell.getCordY() - 1][cell.getCordX()]));
                checkForNewCells(color, tempList);
                cordList.addAll(tempList);
            }
        } catch (Exception ignored) {}
    }

    private void recolorColoredCells(Color color) {
        for (Cell cell: coloredCells) {
            cell.setColor(color);
            field[cell.getCordY()][cell.getCordX()].setColor(color);
        }
    }
}