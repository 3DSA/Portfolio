/*
 * A node data structure to be implemented in our other data structures
 */
public class LinkNode<Currency> {

	private Currency data; 		//data of the node
	private LinkNode<Currency> next;	//next node in the list

	
	public void setData(Currency data) {
		this.data = data;
	}
	
	public void setNext(LinkNode<Currency> next) {
		this.next = next;
	}
	
	public Currency getData() {
		return data;
	}
	
	public LinkNode<Currency> getNext() {
		return next;
	}
	
	
	public LinkNode() {
	}
	
	public LinkNode(Currency data) {
		this(data,null);
	}
	
	public LinkNode(Currency data, LinkNode<Currency> next) {
		setData(data);
		setNext(next);
	}
}
