package com.company;

public abstract class SquareType{
    //Can be made by person creating one class for properties, jail block, etc...
    private String name; //Name of the tile
    private String tileType; //eg. Utility / Taxes / Land
    private int owner; //To determine who owns it. 0 for neutral, 1 for player 1... etc.
    //Standard setters and getters
    public SquareType(String a, String b) {
        name = a;
        tileType = b;
        owner = 0;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getTileType(){
        return tileType;
    }
    public void setTileType(String tileType){
        this.tileType=tileType;
    }
    public int getOwner(){
        return owner;
    }
    public void setOwner(int owner){
        this.owner=owner;
    }
}
