package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board {
    private boolean enemy = false;
    public int ships = 10;
    public boolean playerWon;
    public Cell[][] grid;

    public Board(boolean enemy)
    {

        this.enemy = enemy;
        this.grid = new Cell[10][10];

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                this.grid[i][j]= new Cell(j,i,this);
            }
        }
    }

    public Cell[] getNeighbors(int x, int y){
        Point[] points = new Point[] {
                new Point(x - 1, y),
                new Point(x + 1, y),
                new Point(x, y - 1),
                new Point(x, y + 1),
                new Point(x + 1, y + 1),
                new Point(x + 1, y - 1),
                new Point(x - 1, y + 1),
                new Point(x - 1, y - 1)
        };
        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point p : points) {
            if (isPointValid((int) p.getX(), (int)p.getY())) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);
    }

    public boolean placeShip(Ship ship, int y, int x, boolean visibility) {
        //canPlaceShip, getCall

        if (canPlaceShip(ship, y, x)) {
            int length = ship.type;
            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(i, x);
                    cell.ship = ship;
                    cell.isShipVisible = visibility;
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(y, i);
                    cell.ship = ship;
                    cell.isShipVisible = visibility;

                }
            }
            return true;
        }
        return false;
    }


    public Cell getCell(int y, int x){
        return grid[y][x];
    }


    private boolean canPlaceShip(Ship ship, int y, int x) {
        //getCell, getNeighbors
        int length = ship.type;
        if (!ship.vertical) {
            for (int i = x; i < x + length; i++) {
                if (!isPointValid(y, i))
                    return false;

                Cell cell = getCell(y, i);
                if (cell.ship != null )
                    return false;

                for (Cell neighbor : getNeighbors(y, i)) {
                    if (!isPointValid(y, i))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        } else {
            for (int i = y; i < y + length; i++) {
                if (!isPointValid(i, x))
                    return false;

                Cell cell = getCell(i, x);
                if (cell.ship != null)
                    return false;
                for (Cell neighbor : getNeighbors(i, x)) {
                    if (!isPointValid(i, x))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }

            }
        }
        return true;
    }


    public boolean isPointValid(int x, int y){
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

}