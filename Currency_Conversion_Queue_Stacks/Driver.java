/* A currency simulation to demonstrate Binary Search Trees
 * 
 * Authors: Tom Ekshtein, Dhruv Susheelkar
 * Team 12
 * 
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	final static String OUTPUT = "Output.txt";
	public static int PRINT_NUM = 1;
	
	public static void main(String[] args) {
		 File output = new File(OUTPUT);
	        try {
	            output.createNewFile();
	            new FileWriter(OUTPUT, false).close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		BST<Currency> myBST = new BST<Currency>();
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
		
		for (int i = 0; i < 20; i++) {
			myBST.insert(references[i]);
		}
		mainLoop(myBST);
		 
	}
	
	public static void mainLoop(BST myBST) {
		while(true) {
			printMenu();
			try{
				int input = getInput();
				switch (input) {
				case 1: // insert
					System.out.println("Please input dollar amount to add to the BST:\n");
					Dollar insertion = getCurrencyAmount();
					if (insertion == null) {
						System.out.println("There was an issue adding your currency to the BST.");
						break;
					}
					if (!myBST.duplicate(insertion)) {
						myBST.insert(insertion);	
					} else {
						System.out.println(insertion.toString()+ " is already in the BST.");
					}
					break;
					
				case 2: // search
					System.out.println("Please input dollar amount to search for:\n");
					Dollar target = getCurrencyAmount();
					BSTNode<Dollar> node = myBST.search(target);
					if (node != null) {
						BSTNode<Dollar> left = (node.getLeft() == null) ? null : node.getLeft();
						BSTNode<Dollar> right = (node.getRight() == null) ? null : node.getRight();
						System.out.println("Target found: " + node + "\nLeft node: " + left + "\nRight node: " + right);
					} else {
						System.out.println("Target of " + target.toString() + " was not found.");
					}
					break;
					
				case 3: // delete
					System.out.println("Please input dollar amount to delete:\n");
					Dollar removable = getCurrencyAmount();
					if (myBST.delete(removable)) {
						System.out.println(removable.toString()+ " was removed!");
					} else {
						System.out.println(removable.toString()+ " was not found :(");
					}
					break;
					
				case 4: // clear
					System.out.println("Now clearing the BST...");
					myBST.clearAll();
					break;
					
				case 5: // traversals
					print(myBST);
					break;
					
				case 6: // exit
					System.out.println("Goodbye!");
					print(myBST);
					System.exit(0);
					break;
					
				case 7:
					System.out.println(myBST.getHeight(myBST.getRoot()));
					
				}
			} catch (InputMismatchException e){
			System.out.println("Make sure to input a valid response to the menu!");
			}
		}
	}
	
	public static void printMenu() {
        System.out.print("Main menu\n" +
                "\t1 - add a node\n" +
                "\t2 - search a node\n" +
                "\t3 - delete a node\n" +
                "\t4 - delete all nodes\n" +
                "\t5 - print traversals\n" +
                "\t6 - exit the program\n" +
                "Enter a choice: ");
    }
	
	public static int getInput() {
		Scanner scan = new Scanner(System.in);
		
		int input = 0;
		
		while(true) {
			input = scan.nextInt();
			if (input >= 1 && input <= 7) {
				break;
			}
			System.out.println("Please make sure your input value is between 1 and 6");
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
	
	
	public static void writeToFile(Object obj, String name) {
		FileWriter write = null;
		try {
			write = new FileWriter(OUTPUT, true);
			write.write("Print counter: " + PRINT_NUM/4);
			write.write(name + " " + obj.toString());
			write.write("\n-----------\n");
			write.close();
			PRINT_NUM++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print(BST myBST) {
		Queue<Currency> inOrder = new Queue<>();
		Queue<Currency> preOrder = new Queue<>();
		Queue<Currency> postOrder = new Queue<>();
		SinglyLinkedList<Currency> breadth = myBST.BreadthFirstTraversal();
		myBST.inOrder(myBST.getRoot(), inOrder);
		myBST.preOrder(myBST.getRoot(), preOrder);
		myBST.postOrder(myBST.getRoot(), postOrder);
		System.out.println("Breadth First Traverse\n" + breadth.printList());
		writeToFile(breadth, "Breadth First");
		System.out.println("In Order Traverse\n" + inOrder.printList());
		writeToFile(inOrder, "In Order");
		System.out.println("Pre Order Traverse\n" + preOrder.printList());
		writeToFile(preOrder, "Pre Order");
		System.out.println("Post Order Traverse\n" + postOrder.printList());
		writeToFile(postOrder, "Post Order");
	}

}

