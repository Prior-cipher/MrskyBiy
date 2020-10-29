package com.company;

import java.util.*;

public class AAAAAAIIIII
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
        do{
            x = random.nextInt(10);
            y = random.nextInt(10);

            cell = board.getCell(x, y);

        }
        while(!cell.wasShot);


        return cell;


    }


    public Cell startThink(Board board)
    {
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


        return new Cell(1,1,new Board(false));
    }



    private Cell check(Cell target,Board board)
    {

        //данный модуль срабатывает до тех пор пока мы не попадем еще раз
        //выбирает случайную точку вокруг первого попадания
        ArrayList<Cell> cellsToShoot = new ArrayList<Cell>();
        Cell cellToShoot;
        if(board.isPointValid(target.x + 1, target.y) && !target.wasShot)
        {
            cellsToShoot.add(board.grid[target.x+1][target.y]);

        }
        if(board.isPointValid(target.x-1 , target.y) && !target.wasShot)
        {
            cellsToShoot.add(board.grid[target.x-1][target.y]);

        }
        if(board.isPointValid(target.x , target.y+1) && !target.wasShot){
            cellsToShoot.add(board.grid[target.x][target.y+1]);

        }
        if(board.isPointValid(target.x , target.y-1) && !target.wasShot){
            cellsToShoot.add(board.grid[target.x][target.y-1]);

        }

        cellToShoot = cellsToShoot.get(rand.nextInt(cellsToShoot.size()));

        checkADd(cellToShoot);
        return cellToShoot;
    }

    private void checkHorizotal()
    {
        //проверяет горизонтальность корабля
        if(cellsWithShip.get(0).x==cellsWithShip.get(1).x)
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



        if (horizon==true)
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

                && board.grid[cellsWithShip.get(0).x+diff*napravleniy*a][cellsWithShip.get(0).y+diff*napravleniy*b].wasShot==false)
        {
            target=board.grid[cellsWithShip.get(0).x+diff*napravleniy*a][cellsWithShip.get(0).y+diff*napravleniy*b];
        }

        else
            {//если нет то начинаем идти в другу сторону
                napravleniy*=-1;
                diff=1;

                diff = getDiff(board, a, b, napravleniy, diff);
                target=board.grid[cellsWithShip.get(0).x+diff*napravleniy*a][cellsWithShip.get(0).y+diff*napravleniy*b];
            }




        return target;
    }



    private int getDiff(Board board, int a, int b, int napravleniy, int diff)
    {
        //это ищет куда стрельнуть по вертикали или горризонтали нно только в одну сторону возвращяет поправкку к первому попаданию
        // если нашел что там удже (в той стороне есть выстрел ) завершает работу
        while (board.isPointValid(cellsWithShip.get(0).x+diff*napravleniy*a,cellsWithShip.get(0).y+diff*napravleniy*b)

                && board.grid[cellsWithShip.get(0).x+diff*napravleniy*a][cellsWithShip.get(0).y+diff*napravleniy*b].wasShot==true)

        {
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
