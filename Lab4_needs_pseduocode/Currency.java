/* A currency simulation to demonstrate Classes, Inheritance, and Polymorphism
 * 
 * Authors: Tom Ekshtein, Dhruv Susheelkar
 * Team 12
 * 
 */

import java.math.BigDecimal;

public abstract class Currency implements Comparable<Currency>{ 
	//added the comparable implementation for use with the BST in lab 4
	private int whole;
	private int fraction;
	
	public Currency() {
		whole = 0;
		fraction = 0;
	}
	public Currency(int newWhole, int newFraction) {
		whole = newWhole;
		fraction = newFraction;	
	}
	public Currency(double newCurrency) {
		whole = (int)newCurrency;
		fraction = getDecimal(newCurrency);
		if(getDecimal(newCurrency) < 10 && ((newCurrency-whole)*100) > 10) {
			fraction = getDecimal(newCurrency) * 10;
		}
	}
	public Currency(Currency newCurr) {
		this.fraction = newCurr.fraction;
		this.whole = newCurr.whole;
	}
	
	public int getWhole() {
		return whole;
	}
	
	public int getFraction() {
		return fraction;
	}
	
	public void setWhole(int newWhole) {
		whole = newWhole;
	}
	
	public void setFraction(int newFraction) {
		fraction = newFraction;
	}
	
	/* getDecimal: This function takes a double and returns 
	 * just the decimal portion of the number as an integer
	 * 
	 * pre: 
	 * 		decimalNumber: a double number
	 * 
	 * post: 
	 * 		returns an integer from just the decimal portion of the double
	 */
	public int getDecimal(double decimalNumber) {
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(decimalNumber));
		int wholeNumber = bigDecimal.intValue();
		String fractionStr = bigDecimal.subtract(new BigDecimal(wholeNumber)).toPlainString().substring(2);
		return Integer.parseInt(fractionStr);
	}
	
	/* add: This function takes two currency objects
	 * and adds them together to get an appropriate, logical result
	 * 
	 * pre:
	 * 		c1: the first currency object
	 * 		c2: the second currency object
	 * 
	 * post: 
	 * 		returns c1 after having c2 added to it
	 * 
	 */
	public Currency add(Currency c1, Currency c2) {
		c1.setWhole(c1.getWhole() + c2.getWhole());
		c1.setFraction(c1.getFraction() + c2.getFraction());
		if (c1.getFraction() > 99) {
			c1.setFraction(c1.getFraction() - 100);
			c1.setWhole(c1.getWhole() + 1);
		}
		return c1;
	}
	/* subtract: This function takes two currency objects
	 * and subtracts one from another to get an appropriate, logical result
	 * 
	 * pre:
	 * 		c1: the first currency object
	 * 		c2: the second currency object
	 * 
	 * post: 
	 * 		returns c1 after having c2 subtracted from it
	 * 
	 */
	public Currency subtract(Currency c1, Currency c2) {
		c1.setWhole(c1.getWhole() - c2.getWhole());
			if(c1.getFraction() >= c2.getFraction()) {
				c1.setFraction( c1.getFraction() - c2.getFraction());
			}
			else {
				c1.setFraction( c1.getFraction() - c2.getFraction() + 100);
				c1.setWhole(c1.getWhole() - c2.getWhole() - 1);;
			}
		return c1;
	}

	/* isEqual: This function compares two currency objects to determine
	 * if they are equal to each other
	 * 
	 * pre:
	 * 		c1: the first currency object
	 * 		c2: the second currency object 
	 * 
	 * post:
	 * 		Returns true if c1 and c2 have equal wholes and fractions.
	 * 		Returns false otherwise
	 */
	public boolean isEqual(Currency c1, Currency c2) {
		if (c1.getWhole() == c2.getWhole()) {
			if (c1.getFraction() == c2.getFraction()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * isGreater: This function compares if one currency object is
	 * greater than another
	 * 
	 * pre:
	 * 		c1: the first currency object
	 * 		c2: the second currency object
	 * 
	 * post: 
	 * 		returns true if c1 is greater than c2
	 * 		returns false otherwise
	 */
	public boolean isGreater(Currency c1, Currency c2) {
		if (c1.getWhole() > c2.getWhole()) {
			return true;
		}
		else if (c1.getWhole() == c2.getWhole()) {
			if (c1.getFraction() > c2.getFraction()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * toString: This function converts the current currency to a string
	 * using the whole, a decimal point, and the fraction
	 */
	public String toString() {
		return getWhole() + "." + getFraction();
	}
}
