package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board {
    private boolean enemy = false;
    List<Cell> neighbors = new ArrayList<Cell>();
    private Cell[] cellArray = neighbors.toArray(new Cell[0]);
    /*кораблей пока 5, у каждого свой размер, чтобы можно было быстро сделать
    цикл их создания; конечно, это тоже изменится*/
    public int ships = 10;
    public int Aliveships = 10;
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

    public Cell[] getNeighbors(int x, int y)
    {
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

    public boolean placeShip(Ship ship, int x, int y) {
        //canPlaceShip, getCall

        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;
            if (!ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.ship = ship;

                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.ship = ship;

                }
            }
            return true;
        }
        return false;
    }


    public Cell getCell(int x, int y){
        return grid[x][y];
    }


    private boolean canPlaceShip(Ship ship, int x, int y) {
        //getCell, getNeighbors
        int length = ship.type;
        if (!ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isPointValid(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.ship != null )
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isPointValid(x, i))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isPointValid(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.ship != null)
                    return false;
                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isPointValid(i, y))
                        return false;

                    if (neighbor.ship != null)
                        return false;
                }

            }
        }
        return true;
    }

    public   void checkWon()
    {
        if(this.Aliveships==0)
        {
            playerWon=true;
        }
    }


    public boolean isPointValid(int x, int y){
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

}
