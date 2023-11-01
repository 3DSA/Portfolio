package com.company;

public class player_pieces implements Pieces {
    String name;
    int playernum;
    int money;
    public player_pieces(String a, int b) {
        name = a;
        playernum = b;
        money = 2000;
    }
    public String getName() {
        return name;
    }
    public void setOwner(String human) {
        name = human;
    }
    public void SetPlayernum(int a) {
        playernum = a;
    }
    public int getPlayernum() {
        return playernum;
    }
    public void setMoney(int a) {
        money = a;
    }
    public int getMoney() {
        return money;
    }
    public void ChanceCard() {
        int cardnum = (int) (Math.random() * 2) + 1;
        if(cardnum == 1) {
            System.out.print("Uh oh! Your are in debt. You have to pay $200!");
            money -= 200;
            System.out.println(" You have $" + getMoney() + " left.");
        }
        else if(cardnum == 2) {
            System.out.print("The stock you have invested in has gone up! You get $500. ");
            money += 500;
            System.out.println(" You have $" + getMoney() + " now.");
        }
        else if(cardnum == 3) {
            System.out.println("You found a deserted free parking space. You can rest up here! ");
        }
    }
}
