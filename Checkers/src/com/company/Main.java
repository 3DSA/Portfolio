package com.company;
import java.util.*;
public class Main {
    //game over method
    public static void gameoverchecker(int pieces, int exiter, int playernum) {
        if(pieces == 0) {
            exiter = 0;
            System.out.println("Player " + playernum + " won the game!!!");
        }
    }
    //make king method
    public static void kingchecker(pieces b) {
        if(b.getColor().equals("B") && b.getX() == 1) {
            b.makeKing(true);
            b.setColor("Q");
        }
        else if(b.getColor().equals("W") && b.getX() == 8) {
            b.makeKing(true);
            b.setColor("P");
        }
    }
    //will have to ask user if they want to double kill
    public static boolean doublekillchecker(int rower, int columner, pieces[][] b, String playercolor) {
        int counterifworks = 0;
        String kingcolor = "T";
        if(playercolor.equals("B")) {
            kingcolor = "Q";
        }
        else if(playercolor.equals("W")) {
            kingcolor = "P";
        }
        //checks diagonally left back
        if((b[rower][columner].kingStatus() == true || b[rower][columner].getColor().equals("B")) && (rower-2 >= 0 && columner-2 >= 0) && (rower-2 < 7 && columner-2 <= 7) && b[rower-1][columner-1].getColor().equals(" ") && !b[rower-1][columner-1].getColor().equals(playercolor) && b[rower-2][columner-2].getColor().equals(" ") &&
                !b[rower-1][columner-1].getColor().equals(kingcolor)) {
            System.out.print("Can kill diagonally left back. Enter (DLU) if you wish to do so. ");
            ++counterifworks;
        }
        //checks diagonally right back
        if((b[rower][columner].kingStatus() == true || b[rower][columner].getColor().equals("B")) && (rower-2 >= 0 && columner+2 >= 0) && (rower-2 <= 7 && columner+2 <= 7) && !b[rower-1][columner+1].getColor().equals( " ") && !b[rower-1][columner+1].getColor().equals(playercolor) &&
               b[rower-2][columner+2].getColor().equals(" ") && !b[rower-1][columner+1].getColor().equals(kingcolor)) {
            System.out.print("Can kill diagonally right back. Enter (DRU) if you wish to do so. ");
            ++counterifworks;
        }
        //checks diagonally left up
        if((b[rower][columner].kingStatus() == true || !b[rower][columner].getColor().equals("W")) && (rower+2 >= 0 && columner-2 >= 0) && (rower+2 <= 7 && columner-2 <= 7)  && !b[rower+1][columner-1].getColor().equals(kingcolor) && !b[rower+1][columner-1].getColor().equals(" ") && !b[rower+1][columner-1].getColor().equals(playercolor) &&
                b[rower+2][columner-2].getColor().equals(" ")) {
            System.out.print("Can kill diagonally left up. Enter (DLD) if you wish to do so. ");
            ++counterifworks;
        }
        //checks diagonally right up
        if((b[rower][columner].kingStatus() == true || b[rower][columner].getColor().equals("W")) && (rower+2 >= 0 && columner+2 >= 0) && (rower+2 <= 7 && columner+2 <= 7)  && !b[rower+1][columner+1].getColor().equals(kingcolor) && !b[rower+1][columner+1].getColor().equals(" ") && !b[rower+1][columner+1].getColor().equals(playercolor) &&
                b[rower+2][columner+2].getColor().equals(" ")) {
            System.out.print("Can kill diagonally right up. Enter (DRD) if you wish to do so. ");
            ++counterifworks;
        }
        if(counterifworks > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void movement(String direction, pieces[][] b, int rower, int columner, String playercolor, int piecesleft) {
        Scanner IN = new Scanner(System.in);
        String kingcolor = "T";
        if(!playercolor.equals("B")) {
            kingcolor = "Q";
        }
        else if(playercolor.equals("W")) {
            kingcolor = "P";
        }
        if(direction.equals("no")) {
            return;
        }
        else if((direction.equals("DLD") || direction.equals("DRD")) && b[rower][columner].kingStatus() == false && b[rower][columner].getColor().equals("B")) {
            System.out.println("This piece cannot move this way!");
        }
        else if((direction.equals("DLU") || direction.equals("DRU")) && b[rower][columner].kingStatus() == false && b[rower][columner].getColor().equals("W")) {
            System.out.println("This piece cannot move this way!");
        }
        //Checking for diagonally left up
        else if(direction.equals("DLU")) {
            if(rower-1 < 0 || columner-1 < 0 || rower-1 > 7 || columner-1 > 7) {
                System.out.println("This piece cannot move this way!");
            }
            else if(b[rower-1][columner-1].getColor().equals(" ")) {
                b[rower-1][columner-1].setColor(b[rower][columner].getColor());
                b[rower-1][columner-1].makeKing(b[rower][columner].kingStatus());
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
                kingchecker(b[rower-1][columner-1]);
            }
            else if((rower-2 >= 0 && columner-2 >= 0) && (rower-2 <= 7 && columner-2 <= 7) && !b[rower-1][columner-1].getColor().equals(kingcolor) && !b[rower-1][columner-1].getColor().equals(" ") && !b[rower-1][columner-1].getColor().equals(playercolor) &&
                    !b[rower-2][columner-2].getColor().equals(playercolor) && !b[rower-2][columner-2].getColor().equals(" ")) {
                b[rower-2][columner-2].setColor(b[rower][columner].getColor());
                b[rower-2][columner-2].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower-2][columner-2]);
                b[rower-1][columner-1].setColor(" ");
                b[rower-1][columner-1].makeKing(false);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
                --piecesleft;
                //doublekillchecker
                if(doublekillchecker(rower-2, columner-2, b, playercolor) == true) {
                    direction = IN.nextLine();
                    if(!direction.equals("no")) {
                        System.out.println("   1 2 3 4 5 6 7 8 "); //labels the columns for player
                        for(int i = 0; i < 8; ++i) {
                            System.out.print(i+1 + " "); //labels the rows for player
                            for(int j = 0; j < 8; ++j) {
                                System.out.print("|" + b[i][j].getColor());
                            }
                            System.out.println("|");
                        }
                    }
                    movement(direction, b, rower-2, columner-2,playercolor, piecesleft);
                }
                else {
                    System.out.println("Double kill not possible. ");
                }
            }
            else {
                System.out.println("This piece cannot move this way!");
            }
        }
        //Checking for diagonally right up
        else if(direction.equals("DRU")) {
            //checks for invalid double kill
            if(rower-1 < 0 || columner+1 < 0 || rower-1 > 7 || columner+1 > 7) {
                System.out.println("This piece cannot move this way!");
            }
            else if(b[rower-1][columner+1].getColor().equals(" ")) {
                b[rower-1][columner+1].setColor(b[rower][columner].getColor());
                b[rower-1][columner+1].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower-1][columner+1]);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
            }
            //checks for double kill using recursive
            else if((rower-2 >= 0 && columner+2 >= 0) && (rower-2 <= 7 && columner+2 <= 7) && !b[rower-1][columner+1].getColor().equals(kingcolor) && !b[rower-1][columner+1].getColor().equals(" ") && !b[rower-1][columner+1].getColor().equals(playercolor) &&
                    b[rower-2][columner+2].getColor().equals(" ")) {
                b[rower-2][columner+2].setColor(b[rower][columner].getColor());
                b[rower-2][columner+2].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower-2][columner+2]);
                b[rower-1][columner+1].setColor(" ");
                b[rower-1][columner+1].makeKing(false);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
                --piecesleft;
                //doublekillchecker
                if(doublekillchecker(rower-2, columner+2, b, playercolor) == true) {
                    direction = IN.nextLine();
                    if(!direction.equals("no")) {
                        System.out.println("   1 2 3 4 5 6 7 8 "); //labels the columns for player
                        for(int i = 0; i < 8; ++i) {
                            System.out.print(i+1 + " "); //labels the rows for player
                            for(int j = 0; j < 8; ++j) {
                                System.out.print("|" + b[i][j].getColor());
                            }
                            System.out.println("|");
                        }
                    }
                    movement(direction, b, rower-2, columner+2,playercolor, piecesleft);
                }
                else {
                    System.out.println("Double kill not possible. ");
                }
            }
            else {
                System.out.println("This piece cannot move this way!");
            }

        }
        //diagonally left down
        else if(direction.equals("DLD")) {
            if(rower+1 < 0 || columner-1 < 0 || rower+1 > 7 || columner-1 > 7) {
                System.out.println("This piece cannot move this way!");
            }
            else if(b[rower+1][columner-1].getColor().equals(" ")) {
                b[rower+1][columner-1].setColor(b[rower][columner].getColor());
                b[rower+1][columner-1].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower+1][columner-1]);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
            }
            else if((rower+2 >= 0 && columner-2 >= 0) && (rower+2 <= 7 && columner-2 <= 7) && !b[rower+1][columner-1].getColor().equals(kingcolor) && !b[rower+1][columner-1].getColor().equals(" ") && !b[rower+1][columner-1].getColor().equals(playercolor) &&
                    !b[rower+2][columner-2].getColor().equals(" ")) {
                b[rower+2][columner-2].setColor(b[rower][columner].getColor());
                b[rower+2][columner-2].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower+2][columner-2]);
                b[rower+1][columner-1].setColor(" ");
                b[rower+1][columner-1].makeKing(false);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
                --piecesleft;
                //doublekillchecker
                if(doublekillchecker(rower+2, columner-2, b, playercolor) == true) {
                    direction = IN.nextLine();
                    if(!direction.equals("no")) {
                        System.out.println("   1 2 3 4 5 6 7 8 "); //labels the columns for player
                        for(int i = 0; i < 8; ++i) {
                            System.out.print(i+1 + " "); //labels the rows for player
                            for(int j = 0; j < 8; ++j) {
                                System.out.print("|" + b[i][j].getColor());
                            }
                            System.out.println("|");
                        }
                    }
                    movement(direction, b, rower+2, columner-2,playercolor, piecesleft);
                }
                else {
                    System.out.println("Double kill not possible. ");
                }
            }
            else {
                System.out.println("This piece cannot move this way!");
            }
        }
        //diagonally right down
        else if(direction.equals("DRD")) {
            if(rower+1 < 0 || columner+1 < 0 || rower+1 > 7 || columner+1 > 7) {
                System.out.println("This piece cannot move this way!");
            }
            else if(b[rower+1][columner+1].getColor().equals(" ")) {
                b[rower+1][columner+1].setColor(b[rower][columner].getColor());
                b[rower+1][columner+1].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower+1][columner+1]);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
            }
            else if((rower+2 >= 0 && columner+2 >= 0) && (rower+2 <= 7 && columner+2 <= 7) && !b[rower+1][columner+1].getColor().equals(kingcolor) && !b[rower+1][columner+1].getColor().equals(" ") && !b[rower+1][columner+1].getColor().equals(playercolor) &&
                    !b[rower+2][columner+2].getColor().equals(" ")) {
                b[rower+2][columner+2].setColor(b[rower][columner].getColor());
                b[rower+2][columner+2].makeKing(b[rower][columner].kingStatus());
                kingchecker(b[rower+2][columner+2]);
                b[rower+1][columner+1].setColor(" ");
                b[rower+1][columner+1].makeKing(false);
                b[rower][columner].setColor(" ");
                b[rower][columner].makeKing(false);
                --piecesleft;
                //doublekillchecker
                if(doublekillchecker(rower+2, columner+2, b, playercolor) == true) {
                    direction = IN.nextLine();
                    if(!direction.equals("no")) {
                        System.out.println("   1 2 3 4 5 6 7 8 "); //labels the columns for player
                        for(int i = 0; i < 8; ++i) {
                            System.out.print(i+1 + " "); //labels the rows for player
                            for(int j = 0; j < 8; ++j) {
                                System.out.print("|" + b[i][j].getColor());
                            }
                            System.out.println("|");
                        }
                    }
                    movement(direction, b, rower+2, columner+2,playercolor, piecesleft);
                }
                else {
                    System.out.println("Double kill not possible. ");
                }
            }
            else {
                System.out.println("This piece cannot move this way!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner IN = new Scanner(System.in);
        pieces[][] board = new pieces[8][8];
        //creates board for checkers
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(i<=2 && (((i % 2 == 0) && (j % 2 != 0)) || ((i % 2 != 0) && (j % 2 == 0)))) {    //creates white pieces
                    pieces piece = new pieces(i + 1, j + 1, false, "W");
                    board[i][j] = piece;
                }
                else if(i>=5 && (((i % 2 == 0) && (j % 2 != 0)) || ((i % 2 != 0) && (j % 2 == 0)))) { //creates black pieces
                    pieces piece = new pieces((i + 1), (j + 1), false, "B");
                    board[i][j] = piece;
                }
                else {      //creates blank spaces
                    pieces piece = new pieces(i + 1, j + 1, false, " ");
                    board[i][j] = piece;
                }
            }
        }
        //tells players which pieces to play with
        System.out.println("Player 1 plays with the black pieces and player 2 plays with the white pieces.The White king is the letter P and the Black king is the letter Q.");
        int exiter = 1;
        int player = 0;
        int whitepiecesleft = 12;
        int blackpiecesleft = 12;
        //exiter used end the game if a person wins
        while(exiter != 0) {
            String colorplayer = null;
            if(exiter % 2 != 0) {   //used to determine which player's turn it is
                player = 1;
            }
            else {
                player = 2;
            }
            if(player == 1) {      //determine the color the player should use
                colorplayer = "B";
            }
            else {
                colorplayer = "W";
            }
            String kingcolor = null;
            if(player == 1) {      //determine the color the player should use
                kingcolor = "Q";
            }
            else {
                kingcolor = "P";
            }
            ++exiter;
            boolean therightpiece = false;
            int row = 0;
            int column = 0;
            String direction = null;
            while(therightpiece == false) {
                System.out.println("   1 2 3 4 5 6 7 8 "); //labels the columns for player
                for(int i = 0; i < 8; ++i) {
                    System.out.print(i+1 + " "); //labels the rows for player
                    for(int j = 0; j < 8; ++j) {
                        System.out.print("|" + board[i][j].getColor());
                    }
                    System.out.println("|");
                }
                //makes user enter rows and columns of desired piece to move
                System.out.print("Which piece does player " + player + " want to move(Please enter row first and the column next separated by a space): ");
                row = IN.nextInt()-1;
                column = IN.nextInt()-1;
                IN.nextLine();
                if(row > 7 || column > 7 || row < 0 || column < 0) { //checks out-of-bounds error
                    System.out.println("You chose a piece out of the bounds! Please enter numbers 1-8 for rows and columns.");
                }
                else if(!board[row][column].getColor().equals(colorplayer) && !board[row][column].getColor().equals(kingcolor)) { //checks if player moved correct colored piece
                    System.out.println("You chose the wrong colored piece! Pick again!!!");
                }
                else { //exits out of loop
                    if(board[row][column].kingStatus() == false) {
                        //checks whether piece is king or not
                        if(colorplayer.equals("W")) {
                            System.out.print("Does player " + player + " want to move the piece diagonally(left) down or diagonally(right) down. Type in " + "\"DRD\"" + " for moving it diagonally right down and type in " +
                                    "\"DLD\"" + " for moving diagonally left down: ");
                            direction = IN.nextLine();
                            movement(direction, board, row, column, colorplayer, blackpiecesleft);
                            if(board[row][column].getColor().equals(" ")) {
                                therightpiece = true;
                            }
                            else {
                                therightpiece = false;
                            }
                            gameoverchecker(blackpiecesleft,exiter, player);
                        }
                        else if(colorplayer.equals("B")) {
                            System.out.print("Does player " + player + " want to move the piece diagonally(left) up or diagonally(right) up. Type in " + "\"DRU\"" + " for moving it diagonally right up and type in " +
                                    "\"DLU\"" + " for moving diagonally left up: ");
                            direction = IN.nextLine();
                            movement(direction, board, row, column, colorplayer, whitepiecesleft);
                            if(board[row][column].getColor().equals(" ")) {
                                therightpiece = true;
                            }
                            else {
                                therightpiece = false;
                            }
                            gameoverchecker(whitepiecesleft,exiter, player);
                        }
                    }
                    else {
                        if(kingcolor == "P") {
                            System.out.print("Does player " + player + " want to move the king piece diagonally(left) down,diagonally(right) down, diagonally(left) up, or diagonally(right) up. Type in " + "\"DRU\"" + "for moving it diagonally right up, type in " + "\"DLU\"" +
                                    " for moving diagonally left up,type in " + "\"DLD\"" + " for moving it diagonally(left) down, and type in " + "\"DRD\"" + "for moving the piece diagonally right down: ");
                            direction = IN.nextLine();
                            movement(direction, board, row, column, colorplayer, blackpiecesleft);
                            if(board[row][column].getColor().equals(" ")) {
                                therightpiece = true;
                            }
                            else {
                                therightpiece = false;
                            }
                            gameoverchecker(blackpiecesleft,exiter, player);
                        }
                        else if(kingcolor == "Q") {
                            System.out.print("Does player " + player + " want to move the king piece diagonally(left) down,diagonally(right) down, diagonally(left) up, or diagonally(right) up. Type in " + "\"DRU\"" + "for moving it diagonally right up, type in " + "\"DLU\"" +
                                    " for moving diagonally left up,type in " + "\"DLD\"" + " for moving it diagonally(left) down, and type in " + "\"DRD\"" + "for moving the piece diagonally right down: ");
                            direction = IN.nextLine();
                            movement(direction, board, row, column, colorplayer, whitepiecesleft);
                            if(board[row][column].getColor().equals(" ")) {
                                therightpiece = true;
                            }
                            else {
                                therightpiece = false;
                            }
                            gameoverchecker(whitepiecesleft,exiter, player);
                        }
                    }
                }
            }
        }
    }
}