/* A currency simulation to demonstrate Hash Tables
 * 
 * Authors: Tom Ekshtein, Dhruv Susheelkar
 * Team 12
 * 
 *
 */


import java.util.InputMismatchException;
import java.util.Scanner;


public class Driver {
	public static void main(String[] args) {

		Currency[] references = new Currency[20];
		references[0] = new Dollar(57.12);
		references[1] = new Dollar(23.44);
		references[2] = new Dollar(87.43);
		references[3] = new Dollar(68.99);
		references[4] = new Dollar(111.22);
		references[5] = new Dollar(44.55);
		references[6] = new Dollar(77.77);
		references[7] = new Dollar(18.36);
		references[8] = new Dollar(543.21);
		references[9] = new Dollar(20.21);
		references[10] = new Dollar(345.67);
		references[11] = new Dollar(36.18);
		references[12] = new Dollar(48.48);
		references[13] = new Dollar(101.00);
		references[14] = new Dollar(11.00);
		references[15] = new Dollar(21.00);
		references[16] = new Dollar(51.00);
		references[17] = new Dollar(1.00);
		references[18] = new Dollar(251.00);
		references[19] = new Dollar(151.00);
		hashtable hashtables = new hashtable(29);
		
		for (int i = 0; i < references.length; i++) {
			hashtables.insert(references[i]);
		}
		System.out.println(hashtables.toString());


		mainLoop(hashtables);
		 
	}
	
	public static void mainLoop(hashtable myHT) {
		while(true) {
			printMenu();
			try{
				int input = getInput();
				switch (input) {
				case 1: // insert
					System.out.println("Please input dollar amount to add to the HashTable:\n");
					Dollar insertion = getCurrencyAmount();
					if (insertion == null) {
						System.out.println("There was an issue adding your currency to the HashTable.");
						break;
					} else {
						myHT.insert(insertion);
						System.out.println("Successfully inserted " + insertion.toString());
					}
					break;
					
				case 2: // search
					System.out.println("Please input dollar amount to search for:\n");
					Dollar target = getCurrencyAmount();
					if (myHT.search(target) != null) {
						System.out.println("Target found: " + target.toString());
					} else {
						System.out.println("Invalid Data");
					}
					break;

				case 3: //print
					System.out.println(myHT.toString());
					break;
					
				case 4: // exit
					System.out.println("Goodbye!");
					System.exit(0);
					break;
					
				}
			} catch (InputMismatchException e){
			System.out.println("Make sure to input a valid response to the menu!");
			}
		}
	}
	
	public static void printMenu() {
        System.out.print("Main menu\n" +
                "\t1 - insert a currency object\n" +
                "\t2 - search for a currency object\n" +
                "\t3 - print all values\n" +
                "\t4 - exit the program\n" +
                "Enter a choice: ");
    }
	
	public static int getInput() {
		Scanner scan = new Scanner(System.in);
		
		int input = 0;
		
		while(true) {
			input = scan.nextInt();
			if (input >= 1 && input <= 4) {
				break;
			}
			System.out.println("Please make sure your input value is between 1 and 4");
		}
		return input;
	}
	
	
	public static Dollar getCurrencyAmount() {
		Scanner scan = new Scanner(System.in);
		
		double input = 0;
		
		while(true) {
			input = scan.nextDouble();
			if (input >= 0 ) {
				break;
			}
			System.out.println("Please make sure your input value is a positive number");
		}
		Dollar ret = new Dollar(input);
		return ret;
	}
	
	
	
}