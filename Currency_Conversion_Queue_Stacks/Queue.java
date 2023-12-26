/*
 * A queue data structure class
 */
public class Queue<Currency> extends SinglyLinkedList<Currency>{
    public Queue() {
		super(); {
	        setHead(null);
	        setTail(null);
	    }
    }
    public Queue(SinglyLinkedList<Currency> list) {
    	for(LinkNode<Currency> node = list.getHead(); node != null; node = node.getNext()){
            enqueue(node.getData());
        }
    }

    public Currency peekFront() {
        return getHead().getData();
    }
    public Currency peekRear() {
        return getTail().getData();
    }
    public Currency dequeue() {
    	removeEnd();
    	return getHead().getData();
    }
    public void enqueue(Currency curr) {
    	super.append(curr);
    }
    public String printQueue() {
    	return super.printList();
    }
    
}
