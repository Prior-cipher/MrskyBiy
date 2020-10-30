package com.company;

import java.util.*;

public class Main {

    private boolean running = false;
    Board enemyBoard = new Board(true);
    Board playerBoard = new Board(false);
    AI enemy = new AI();
    AI player = new AI();

    private boolean enemyTurn = true;

    private Random random = new Random();

    public void createContent(){
        startGame();
        while(running) {
            if (enemyTurn) {
                enemyMove();

            }
            if (!enemyTurn) {
                playerMove();

            }
        }
        showGrid(playerBoard);
        showGrid(enemyBoard);
    }
    private void playerMove(){
        while(!enemyTurn){

            Cell cell = player.startThink(enemyBoard);
            enemyTurn = !cell.shoot();
            if(enemyBoard.ships == 0){
                running = false;
            }

        }
    }
    private void enemyMove(){

        while(enemyTurn){

            Cell cell = enemy.startThink(playerBoard);
            enemyTurn = cell.shoot();

            if(playerBoard.ships == 0){
                running = false;
            }

        }
    }

    private void fillBoard(Board order, boolean isBegun){
        int type = 4;
        int shipsToPlace = 10;
        while(shipsToPlace > 0){
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (order.placeShip(new Ship(type, Math.random()<0.5),
                    x, y, !isBegun)){

                shipsToPlace--;
                if(shipsToPlace == 9 || shipsToPlace == 7 || shipsToPlace == 4){
                    --type;
                }
            }
        }
        running = isBegun;
    }


    public void startGame(){
        fillBoard(playerBoard, false);
        fillBoard(enemyBoard, true);

    }









    public static void main(String[] args) {
        Main m1= new Main();

        m1.createContent();
    }
    public void showGrid(Board a)
    {
        System.out.println("0123456789");
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {


                if(a.grid[i][j].wasShot && a.grid[i][j].ship==null)
                {
                    System.out.print('1');
                }
                else if (a.grid[i][j].ship==null)
                {
                    System.out.print('.');
                }

                else if(a.grid[i][j].wasShot && a.grid[i][j].ship!=null)
                {
                    System.out.print('2');
                }

                else
                {
                    System.out.print('*');
                }


            }
            System.out.println("|"+i);
        }
        System.out.println("----------------------------------------------------------");
    }




}


