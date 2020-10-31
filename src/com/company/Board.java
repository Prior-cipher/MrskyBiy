package com.company;

import java.util.*;
import java.util.List;

public class Board {
    private boolean enemy = false;
    public int ships = 10;
    public int aliveShips = 10;
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
        List<Cell> neighbors = new ArrayList<>();

        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if(isPointValid(x+i,y+j) && !(i==0 && j==0) )
                {


                    neighbors.add(this.grid[x+i][y+j]);

                }
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    private void createShip(Ship ship, int x, int y) {
        Cell cell = getCell(x, y);
        cell.ship = ship;
        cell.isShipVisible = this.enemy;
    }

    public boolean placeShip(Ship ship, int x, int y) {
        //canPlaceShip, getCall

        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;
            if (!ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    createShip(ship, x, i);
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    createShip(ship, i, y);
                }
            }
            return true;
        }
        return false;
    }




    public Cell getCell(int x, int y){
        return grid[y][x];
    }



    private boolean canPlaceShip(Ship ship, int x, int y)
    {

        int length = ship.type;
        if (!ship.vertical)
        {
            for (int i = y; i < y + length; i++) {
                if (!isPointValid(x, i) || getCell(x,i).ship!= null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i))
                {
                    if (!isPointValid(x, i)||neighbor.ship != null) {
                        return false;
                    }
                }
            }
        }
        else
        {
            for (int i = x; i < x + length; i++)
            {
                if (!isPointValid(i, y) || getCell(i,y).ship!=null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y))
                {
                    if (!isPointValid(i, y) ||neighbor.ship != null)
                        return false;
                }
            }
        }
        return true;
    }



    public   void checkWon()
    {
        if(this.aliveShips==0)
        {
            playerWon=true;
        }
    }


    public boolean isPointValid(int x, int y){
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

}
