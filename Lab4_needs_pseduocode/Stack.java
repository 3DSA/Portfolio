/*
 * A stack data structure class
 */
public class Stack<Currency> extends SinglyLinkedList<Currency>{

	public Stack() {
		super(); {
	        setHead(null);
	        setTail(null);
	    }
	}
	public Stack(SinglyLinkedList<Currency> list) {
    	for(LinkNode<Currency> node = list.getHead(); node != null; node = node.getNext()){
            push(node.getData());
        }
    }
	
    public void push(Currency data) {
    	if (isListEmpty()) {
        	LinkNode<Currency> newHead = new LinkNode<Currency>();
        	newHead.setData(data);
        	this.setHead(newHead);
        	this.setTail(newHead);
        	newHead.setNext(newHead);
        } else {
        	LinkNode<Currency> newHead = new LinkNode<Currency>();
        	newHead.setData(data);
        	newHead.setNext(this.getHead());
        	this.setHead(newHead);
        	this.getTail().setNext(null);
        }
    }
    public Currency peek() {
    	return getHead().getData();
    }
    
    public Currency pop() {
        LinkNode<Currency> temp = this.getHead();       
        this.setHead(temp.getNext());
        
        return temp.getData();
    }
    public String printStack() {
    	return super.printList();
    }
}