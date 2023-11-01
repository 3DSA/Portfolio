//Dhruv Susheelkar
//Mr. Jan
//AP Compsci P: 2
package com.company;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int codebreaker = 1;
        int[] browncolor_count = new int[2];
        int[] redcolor_count = new int[2];
        int[] yellowcolor_count = new int[2];
        int[] bluecolor_count = new int[2];
        int[] utility_count = new int[2];
        Scanner IN = new Scanner(System.in);
        System.out.print("Please enter the name of player 1: ");
        String name1 = IN.nextLine();
        System.out.print("Please enter the name of player 2: ");
        String name2 = IN.nextLine();
        player_pieces player1 = new player_pieces(name1, 1);
        player_pieces player2 = new player_pieces(name2, 2);
        player_pieces[] players = new player_pieces[]{player1, player2};
        String[][] board_player1 = new String[9][9];
        String[][] board_player2 = new String[9][9];
        Property[][] board_properties = new Property[9][9];
        board_properties[8][8] = new Property("GO");
        board_properties[8][7] = new Property("Chance"); //Chance
        board_properties[8][6] = new Property("Euston Road", "Brown", 50); //Land
        board_properties[8][5] = new Property("Oxford Street", "Brown", 75);    //Land
        board_properties[8][4] = new Property("Liverpool St. Station", 250); //Utility
        board_properties[8][3] = new Property(200); //Luxury Tax
        board_properties[8][2] = new Property("Park Lane", "Brown", 100);    //Land
        board_properties[8][1] = new Property("Mayfair Street", "Brown", 125);    //Land
        board_properties[8][0] = new Property();    //Go to Jail
        board_properties[7][0] = new Property("Vine Street", "Red", 150);    //Land
        board_properties[6][0] = new Property("Coventry Street", "Red", 175);    //Land
        board_properties[5][0] = new Property("Leicester Square", "Red", 200); //Land
        board_properties[4][0] = new Property("MaryLebone Station", 250); //Utility
        board_properties[3][0] = new Property(200);  //Luxury Tax
        board_properties[2][0] = new Property("Chance"); //Chance
        board_properties[1][0] = new Property("Bow Street", "Red", 220); //Land
        board_properties[0][0] = new Property("Free Parking");
        board_properties[0][1] = new Property("Angel Islington", "Yellow", 240); //Land
        board_properties[0][2] = new Property("Trafalgar Square", "Yellow", 260); //Land
        board_properties[0][3] = new Property(200);  //Luxury Tax
        board_properties[0][4] = new Property("Fenchurch St. Station", 250); //Utility
        board_properties[0][5] = new Property("Fleet Street", "Yellow", 280); //Land
        board_properties[0][6] = new Property("Chance"); //Chance
        board_properties[0][7] = new Property("Old Kent Road", "Blue", 300); //Land
        board_properties[0][8] = new Property("Jail"); //Jail
        board_properties[1][8] = new Property("Whitehall", "Blue", 325); //Land
        board_properties[2][8] = new Property(200);  //Luxury Tax
        board_properties[3][8] = new Property("Pall Mall", "Blue", 350); //Land
        board_properties[4][8] = new Property("King Cross Station", 250); //Utility
        board_properties[5][8] = new Property("Chance"); //Chance
        board_properties[6][8] = new Property("Bond Street", "Blue", 375); //Land
        board_properties[7][8] = new Property("Boardwalk", "Blue", 400); //Land
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (i == 0 || i == 8 || j == 8 || j == 0) {
                    board_player1[i][j] = "_";
                    board_player2[i][j] = "_";
                }
            }
        }
        //starting point for both pieces
        board_player1[8][8] = "1";
        board_player2[8][8] = "2";
        int turnplayer = 0;
        int otherplayer = 0;
        while(codebreaker != 0) {
            if (codebreaker % 2 == 1) {
                turnplayer = 0;
                otherplayer = 1;
            } else {
                turnplayer = 1;
                otherplayer = 0;
            }
            System.out.println("___________________");
            if (turnplayer == 0) { //board for player 1 before die roll
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if (i == 0 || i == 8 || j == 8) {
                            System.out.print("|" + board_player1[i][j]);
                        } else if (j == 0) {
                            System.out.print("|" + board_player1[i][j] + "|");
                        } else if (i == 7 && j != 7) {
                            System.out.print("__");
                        } else if (i == 7 && j == 7) {
                            System.out.print("_");
                        } else if (j == 7) {
                            System.out.print(" ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    System.out.println("|");
                }
            } else {
                for (int i = 0; i < 9; ++i) {      //board for player 2 before die roll
                    for (int j = 0; j < 9; ++j) {
                        if (i == 0 || i == 8 || j == 8) {
                            System.out.print("|" + board_player2[i][j]);
                        } else if (j == 0) {
                            System.out.print("|" + board_player2[i][j] + "|");
                        } else if (i == 7 && j != 7) {
                            System.out.print("__");
                        } else if (i == 7 && j == 7) {
                            System.out.print("_");
                        } else if (j == 7) {
                            System.out.print(" ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    System.out.println("|");
                }

            }
            //finding player's piece
            int location_x = 0;
            int location_y = 0;
            if (turnplayer == 0) {
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if(i == 0 || j == 0 || i == 8 || j == 8) {
                            if (board_player1[i][j].equals("1")) {
                                location_x = i;
                                location_y = j;
                            }
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if(i == 0 || j == 0 || i == 8 || j == 8) {
                            if (board_player2[i][j].equals("2")) {
                                location_x = i;
                                location_y = j;
                            }
                        }
                    }
                }
            }
            int dice1 = (int) (Math.random() * 5) + 1;
            int dice2 = (int) (Math.random() * 5) + 1;
            System.out.print("Press \"ENTER\" twice to roll the dice: ");
            String die_roll_user = IN.nextLine();
            IN.nextLine();
            System.out.println(players[turnplayer].getName() + " rolled a " + dice1 + " and " + dice2 + "!");
            if (board_properties[location_x][location_y].getTileType().equals("Jail") && board_properties[location_x][location_y].getJailtime() != 0) {     //checks if user is in jail
                if (dice1 == dice2) {
                    System.out.println("You rolled a double. You are free!");
                    board_properties[location_x][location_y].setJailtime(0);
                } else {
                    board_properties[location_x][location_y].setJailtime(board_properties[location_x][location_y].getJailtime() - 1);
                    System.out.println("You have " + board_properties[location_x][location_y].getJailtime() + " turn left to serve...");
                }
            }
            else {
                int total_roll = dice1 + dice2;
                if(turnplayer == 0) {
                    board_player1[location_x][location_y] = "_";
                }
                else {
                    board_player2[location_x][location_y] = "_";
                }
                while(total_roll > 0) {     //does movement on board
                    if(location_x == 7 && location_y == 8) {
                        ++location_x;
                        players[turnplayer].setMoney(players[turnplayer].getMoney()+200);
                        System.out.println(players[turnplayer].getName() + " has recieved " + " $200 from passsing GO!");
                    }
                    else if(location_x == 8 && location_y != 0) {
                        --location_y;
                    }
                    else if(location_x != 0 && location_y == 0) {
                        --location_x;
                    }
                    else if(location_x == 0 && location_y != 8) {
                        ++location_y;
                    }
                    else if(location_x != 8 && location_y == 8) {
                        ++location_x;
                    }
                    --total_roll;
                }
                if(turnplayer == 0) {       //location on board after roll
                    board_player1[location_x][location_y] = "1";
                }
                else {
                    board_player2[location_x][location_y] = "2";
                }
                System.out.println("___________________");
                if (turnplayer == 0) { //board for player 1 after die roll
                    for (int i = 0; i < 9; ++i) {
                        for (int j = 0; j < 9; ++j) {
                            if (i == 0 || i == 8 || j == 8) {
                                System.out.print("|" + board_player1[i][j]);
                            } else if (j == 0) {
                                System.out.print("|" + board_player1[i][j] + "|");
                            } else if (i == 7 && j != 7) {
                                System.out.print("__");
                            } else if (i == 7 && j == 7) {
                                System.out.print("_");
                            } else if (j == 7) {
                                System.out.print(" ");
                            } else {
                                System.out.print("  ");
                            }
                        }
                        System.out.println("|");
                    }
                }
                else {
                    for (int i = 0; i < 9; ++i) {       //board for player 2 after die roll
                        for (int j = 0; j < 9; ++j) {
                            if (i == 0 || i == 8 || j == 8) {
                                System.out.print("|" + board_player2[i][j]);
                            } else if (j == 0) {
                                System.out.print("|" + board_player2[i][j] + "|");
                            } else if (i == 7 && j != 7) {
                                System.out.print("__");
                            } else if (i == 7 && j == 7) {
                                System.out.print("_");
                            } else if (j == 7) {
                                System.out.print(" ");
                            } else {
                                System.out.print("  ");
                            }
                        }
                        System.out.println("|");
                    }
                }
                //Code for Landed Property
                System.out.println(players[turnplayer].getName() + " has landed on " + board_properties[location_x][location_y].getName() + ".");
                String user_decision = "_";
                if(board_properties[location_x][location_y].getOwner() == turnplayer+1) {     //code if you land on tile you own
                    int house_input = 0;
                    if(board_properties[location_x][location_y].getTileType().equals("Land")) {
                        if(board_properties[location_x][location_y].getColor().equals("Brown") && browncolor_count[turnplayer] == 4) {
                            System.out.print("Do you want to add houses(Answer with \"Yes\" or \"No\")? ");
                            user_decision = IN.nextLine();
                            int input_error_user = 1;
                            if(user_decision.equals("Yes")) { //checks if user wants to add houses
                                while(input_error_user == 1) {      //loops if user does not input correct value
                                    System.out.print(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount()
                                            + " hotels. How many houses do you want to add? You can add up to $" + (players[turnplayer].getMoney()/board_properties[location_x][location_y].getHotels().getHouse_price()) + ".");
                                    house_input = IN.nextInt();
                                    IN.nextLine();
                                    if(players[turnplayer].getMoney() >= (board_properties[location_x][location_y].getHotels().getHouse_price() * 4)) {
                                        players[turnplayer].setMoney(players[turnplayer].getMoney() - (board_properties[location_x][location_y].getHotels().getHouse_price()*4));
                                        board_properties[location_x][location_y].getHotels().setNum_houses(house_input);
                                        board_properties[location_x][location_y].getHotels().setHotelcountwithHouse();
                                        System.out.println(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount() +
                                                " hotels now on " + board_properties[location_x][location_y].getName() + ".");
                                        input_error_user = 0;
                                    }
                                    else {
                                        System.out.println(players[turnplayer].getName() + " doesn't have enough money to add that many houses.");
                                    }
                                }
                            }
                            else {
                                System.out.println(players[turnplayer].getName() + " already owns this property.");
                            }
                        }
                        else if(board_properties[location_x][location_y].getColor().equals("Red") && redcolor_count[turnplayer] == 4) {
                            if(board_properties[location_x][location_y].getColor().equals("Red") && redcolor_count[turnplayer] == 4) {
                                System.out.print("Do you want to add houses(Answer with \"Yes\" or \"No\")? ");
                                user_decision = IN.nextLine();
                                int input_error_user = 1;
                                if(user_decision.equals("Yes")) { //checks if user wants to add houses
                                    while(input_error_user == 1) {      //loops if user does not input correct value
                                        System.out.print(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount()
                                                + " hotels. How many houses do you want to add? You can add up to $" + (players[turnplayer].getMoney()/board_properties[location_x][location_y].getHotels().getHouse_price()) + ".");
                                        house_input = IN.nextInt();
                                        IN.nextLine();
                                        if(players[turnplayer].getMoney() >= (board_properties[location_x][location_y].getHotels().getHouse_price() * 4)) {
                                            players[turnplayer].setMoney(players[turnplayer].getMoney() - (board_properties[location_x][location_y].getHotels().getHouse_price()*4));
                                            board_properties[location_x][location_y].getHotels().setNum_houses(house_input);
                                            board_properties[location_x][location_y].getHotels().setHotelcountwithHouse();
                                            System.out.println(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount() +
                                                    " hotels now on " + board_properties[location_x][location_y].getName() + ".");
                                            input_error_user = 0;
                                        }
                                        else {
                                            System.out.println(players[turnplayer].getName() + " doesn't have enough money to add that many houses.");
                                        }
                                    }
                                }
                                else {
                                    System.out.println(players[turnplayer].getName() + " already owns this property.");
                                }
                            }
                        }
                        else if(board_properties[location_x][location_y].getColor().equals("Yellow") && yellowcolor_count[turnplayer] == 4) {
                            if(board_properties[location_x][location_y].getColor().equals("Yellow") && yellowcolor_count[turnplayer] == 4) {
                                System.out.print("Do you want to add houses(Answer with \"Yes\" or \"No\")? ");
                                user_decision = IN.nextLine();
                                int input_error_user = 1;
                                if(user_decision.equals("Yes")) { //checks if user wants to add houses
                                    while(input_error_user == 1) {      //loops if user does not input correct value
                                        System.out.print(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount()
                                                + " hotels. How many houses do you want to add? You can add up to $" + (players[turnplayer].getMoney()/board_properties[location_x][location_y].getHotels().getHouse_price()) + ".");
                                        house_input = IN.nextInt();
                                        IN.nextLine();
                                        if(players[turnplayer].getMoney() >= (board_properties[location_x][location_y].getHotels().getHouse_price() * 4)) {
                                            players[turnplayer].setMoney(players[turnplayer].getMoney() - (board_properties[location_x][location_y].getHotels().getHouse_price()*4));
                                            board_properties[location_x][location_y].getHotels().setNum_houses(house_input);
                                            board_properties[location_x][location_y].getHotels().setHotelcountwithHouse();
                                            System.out.println(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount() +
                                                    " hotels now on " + board_properties[location_x][location_y].getName() + ".");
                                            input_error_user = 0;
                                        }
                                        else {
                                            System.out.println(players[turnplayer].getName() + " doesn't have enough money to add that many houses.");
                                        }
                                    }
                                }
                                else {
                                    System.out.println(players[turnplayer].getName() + " already owns this property.");
                                }
                            }
                        }
                        else if(board_properties[location_x][location_y].getColor().equals("Blue") && bluecolor_count[turnplayer] == 4) {
                            if(board_properties[location_x][location_y].getColor().equals("Blue") && bluecolor_count[turnplayer] == 4) {
                                System.out.print("Do you want to add houses(Answer with \"Yes\" or \"No\")? ");
                                user_decision = IN.nextLine();
                                int input_error_user = 1;
                                if(user_decision.equals("Yes")) { //checks if user wants to add houses
                                    while(input_error_user == 1) {      //loops if user does not input correct value
                                        System.out.print(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount()
                                                + " hotels. How many houses do you want to add? You can add up to $" + (players[turnplayer].getMoney()/board_properties[location_x][location_y].getHotels().getHouse_price()) + ".");
                                        house_input = IN.nextInt();
                                        IN.nextLine();
                                        if(players[turnplayer].getMoney() >= (board_properties[location_x][location_y].getHotels().getHouse_price() * 4)) {
                                            players[turnplayer].setMoney(players[turnplayer].getMoney() - (board_properties[location_x][location_y].getHotels().getHouse_price()*4));
                                            board_properties[location_x][location_y].getHotels().setNum_houses(house_input);
                                            board_properties[location_x][location_y].getHotels().setHotelcountwithHouse();
                                            System.out.println(players[turnplayer].getName() + " has " + board_properties[location_x][location_y].getHotels().getNum_houses() +" houses " + board_properties[location_x][location_y].getHotels().getHotelcount() +
                                                    " hotels now on " + board_properties[location_x][location_y].getName() + ".");
                                            input_error_user = 0;
                                        }
                                        else {
                                            System.out.println(players[turnplayer].getName() + " doesn't have enough money to add that many houses.");
                                        }
                                    }
                                }
                                else {
                                    System.out.println(players[turnplayer].getName() + " already owns this property.");
                                }
                            }
                        }
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Utility")) {
                        System.out.println(players[turnplayer] + " already owns this property.");
                    }
                }
                else if(board_properties[location_x][location_y].getOwner() == 0) {     //code if you land on not owned tile
                    if(board_properties[location_x][location_y].getTileType().equals("Land")) {
                        System.out.print("Do you want to buy this property(Answer with \"Yes\" or \"No\")? ");
                        user_decision = IN.nextLine();
                        if(user_decision.equals("Yes")) {
                            if(players[turnplayer].getMoney() >= board_properties[location_x][location_y].getPrice()) {
                                players[turnplayer].setMoney(players[turnplayer].getMoney() - board_properties[location_x][location_y].getPrice());
                                board_properties[location_x][location_y].setOwner(turnplayer+1);
                                System.out.println(players[turnplayer].getName() + " has $" + players[turnplayer].getMoney() + " left.");
                                if(board_properties[location_x][location_y].getColor().equals("Brown")) {
                                   browncolor_count[turnplayer] +=1;
                                }
                                else if(board_properties[location_x][location_y].getColor().equals("Red")) {
                                    redcolor_count[turnplayer] +=1;
                                }
                                else if(board_properties[location_x][location_y].getColor().equals("Yellow")) {
                                    yellowcolor_count[turnplayer] +=1;
                                }
                                else if(board_properties[location_x][location_y].getColor().equals("Blue")) {
                                    bluecolor_count[turnplayer] +=1;
                                }
                            }
                            else {
                                System.out.println(players[turnplayer].getName() + "does not have enough money to buy the property...");
                            }
                        }
                        else {
                            System.out.println(players[turnplayer].getName() + " has decided to not buy the property.");
                        }
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Utility")) {
                        System.out.print("Do you want to buy this property(Answer with \"Yes\" or \"No\")? ");
                        user_decision = IN.nextLine();
                        if(user_decision.equals("Yes")) {
                            if(players[turnplayer].getMoney() >= board_properties[location_x][location_y].getPrice()) {
                                players[turnplayer].setMoney(players[turnplayer].getMoney() - board_properties[location_x][location_y].getPrice());
                                board_properties[location_x][location_y].setOwner(turnplayer+1);
                                System.out.println(players[turnplayer].getName() + " has $" + players[turnplayer].getMoney() + " left.");
                                utility_count[turnplayer] +=1;

                            }
                            else {
                                System.out.println(players[turnplayer].getName() + "does not have enough money to buy the property...");
                            }
                        }
                        else {
                            System.out.println(players[turnplayer].getName() + " has decided to not buy the property.");
                        }
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Luxury Tax")) {
                        players[turnplayer].setMoney(players[turnplayer].getMoney()-200);
                        System.out.println(players[turnplayer].getName() + " has $" + players[turnplayer].getMoney() + " left.");
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Free Parking")) {
                        System.out.println(players[turnplayer].getName() + " has free parking!");
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Chance")) {
                        players[turnplayer].ChanceCard();
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Jail")) {
                        System.out.println("You are in Jail now...");
                        board_properties[location_x][location_y].setJailtime(2);
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Go to Jail")) {
                        System.out.println("You have been caught shoplifting. You are being transported to jail...");
                        if(turnplayer == 0) {
                            board_player1[0][8] = "1";
                            board_player1[location_x][location_y] = "_";
                        }
                        else {
                            board_player2[0][8] = "2";
                            board_player2[location_x][location_y] = "_";
                        }
                        board_properties[0][8].setJailtime(2);
                    }
                }
                else if(board_properties[location_x][location_y].getOwner() == otherplayer+1) {   //code if you land on the other player's tile
                    if(board_properties[location_x][location_y].getTileType().equals("Land")) {
                        int loop_breaker = 0;       //breaks loop if user is able to pay rent
                        while(loop_breaker == 0) {
                            if(players[turnplayer].getMoney() >= board_properties[location_x][location_y].getRent()) {
                                players[turnplayer].setMoney(players[turnplayer].getMoney() - board_properties[location_x][location_y].getRent());
                                players[otherplayer].setMoney(players[turnplayer].getMoney() + board_properties[location_x][location_y].getRent());
                                loop_breaker = 1;       //breaks loop
                            }
                            else {  //Mortgage of property
                                System.out.println("You don't have enough money to pay rent. You will have to mortgage a property");
                                ArrayList<String> mortgage_prop_list = new ArrayList<String>();     //arraylist to be used to check in properties available to mortgage
                                ArrayList<Integer> mortgage_prop_locations = new ArrayList<Integer>();      //araylist to be used to check in locations of available properties
                                int mortgage_num_properties = 0;
                                for(int i = 0; i < 9; ++i) {        //used to check properties owned by user
                                    for(int j = 0; j < 9; ++j) {
                                        if(board_properties[i][j].getOwner() == turnplayer+1) {
                                            ++mortgage_num_properties;
                                        }
                                    }
                                }
                                if(mortgage_num_properties > 0) {
                                    System.out.print("You can mortgage these properties(");
                                    String user_input_property = "_";
                                    for(int i = 0; i < 9; ++i) {
                                        for(int j = 0; j < 9; ++j) {        //checks which properties can be mortgaged
                                            if(board_properties[i][j].getOwner() == turnplayer+1) {
                                                mortgage_prop_list.add(board_properties[i][j].getName());
                                                mortgage_prop_locations.add(i);
                                                mortgage_prop_locations.add(j);
                                                System.out.print(board_properties[i][j].getName() +", ");
                                            }
                                        }
                                    }
                                    boolean input_property_match = false;
                                    while(input_property_match == false) {    //loops if user did typo
                                        System.out.println("). Please type in the name of the property you want to mortgage(Please type the name properly: ");
                                        user_input_property = IN.nextLine();
                                        for(int i = 0; i < mortgage_prop_list.size(); ++i) {        //code to check for user's choice of mortgage
                                            if(mortgage_prop_list.get(i).equals(user_input_property)) {
                                                board_properties[mortgage_prop_locations.get(i*2)][mortgage_prop_locations.get((i*2)+1)].Mortgage(players[turnplayer]);
                                                input_property_match = true;
                                            }
                                        }
                                    }
                                }
                                else {      //win condition of player runs out of money
                                    System.out.println("You don't have enough money to pay the rent. " + players[otherplayer].getName() + " has won!");
                                    codebreaker = 0;
                                }
                            }
                        }
                    }
                    else if(board_properties[location_x][location_y].getTileType().equals("Utility")) {
                        int loop_breaker = 0;       //breaks loop if user is able to pay rent
                        while(loop_breaker == 0) {
                            if(players[turnplayer].getMoney() >= (board_properties[location_x][location_y].getRent()* utility_count[otherplayer])) {
                                players[turnplayer].setMoney(players[turnplayer].getMoney() - (board_properties[location_x][location_y].getRent() * utility_count[otherplayer]));
                                players[otherplayer].setMoney(players[turnplayer].getMoney() + (board_properties[location_x][location_y].getRent() * utility_count[otherplayer]));
                                loop_breaker = 1;
                            }
                            else {  //Mortgage of property
                                System.out.println("You don't have enough money to pay rent. You will have to mortgage a property");
                                ArrayList<String> mortgage_prop_list = new ArrayList<String>();     //arraylist to be used to check in properties available to mortgage
                                ArrayList<Integer> mortgage_prop_locations = new ArrayList<Integer>();      //araylist to be used to check in locations of available properties
                                int mortgage_num_properties = 0;
                                for(int i = 0; i < 9; ++i) {        //used to check properties owned by user
                                    for(int j = 0; j < 9; ++j) {
                                        if(board_properties[i][j].getOwner() == turnplayer+1) {
                                            ++mortgage_num_properties;
                                        }
                                    }
                                }
                                if(mortgage_num_properties > 0) {
                                    System.out.print("You can mortgage these properties(");
                                    String user_input_property = "_";
                                    for(int i = 0; i < 9; ++i) {
                                        for(int j = 0; j < 9; ++j) {        //checks which properties can be mortgaged
                                            if(board_properties[i][j].getOwner() == turnplayer+1) {
                                                mortgage_prop_list.add(board_properties[i][j].getName());
                                                mortgage_prop_locations.add(i);
                                                mortgage_prop_locations.add(j);
                                                System.out.print(board_properties[i][j].getName() +", ");
                                            }
                                        }
                                    }
                                    boolean input_property_match = false;
                                    while(input_property_match == false) {    //loops if user did typo
                                        System.out.println("). Please type in the name of the property you want to mortgage(Please type the name properly: ");
                                        user_input_property = IN.nextLine();
                                        for(int i = 0; i < mortgage_prop_list.size(); ++i) {        //code to check for user's choice of mortgage
                                            if(mortgage_prop_list.get(i).equals(user_input_property)) {
                                                board_properties[mortgage_prop_locations.get(i*2)][mortgage_prop_locations.get((i*2)+1)].Mortgage(players[turnplayer]);
                                                input_property_match = true;
                                            }
                                        }
                                    }
                                }
                                else {      //win condition of player runs out of money
                                    System.out.println("You don't have enough money to pay the rent. " + players[otherplayer].getName() + " has won!");
                                    codebreaker = 0;
                                }
                            }

                        }
                    }
                }
                if(dice1 != dice2 && codebreaker != 0){     //checks if the dice roll are the same
                    ++codebreaker;
                }
                else if(codebreaker != 0){
                    board_properties[0][8].setJailtime(0);
                    System.out.println("You earned a chance card!");
                    players[turnplayer].ChanceCard();
                    System.out.println("You get to roll again!");
                }
            }
        }
    }
}
