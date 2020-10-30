package com.company;

public class Cell {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;
    public boolean isShipVisible;
    private Board board;

    public Cell(int y, int x, Board board){
        this.y = y;
        this.x = x;

        this.board = board;
    }

    public boolean shoot(){
        wasShot = true;

        if (ship != null){
            ship.hit();
            if(!ship.isAlive()){
                board.ships--;
            }
            return true;
        } else {

            return false;

        }
    }
}