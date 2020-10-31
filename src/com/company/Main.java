package com.company;

import java.util.*;

public class Main
{
    private boolean running = false;
    private Board enemyBoard = new Board(true);
    private Board playerBoard = new Board(false);

    private boolean enemyTurn = false;

    private Random random = new Random();
    AI enemy = new AI();
    int y, x;
    private void fillBoard(Board order, boolean isBegun){
        int type = 4;

        while(order.ships > 0){
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (order.placeShip(new Ship(type, Math.random()<0.5),
                    x, y)){

                order.ships--;
                if(order.ships == 9 || order.ships == 7 || order.ships == 4){
                    --type;
                }
            }
        }
        running = isBegun;
    }

    private void startGame(){
        fillBoard(playerBoard, false);
        fillBoard(enemyBoard, true);
    }

    private void enemyMove(){
        Cell cel12 = enemy.startThink(playerBoard);
        enemyTurn = cel12.shoot();
    }

    private void playerMove(int y, int x){
        Cell cel12 = enemyBoard.getCell(y, x) ;
        enemyTurn = !cel12.shoot();
        cel12.isShipVisible = cel12.shoot();
    }

    private void gameProcess(){
        if(running) {
            while (playerBoard.playerWon == false && enemyBoard.playerWon == false) {
                if (enemyTurn == false) {
                    enemyMove();
                } else {
                    playerMove(y, x);
                }
            }
        }
    }

    private void createContent() {
        startGame();
        gameProcess();

    }



    public static void main(String[] args)
    {
        Main m1 = new Main();
        m1.createContent();
    }
}