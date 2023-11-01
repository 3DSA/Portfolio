/* A currency simulation to demonstrate Classes, Inheritance, and Polymorphism
 * 
 * Authors: Tom Ekshtein, Dhruv Susheelkar
 * Team 12
 * 
 */

public class Dollar extends Currency {
	private String name = "Dollar";

	public Dollar() {
		super();
	}
	public Dollar(int whole, int fraction) {
		super(whole,fraction);
	}
	public Dollar(double newCurrency) {
		super(newCurrency);
	}
	public Dollar(Currency newCurr) {
		super(newCurr);
	}
	public String getName() { // added in lab 4
		return name;
	}
	
	/*
	 * toString: This function converts the current currency to a string
	 * using the whole, a decimal point, the fraction, and the name (dollar)
	 */
	public String toString() {
		return getWhole() + "." + getFraction() + " " + name;
	}
	@Override
	public int compareTo(Currency curr) { //added in lab 4
		if (curr.isGreater(curr, this)) {
			return -1;
		}
		if (curr.isGreater(this, curr)) {
			return 1;
		}
		return 0;
	}
}

