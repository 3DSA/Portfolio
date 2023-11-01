package com.company;
//include utilites, land and houses, go to jail, and jail
//taxes in main file
public class Property extends SquareType{
    private int price;
    private String color; //land
    private int jailtime; // jail
    private Hotel hotels;
    public Property(String a, String b, int c) {//constructor for Land
        super(a, "Land");
        price = c;
        color = b;
        hotels = new Hotel(0, b);
    }
    public Property(String a, int c) {       //constructor for Utility
        super(a, "Utility");
        price = c;
    }
    public Property(String a) {     //constructor for jail && Go && Chance && Free Parking
        super(a, a);
        if(a.equals("Jail")) {
            jailtime = 2;
        }
    }
    public Property(int a) { //Luxury Tax
        super("Luxury Tax","Luxury Tax");
        price = a;
    }
    public Property() {     //constructor for go to jail
        super("Go to Jail", " Go to Jail");
    }
    public void setPrice(int a) { //sets buying price
        price = a;
    }
    public int getPrice() { //gets buying price
        return price;
    }
    public int getRent() {      //returns rent
        return (price/10 + (hotels.getNum_houses() * (price/100) * 2) + (hotels.getHotelcount() * (price/100) * 10));
    }
    public Hotel getHotels() {
        return hotels;
    }
    public void Mortgage(player_pieces a) {
        a.setMoney(a.getMoney()+price);
        price = 0;
        System.out.println(getName() + " has been mortgaged!");
    }
    public void setColor(String a) {    //sets color
        color = a;
    }
    public String getColor() {      //returns color
        return color;
    }
    public void setJailtime(int a) {    //sets jailtime
        jailtime = a;
    }
    public int getJailtime() {      //returns jailtime
        return jailtime;
    }

}