package com.company;

import java.util.*;

public class Main {

    private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();

    private void createContent(){

    }

    private void fillBoard(Board order, boolean isBegun){
        int type = 5;

        while(type > 0){
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (order.placeShip(new Ship(type, Math.random()<0.5),
                    x, y)){
                type--;
            }
        }
        running = isBegun;
    }


    private void startGame(){
        fillBoard(playerBoard, false);
        fillBoard(enemyBoard, true);
    }



    //Это все, что сделано для ИИ
    private Cell enemyFirstShot(int x, int y){
        Cell cell;
        do{
            x = random.nextInt(10);
            y = random.nextInt(10);

            cell = playerBoard.getCell(x, y);
        }
        while(!cell.wasShot);

        return cell;
    }

    private Cell enemyFirstHit( int x, int y){

        Cell cell1 = playerBoard.getCell(x + 1, y);
        Cell cell2 = playerBoard.getCell(x - 1, y);
        Cell cell3 = playerBoard.getCell(x, y + 1);
        Cell cell4 = playerBoard.getCell(x, y - 1);
        ArrayList<Cell> cellsToShoot1 = new ArrayList<Cell>();
        if(playerBoard.isPointValid(x + 1, y) && !cell1.wasShot){
            cellsToShoot1.add(cell1);
        }
        if(playerBoard.isPointValid(x - 1, y) && !cell2.wasShot){
            cellsToShoot1.add(cell2);
        }
        if(playerBoard.isPointValid(x, y + 1) && !cell3.wasShot){
            cellsToShoot1.add(cell3);
        }
        if(playerBoard.isPointValid(x, y - 1) && !cell4.wasShot){
            cellsToShoot1.add(cell4);
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(cellsToShoot1.size());
        Cell cellToShoot1 = cellsToShoot1.get(randomIndex);

        return cellToShoot1;
    }

    private Cell enemyNextHits(int x, int y){
        //здесь должна возвращаться выбранная клетка, но пока нет
        return null;
    }


    private void setNeighborsShot(int x, int y){
        if(playerBoard.isPointValid(x + 1, y)){
            playerBoard.getCell(x + 1, y).wasShot = true;
        }
        if(playerBoard.isPointValid(x - 1, y)){
            playerBoard.getCell(x - 1, y).wasShot = true;
        }
        if(playerBoard.isPointValid(x, y + 1)){
            playerBoard.getCell(x, y + 1).wasShot = true;
        }
        if(playerBoard.isPointValid(x, y - 1)){
            playerBoard.getCell(x, y - 1).wasShot = true;
        }
    }


    private void enemyMove(){
        Cell cell = null;
        int x = 0;
        int y = 0;
        while(enemyTurn){

            if(cell == null){
                cell = enemyFirstShot(x, y);
            } else {
                if(cell.ship.isAlive()) {
                    if (cell.ship.health == cell.ship.type - 1) {
                        cell = enemyFirstHit(x, y);
                    } else {
                        cell = enemyNextHits(x, y);
                    }
                } else {
                    setNeighborsShot(x, y);
                    cell = enemyFirstShot(x, y);
                }
            }

            enemyTurn = cell.shoot();

            if(playerBoard.ships == 0){
                playerBoard.playerWon = false;
            }
        }
    }


    public static void main(String[] args) {

    }


}
