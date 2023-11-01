
import java.util.ArrayList;
import java.lang.Math.*;
public class hashtable<t> {
	
    private hash<Currency>[] hashtables;
    private int collisions = 0;
    private int m = 2;
    private int n = 3;
    private int size;
    private double load;
    private int count;
    
    /*
     * Constructors
     */
    
    public hashtable() {
    	
    }
    public hashtable(int size) {
        hashtables = (hash<Currency>[]) new hash[size];
        for(int i = 0; i < size; ++i) {
            hashtables[i] = new hash();
        	hashtables[i].setIndex(i);
        }
        this.size = size;
    }
    
    
    /*
	 * insert is a method which takes a currency object and inserts it into the hashtable
	 *
	 * pre:
	 * 		dataval: Currency object
	 * returns:
	 * 		void
	 *
	 * pseuducode:
	 * while buckets probed is less than size of hashtable
	 * 		if hashtable[hashed location] is null
	 * 			set hashtable[hashed location] as dataval
	 * 			load calculations after inserting a new value
	 * 			break
	 * 		increment all values used for hashing, collisions, and buckets probed
	 */
    public void insert(Currency dataval) {
    	int mNew = m;
    	int nNew = n;
    	int bucketsProbed = 0;
        int datalocation = (mNew * dataval.getWhole() + (nNew * nNew) * dataval.getFraction()) % size;
        
        while(bucketsProbed < size) {
        	if (hashtables[datalocation].getData() == null) {
        		hashtables[datalocation].setData(dataval);
        		setLoad();
        		count++;
        		break;
        	}
        	mNew++;
        	nNew++;
            datalocation = (mNew * dataval.getWhole() + (nNew * nNew) * dataval.getFraction()) % size;
            collisions++;
            bucketsProbed++;
        }
    }
    /*
   	 * search is a method which searches for a currency object in the hashtable
   	 *
   	 * pre:
   	 * 		dataval: Currency object
   	 * returns:
   	 * 		dataval if found, null otherwise
   	 *
   	 * pseuducode:
   	 * while hashtables[hashed value]'s data is not null and bucketsprobed < size of hashtable
   	 * 		if hashtables[hashed value]'s whole == dataval's whole AND hashtables[hashed value]'s fraction == dataval's fraction
   	 * 			return dataval
   	 * 		increment all values used for hashing, and buckets probed
   	 * return null
   	 */
    public Currency search(Currency dataval) {
    	int mNew = m;
    	int nNew = n;
    	int bucketsProbed = 0;
        int datalocation = (mNew * dataval.getWhole() + (nNew * nNew) * dataval.getFraction()) % size;        
        
        while((hashtables[datalocation].getData() != null) && (bucketsProbed < size)) {
        	if (hashtables[datalocation].getData().getWhole() == dataval.getWhole() &&
        			hashtables[datalocation].getData().getFraction() == dataval.getFraction()) {
        		return dataval;
        			
        	}
        	mNew++;
        	nNew++;
        	bucketsProbed++;
        	datalocation = (mNew * dataval.getWhole() + (nNew * nNew) * dataval.getFraction()) % size;  
    	}
        return null;
    }
    
    
    /*
     * Getters and Setters
     */
    public void setLoad() {
    	int count = 0;
    	for (int i = 0; i < size; i++) {
    		if (hashtables[i].getData() != null)
    			count++;
    	}
    	this.load = (double)count / (double)size;
    }
    
    public double getLoad() {
    	return this.load;
    }
    
    public int getCollisions() {
    	return this.collisions;
    }
    
    public int getCount() {
    	return this.count;
    }
    
    /*
     * Overrides
     */
    
    /*
   	 * toString is a method which overrides the typical toString method. 
   	 *
   	 * pre:
   	 * 		
   	 * returns:
   	 * 		Stringified version of the hashtable and all other necessary values
   	 * 		including load, collisions, and number of items
   	 *
   	 * pseuducode:
   	 * for size of hashtable
   	 * 		if hashtable[i] is not null append information to return variable
   	 * 		else append index and null to return variable
   	 */
    @Override
    public String toString() {
    	String ret = "";
    	ret += "Load Factor: " + getLoad() + "\n\n";
    	ret += "Collisions: " + getCollisions() + "\n\n";
    	ret += "Number of items: " + getCount() + "\n\n";
    	for (int i = 0; i < size; i++) {
    		if (hashtables[i].getData() != null) {
    			ret += ("Index: " + i + "\nValue: " + hashtables[i].getData().toString() + "\n\n");
    		} else {
    			ret += ("Index: " + i + "\nValue: null\n\n");
    		}
    	}
    	return ret;
    }
    
}
