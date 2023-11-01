
public class BSTNode<t> {
	private t data;

	private BSTNode<t> right;
	private BSTNode<t> left;
		
	public BSTNode(t data, BSTNode<t> right, BSTNode<t> left){
		this.data = data;
		this.right = right;
		this.left = left;
	}
	
	public BSTNode(t data) {
		this.data = data;
	}
	
	public BSTNode() {
		
	}
	
	public t getData() {
		return this.data;
	}
	
	public BSTNode<t> getRight(){
		return this.right;
	}
	
	public BSTNode<t> getLeft(){
		return this.left;
	}
	
	
	public void setData(t data) {
		this.data = data;
	}
	
	public void setRight(BSTNode<t> right) {
		this.right = right;
	}
	
	public void setLeft(BSTNode<t> left) {
		this.left = left;
	}

	/*
	 * toString: This function overrides a previous function to convert data to a string
	 */
	@Override
	public String toString() {
		return data.toString();
	}



	

}
