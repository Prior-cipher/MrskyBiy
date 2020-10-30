package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;

public class Main
{

    private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();




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




    private void startGame() {
        enemyBoard = new Board(true);
        playerBoard = new Board(false);

        fillBoard(playerBoard, false);
        fillBoard(enemyBoard, true);
        AI test = new AI();
        AI test2 =new  AI();
        while (playerBoard.playerWon==false && enemyBoard.playerWon==false)
        {

            if(enemyTurn==false)
            {
                Cell cel12 = test.startThink(playerBoard);
                System.out.println( playerBoard.grid[cel12.y][cel12.x].shoot());
                System.out.println(cel12.x+" "+ cel12.y);

                showGrid(enemyBoard);
                showGrid(playerBoard);

                if(cel12.ship==null)
                {
                    enemyTurn=true;
                }
            }
            else
                {
                    Cell cel12 = test2.startThink(enemyBoard);
                    System.out.println( enemyBoard.grid[cel12.y][cel12.x].shoot());
                    System.out.println(cel12.x+" "+ cel12.y);

                    showGrid(enemyBoard);
                    showGrid(playerBoard);
                    if(cel12.ship==null)
                    {
                        enemyTurn=false;
                    }
                }


        }
        if(playerBoard.playerWon)
            System.out.println("выйграл игрок " );
        else
            {
                System.out.println("выйграл ии " );
            }

    }




    //Это все, что сделано для ИИ
//    private Cell enemyFirstShot(int x, int y)
//    {
//        Cell cell;
//        do{
//            x = random.nextInt(10);
//            y = random.nextInt(10);
//
//            cell = playerBoard.getCell(x, y);
//
//        }
//        while(!cell.wasShot);
//
//
//        return cell;
//

//    }

//    private Cell enemyFirstHit( int x, int y)
//    {
//        Cell cell0 =playerBoard.getCell(x , y);
//
//        Cell cell1 = playerBoard.getCell(x + 1, y);
//        Cell cell2 = playerBoard.getCell(x - 1, y);
//        Cell cell3 = playerBoard.getCell(x, y + 1);
//        Cell cell4 = playerBoard.getCell(x, y - 1);
//        ArrayList<Cell> cellsToShoot1 = new ArrayList<Cell>();
//        if(playerBoard.isPointValid(x + 1, y) && !cell1.wasShot){
//            cellsToShoot1.add(cell1);
//        }
//        if(playerBoard.isPointValid(x - 1, y) && !cell2.wasShot){
//            cellsToShoot1.add(cell2);
//        }
//        if(playerBoard.isPointValid(x, y + 1) && !cell3.wasShot){
//            cellsToShoot1.add(cell3);
//        }
//        if(playerBoard.isPointValid(x, y - 1) && !cell4.wasShot){
//            cellsToShoot1.add(cell4);
//        }
//        Random rand = new Random();
//        int randomIndex = rand.nextInt(cellsToShoot1.size());
//        Cell cellToShoot1 = cellsToShoot1.get(randomIndex);
//
//        return cellToShoot1;
//    }

//    private Cell enemyNextHits(int x, int y)
//    {
//        //здесь должна возвращаться выбранная клетка, но пока нет
//        return null;
//    }


//    private void setNeighborsShot(int x, int y)
//    {
//        if(playerBoard.isPointValid(x + 1, y)){
//            playerBoard.getCell(x + 1, y).wasShot = true;
//        }
//        if(playerBoard.isPointValid(x - 1, y)){
//            playerBoard.getCell(x - 1, y).wasShot = true;
//        }
//        if(playerBoard.isPointValid(x, y + 1)){
//            playerBoard.getCell(x, y + 1).wasShot = true;
//        }
//        if(playerBoard.isPointValid(x, y - 1)){
//            playerBoard.getCell(x, y - 1).wasShot = true;
//        }
//    }


//    private void enemyMove()
//    {
//        Cell cell = null;
//        int x = 0;
//        int y = 0;
//        while(enemyTurn){
//
//            if(cell == null){
//                cell = enemyFirstShot( x, y);
//            } else {
//                if(cell.ship.isAlive()) {
//                    if (cell.ship.health == cell.ship.type - 1) {
//                        cell = enemyFirstHit(x, y);
//                    } else {
//                        cell = enemyNextHits(x, y);
//                    }
//                } else {
//                    setNeighborsShot(x, y);
//                    cell = enemyFirstShot(x, y);
//                }
//            }
//
//            enemyTurn = cell.shoot();
//
//            if(playerBoard.ships == 0){
//                playerBoard.playerWon = false;
//            }
//        }
//    }


    public static void main(String[] args)
    {
        Main m1 = new Main();
        m1.startGame();
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
