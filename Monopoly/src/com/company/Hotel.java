package com.company;
public class Hotel extends House{
    private int hotelcount;
    private int hotelprice;
   public Hotel(int a, String b) {
       super(a,b);
       if(getNum_houses() >=4) {
           hotelcount = getNum_houses()/4;
           setNum_houses(getNum_houses() - (hotelcount*4));
       }
       hotelprice = getHouse_price() * 4;
    }
    public void setHotelcount() {
       if(getNum_houses() >=4) {
           hotelcount = getNum_houses()/4;
           setNum_houses(getNum_houses() - (hotelcount*4));
       }
    }
    public void setHotelcountwithHouse() {
        if(getNum_houses() >=4) {
            hotelcount = getNum_houses()/4;
            setNum_houses(getNum_houses() - (hotelcount*4));
        }
    }
    public void setHotelcount(int a) {
       hotelcount = a;
    }
    public int getHotelcount() {
        return hotelcount;
    }
    public void setHotelprice(int a) {
        hotelprice = a;
    }
    public int getHotelprice() {
        return hotelprice;
    }
}
