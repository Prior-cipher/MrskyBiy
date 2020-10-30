
package com.company;

import java.util.*;

public class AI
{
    /*начальная ремарка прочитай пожалуйста
    как работает ии.
    передаешь в него доску.
    он ищет точку куда стрельнуть
    и возвращяет точку
    точнее ячейку вытаскивай из нее координаты и работай с ней
    дальше нюансы
   этап первый стрельба куда придется
   второй стрельба вокруг первого до второго
   третий определение положения корабля поиск клеток на предпологаемых концах
   четвернтый после смерти корабля должен запустится сбросс ии метод ту зеро
   запускать его должен твой кораблеокуржатель
*/

    static ArrayList<Cell> cellsWithShip = new ArrayList<Cell>();

    private Random random = new Random();
    static boolean horizon = false;


    static boolean keepFinding = false;


    static boolean startNoCheck =true;
    Random rand = new Random();


//    тут твой код

    private Cell enemyFirstShot(int x, int y,Board board)
    {
        Cell cell;

        do {
            x = random.nextInt(10);
            y = random.nextInt(10);

            cell = board.getCell(x, y);
        }
        while ( cell.wasShot!=false);






        return cell;


    }


    public Cell startThink(Board board)
    {

        killConfermded(board);

        Cell target;

        if (keepFinding==false)
        {
            target= enemyFirstShot(0,0,board);

        }

        //если мы стреляем по ранмоу в первый раз отрабатывает  данный модуль
        //иначе запускаем модули с мозгами
        else
        {


            if (cellsWithShip.size()==1)
            {
                target=check(cellsWithShip.get(0),board);
                //тут запустится и будет запускаться твой модуль с рандомом пок4а мы не попадем в корабль еще раз
            }

            else
            {
                target=thinkModule(board);

            }

        }
        //тут мы меняем флаг для следующих вызовов
        //если мы попали конечно
        //а они точно будут если мы попали
        checkADd(target);


        return target;
    }



    private void killConfermded(Board board)
    {
        if(cellsWithShip.size()>0 && cellsWithShip.get(0).ship.health==0)
        {
            for (Cell cell:cellsWithShip)
            {
                for (Cell cell1:board.getNeighbors(cell.y,cell.x))
                {
                    cell1.shoot();
                }
            }


            setToZero();
        }

    }



    private Cell check(Cell target,Board board)
    {

        //данный модуль срабатывает до тех пор пока мы не попадем еще раз
        //выбирает случайную точку вокруг первого попадания
        ArrayList<Cell> cellsToShoot = new ArrayList<Cell>();
        Cell cellToShoot;
        if(board.isPointValid(target.x + 1, target.y) && !(board.grid[target.y][target.x+1].wasShot))
        {
            cellsToShoot.add(board.grid[target.y][target.x+1]);

        }
        if(board.isPointValid(target.x-1 , target.y) && !(board.grid[target.y][target.x-1].wasShot))
        {
            cellsToShoot.add(board.grid[target.y][target.x-1]);

        }
        if(board.isPointValid(target.x , target.y+1) && !(board.grid[target.y+1][target.x].wasShot))
        {
            cellsToShoot.add(board.grid[target.y+1][target.x]);

        }
        if(board.isPointValid(target.x , target.y-1) && !(board.grid[target.y-1][target.x].wasShot))
        {
            cellsToShoot.add(board.grid[target.y-1][target.x]);

        }

        cellToShoot = cellsToShoot.get(rand.nextInt(cellsToShoot.size()));


        return cellToShoot;
    }

    private void checkHorizotal()
    {
        //проверяет горизонтальность корабля
        if(cellsWithShip.get(0).y==cellsWithShip.get(1).y)
        {
            horizon=true;
        }



    }




    private Cell thinkModule(Board board)
    {
        int a=0;
        int b=0;
        int napravleniy = rand.nextInt(2);
        int diff=0;
        Cell target;

        int sx=cellsWithShip.get(0).x;
        int sxy=cellsWithShip.get(0).y;


        if (horizon)
        {
            a=1;
        }

        else
        {
            b=1;


        }


        if(napravleniy==0)
        {
            napravleniy = -1 ;
        }


        diff = getDiff(board, a, b, napravleniy, diff);

//проверка нужнаяя ли нам пустая точка с потенциальым кораблем  в одном из напрмавелний
        if(board.isPointValid(cellsWithShip.get(0).x+diff*napravleniy*a,cellsWithShip.get(0).y+diff*napravleniy*b)

                && board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].wasShot==false)
        {
            target=board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a];
        }

        else
        {//если нет то начинаем идти в другу сторону
            napravleniy*=-1;
            diff=0;

            diff = getDiff(board, a, b, napravleniy, diff);
            target=board.grid[cellsWithShip.get(0).x+diff*napravleniy*a][cellsWithShip.get(0).y+diff*napravleniy*b];
        }


        System.out.println(a+" "+b+" "+ diff);
        System.out.println(target.x+" "+ target.y);
        return target;
    }




    private int getDiff(Board board, int a, int b, int napravleniy, int diff)
    {
        //это ищет куда стрельнуть по вертикали или горризонтали нно только в одну сторону возвращяет поправкку к первому попаданию
        // если нашел что там удже (в той стороне есть выстрел ) завершает работу
        while (board.isPointValid(cellsWithShip.get(0).x+diff*napravleniy*a,cellsWithShip.get(0).y+diff*napravleniy*b)

                && board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].wasShot==true
                &&board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].ship!=null )

        {
            Cell sh=board.getCell(cellsWithShip.get(0).y+diff*napravleniy*b,cellsWithShip.get(0).x+diff*napravleniy*a);
            int ex1= board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].x;
            int ex2=board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].y;
            System.out.println("x- "+ex1+" y= "+ex2 );
            System.out.println(board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].wasShot);
            System.out.println(board.grid[cellsWithShip.get(0).y+diff*napravleniy*b][cellsWithShip.get(0).x+diff*napravleniy*a].ship);
            diff+=1;
        }
        return diff;
    }

    private void checkADd(Cell target)
    {
        //сей модуль добавляет ячейки кораблей с попаданиями
        if (target.ship !=null)
        {
            cellsWithShip.add(target);
            keepFinding=true;
        }
        /*а это при добавлении второго должен запустить проверку на горизонтальность*/
        if(cellsWithShip.size()==2)
        {
            checkHorizotal();
        }
    }

    public void setToZero()

    {
        cellsWithShip = new ArrayList<Cell>();

        random = new Random();
        horizon = false;


        keepFinding = false;


        startNoCheck =true;
        rand = new Random();
    }


}