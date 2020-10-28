package com.company;

public class Board {
    private boolean enemy = false;

    /*кораблей пока 5, у каждого свой размер, чтобы можно было быстро сделать
    цикл их создания; конечно, это тоже изменится*/
    public int ships = 5;
    public boolean playerWon;
    Cell[][] grid;

    public Board(boolean enemy){
        this.enemy = enemy;
        this.grid = new Cell[10][10];
    }

    public boolean placeShip(Ship ship, int x, int y) {
        //canPlaceShip, getCall

        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;
            if (ship.vertical) {
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


    private boolean canPlaceShip(Ship ship, int x, int y){
        //getCell, getNeighbors
        int length = ship.type;
        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isPointValid(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.ship != null)
                    return false;
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isPointValid(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.ship != null)
                    return false;
            }
        }
        return true;
    }


    public boolean isPointValid(int x, int y){
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

}
