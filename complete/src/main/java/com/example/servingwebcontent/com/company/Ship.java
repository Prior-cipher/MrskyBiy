package com.example.servingwebcontent.com.company;

public class Ship {
    public int type;
    public boolean vertical = true;
    public int health;

    public Ship(int type, boolean vertical){
        this.type = type;
        this.vertical = vertical;
        health = type;
    }

    public void hit(){
        health--;
    }
    public boolean isAlive(){
        return health > 0;
    }
}
