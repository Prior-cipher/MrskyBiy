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

    private void enemyMove(){
        while(enemyTurn){
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if(cell.wasShot)
                continue;

            enemyTurn = !cell.shoot();

            if(playerBoard.ships == 0){
                playerBoard.playerWon = false;
            }
        }
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
    public static void main(String[] args) {

    }
}
