//Dhruv Susheelkar
//Mr.Jan
//Computer Science Period: 2
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
public class Race {
    public static void main(String[] args) throws FileNotFoundException{
        File DerbyData = new File("/Users/dhruvsusheelkar/Desktop/Derby.txt");
        Scanner IN = new Scanner(DerbyData);
        int contestants = IN.nextInt();
        IN.nextLine();
        int[][] heats = new int[contestants][5]; //creates array to store data for contestants in each hat
        int[] Totalpoints = new int[contestants]; //creates array for total points for each contestant
        int[][] rankings = new int[contestants][5]; //creates array to store data for rankings in each heat
        for(int i = 0; i < contestants; ++i) {      //gives each contestant a starting value of 0 points
            Totalpoints[i] = 0;
        }
        for(int i = 0; i < contestants; ++i) {
            IN.next();
            for(int j = 0; j < 5; ++j) {
                heats[i][j] = IN.nextInt();
            }
            IN.nextLine();
        }
        for(int i = 0; i < contestants; ++i) {
            IN.next();
            for(int j = 0; j < 5; ++j) {
                rankings[i][j] = IN.nextInt();
            }
            if(i+1 != contestants) {
                IN.nextLine();
            }
        }
        for(int i = 0; i < contestants; ++i) {  //assigns total points to each contestant
            for(int j = 0; j < 5; ++j) {
                Totalpoints[heats[i][j]-1] += rankings[i][j];
            }
        }
        //declaring values for lowest values
        int least1 = Totalpoints[0];
        int least2 = Totalpoints[0];
        int least3 = Totalpoints[0];
        int least4 = Totalpoints[0];
        int least5 = Totalpoints[0];
        //declaring each place
        String place1 = "";
        String place2 = "";
        String place3 = "";
        String place4 = "";
        String place5 = "";
        for(int i = 0; i < contestants; ++i) {      //loop used to find all the places
            if(least1 > Totalpoints[i]) {   //new first place
                least5 = least4;
                least4 = least3;
                least3 = least2;
                least2 = least1;
                least1 = Totalpoints[i];
                place5 = place4;
                place4 = place3;
                place3 = place2;
                place2 = place1;
                place1 = String.valueOf(i+1) + " ";
            }
            else if(least1 == Totalpoints[i]) { //tie for first place
                place1 += String.valueOf(i+1) + " ";
            }
            else if(least2 > Totalpoints[i]) {  //new second place
                least5 = least4;
                least4 = least3;
                least3 = least2;
                least2 = Totalpoints[i];
                place5 = place4;
                place4 = place3;
                place3 = place2;
                place2 = String.valueOf(i+1) + " ";
            }
            else if(least2 == Totalpoints[i]) { //tie for second place
                place2 += String.valueOf(i+1) + " ";
            }
            else if(least3 > Totalpoints[i]) {  //new third place
                least5 = least4;
                least4 = least3;
                least3 = Totalpoints[i];
                place5 = place4;
                place4 = place3;
                place3 = String.valueOf(i+1) + " ";
            }
            else if(least3 == Totalpoints[i]) { //tie for third place
                place3 += String.valueOf(i+1) + " ";
            }
            else if(least4 > Totalpoints[i]) {  //new fourth place
                least5 = least4;
                least4 = Totalpoints[i];
                place5 = place4;
                place4 = String.valueOf(i+1) + " ";
            }
            else if(least4 == Totalpoints[i]) { //tie for fourth place
                place4 += String.valueOf(i+1) + " ";
            }
            else if(least5 > Totalpoints[i]) {  //new fifth place
                least5 = Totalpoints[i];
                place5 = String.valueOf(i+1) + " ";
            }
            else if(least5 == Totalpoints[i]) { //tie for fifth place
                place5 += String.valueOf(i+1) + " ";
            }
        }
        //printing out every place
        System.out.println("FIRST PLACE: " + place1);
        System.out.println("SECOND PLACE: " + place2);
        System.out.println("THIRD PLACE: " + place3);
        System.out.println("FOURTH PLACE: " + place4);
        System.out.println("FIFTH PLACE: " + place5);
    }
}
