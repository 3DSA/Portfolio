package com.company;

public class House {
    private int num_houses;
    private int house_price;
    public House(int a, String b) {
        num_houses = a;
        if(b.equals("Brown")) {
            house_price = 50;
        }
        else if(b.equals("Red")) {
            house_price = 100;
        }
        else if(b.equals("Yellow")) {
            house_price = 150;
        }
        else if(b.equals("Blue")) {
            house_price = 200;
        }
    }
    public int getNum_houses() {    //returns number of houses
        return num_houses;
    }
    public void setNum_houses(int a) {    //sets number of houses
        num_houses = a;
    }
    public void setHouse_price(int a) {    //sets the price of the house
        house_price = a;
    }
    public void setHouse_priceString(String b) {    //sets the price of the house
        if(b.equals("Brown")) {
            house_price = 50;
        }
        else if(b.equals("Red")) {
            house_price = 100;
        }
        else if(b.equals("Yellow")) {
            house_price = 150;
        }
        else if(b.equals("Blue")) {
            house_price = 200;
        }
    }
    public int getHouse_price() {    //gets the price of the house
        return house_price;
    }
}
