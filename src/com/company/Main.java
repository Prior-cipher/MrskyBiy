package com.company;


import java.util.*;

public class Main
{

    private boolean running = false;
    private Board enemyBoard = new Board(true);
    private Board playerBoard = new Board(false);

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();
    AI test = new AI();
    AI test2 = new AI();



    Scanner in= new Scanner(System.in);


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
        Cell cel12 = test.startThink(playerBoard);
        System.out.println(playerBoard.grid[cel12.y][cel12.x].shoot());
        System.out.println(cel12.x + " " + cel12.y);

        showGrid(enemyBoard);
        showGrid(playerBoard);

        if (cel12.ship == null) {
            enemyTurn = true;
        }
    }

    private void playerMove(){
        Cell cel12 = test2.startThink(enemyBoard);
        System.out.println(enemyBoard.grid[cel12.y][cel12.x].shoot());
        System.out.println(cel12.x + " " + cel12.y);

        showGrid(enemyBoard);
        showGrid(playerBoard);
        if (cel12.ship == null) {
            enemyTurn = false;
        }
    }

    private void gameProcess(){
        if(running) {
            while (playerBoard.playerWon == false && enemyBoard.playerWon == false) {
                if (enemyTurn == false) {
                    enemyMove();
                } else {
                    playerMove();
                }
            }
            if (playerBoard.playerWon)
                System.out.println("выиграл игрок ");
            else {
                System.out.println("выиграл ии ");
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