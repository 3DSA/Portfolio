/*
 * A singly linked list data structure class
 */
public class SinglyLinkedList<Currency> {
	private int count = 0;
	private LinkNode<Currency> head;
	private LinkNode<Currency> tail;
	
	/*
	 * Getters and Setters
	 */
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int n) {
		count = n;
	}
	public LinkNode<Currency> getHead() {
		return head;
	}
	
	public LinkNode<Currency> getTail(){
		return tail;
	}
	
	public void setHead(LinkNode<Currency> head) {
		this.head = head;
	}
	
	public void setTail(LinkNode<Currency> tail){
		this.tail = tail;
	}

	/* toString is a overriden method to convert our singly linked list
	 * into a human readable string for output
	 * 	
	 */
	@Override
	public String toString() {
		String out = "[ ";
		for (Currency node: (Currency[]) toArray()){
			out += node + " ";
		}
		out += "]";
		return out;
	}
	
	
	/*
	 * addCurrency method which takes a Currency object and a node index value as 
	 * parameters to add the Currency to the list at that index.
	 */
	public void addCurrency(Currency curr, int index) {
		
		if(head == null) {
			return;
		}
		LinkNode<Currency> current = head;
		for(int i = 0; i < index; ++i) {
			current = current.getNext();
		}
		if (((Dollar)current.getData()).toString() == "Dollar") {
			if (((Dollar)curr).toString() == "Dollar") {
				Dollar tempCurr = new Dollar (((Dollar)curr).getWhole(),((Dollar)curr).getFraction());
				Dollar newCurrent = new Dollar ((double) current.getData());
				tempCurr.add(tempCurr, newCurrent);
				current.setData((Currency)tempCurr);
			}
		}
	}
	
	/*
	 * removeCurrency method which takes a Currency object as parameter 
	 * and removes that Currency object from the list
	 */
	public void removeCurrency(Currency curr) {
		if(head == null) {
			return;
		}
		int index = 0;
		if (findCurrency(curr) != -1) {
			for (LinkNode<Currency> current = head; current != null; current = current.getNext(), index++) {
				if (current.getNext().getData().toString().equals(curr.toString())) {
					current.setNext(current.getNext().getNext());
					count--;
					return;
				}
			}
		}
	}
	
	/*
	 * removeCurrency overload method which takes a node index as parameter 
	 * and removes the Currency object at that index
	 */
	public void removeCurrency(int index) {
		int i = 0;
		for (LinkNode<Currency> current = head; current != null; current = current.getNext(), i++) {
			if (i==index-1) {
				current.setNext(current.getNext().getNext());
				count--;
				return;
			}
		}
	}
	
	/*
	 * findCurrency method which takes a Currency object as parameter and returns 
	 * the node index at which the Currency is found in the list.
	 */
	public int findCurrency(Currency curr) {
		int index = 0;
		for (LinkNode<Currency> node = getHead(); node != null; node = node.getNext(), index++) {
			if (node.getData().toString().equals(curr.toString())) {
			//if (node.getData().equals(curr)) {
				return index;
			}
		}
		return -1;
		
	}
	/*
	 * getCurrency method which takes an index values as a parameter 
	 * and returns the Currency object.
	 */
	public Currency getCurrency(int index) {
		if(head == null) {
			return null;
		}
		LinkNode<Currency> current = head;
		for(int i = 0; i < index; ++i) {
			current = current.getNext();
		}
		return current.getData();
	}
	
	/*
	 * printList method which returns a string of all the Currency 
	 * objects in the list in the order of index, tab spaced.
	 */
	public String printList() {
		String ret = "";
		if(head == null) {
			return ret;
		}
		LinkNode<Currency> current = head;
		while(current != null) {
			ret+= (current.getData().toString() + "\t");
			current = current.getNext();
		}
		ret+= "\n";
		return ret;
	}
	/*
	 * returns whether list is empty or not
	 */
	public boolean isListEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}
	/*
	 * getter for count
	 */
	public int countCurrency() {
		return count;
	}
	
	/*
	 * append is a method used to add elements to the end of our data structures.
	 * 
	 * pre:
	 * 		data: Currency object
	 * returns:
	 * 		void
	 * 
	 * pseduocode:
	 * new LinkNode(data)
	 * 		check if head is null
	 * 			set the head to data
	 * 			set the tail to data
	 * 		else
	 * 			set the tail's next node to data
	 * 			set the new data node to the tail
	 */
	public void append(Currency data) {
		LinkNode<Currency> node = new LinkNode<>(data);
		if (getHead() == null) { //first element case
			setHead(node);
			setTail(node);
		} else {
			getTail().setNext(node);
			setTail(node);
		}
		++count;
	}
	
	/*
	 * getIndex is a method used to get the position of a specific Currency object
	 * 
	 * pre:
	 * 		data: Currency object
	 * returns:
	 * 		position of object if found, -1 otherwise
	 * 
	 * pseduocode:
	 * i = 0
	 * for head node, while node is not null, next node is the following one
	 * 		if node data = currency object
	 * 			return i
	 * return -1 otherwise
	 */
	public int getIndex(Currency data) {
		int i = 0;
		for (LinkNode<Currency> node = getHead(); node != null; node = node.getNext(), i++) {
			if (node.getData().equals(data)) {
				return i; //found
			}
		}
		return -1; //not found
	}
	
	/*
	 * toArray is a method used to convert a custom data structure to an array
	 * 
	 * pre:
	 * 		
	 * returns:
	 * 		Object array
	 * 
	 * pseduocode:
	 * check if list is empty
	 * 		return empty object array
	 * create object array items of size count
	 * i = 0
	 * for head node, while node is not null, next node is the following one
	 * 		items[i++] = node data
	 * return items
	 */
	public Object[] toArray() {
		if (isListEmpty()) {
			return new Object[0];
		}
		Object[] items = new Object[getCount()];
		int i = 0;
		for (LinkNode<Currency> node = getHead(); node != null; node = node.getNext()) {
			items[i++] = node.getData();
		}
		return items;
	}
	
	/*
	 * clearAll will clear all contents of our data structures
	 * 
	 * pre:
	 * 		
	 * returns:
	 * 		
	 * 
	 * pseduocode:
	 * set head as null
	 * set tail as null
	 * count = 0
	 */
	public void clearAll() {
		setHead(null);
		setTail(null);
		count = 0;
	}
	
	public Currency removeEnd() {
		if (isListEmpty()) {
			return null;
		}
		LinkNode<Currency> node = getHead();
		setHead(node.getNext());
		
		if (node == getTail()) {
			setTail(null);
		}
		count--;
		return node.getData();
	}
}