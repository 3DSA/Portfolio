import java.lang.StringBuilder;

public class BST<t extends Comparable<t>> {
	private BSTNode<t> root;
	private int count = 0;
	
	public BST(){
		root = null;
	}
	public BST(t data) {
		root.setData(data);
	}
	
	/*
	 * getters and setters
	 */
	public BSTNode<t> getRoot(){
		return this.root;
	}
	
	public int count() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setRoot(BSTNode<t> root) {
		this.root = root;
	}
	
	/*
	 * basic methods
	 */

	/*
	 * delete is a method used to get rid of a specific data from the tree
	 *
	 * pre:
	 * 		data: Dollar object
	 * returns:
	 * 		true of object is deleted, false if not found in tree
	 *
	 * pseuducode:
	 * while current node != null,
	 * for head node, while node is not null, next node is the following one
	 * 		if current node data = currency object to be deleted
	 * 			delete current node
	 * 			return true
	 * else
	 * return false
	 */
	
	public boolean delete(t data) {
		/*
		 * needs 5 main cases,
		 * 1	leaf node 
		 * 2	node only with left child
		 * 3	node only with right child
		 * 4	node with two children
		 * 5	no node found
		 */
		
		BSTNode<t> parent = null;
		BSTNode<t> curr = root;
		
		while (curr != null) {
			if (curr.getData().compareTo(data) == 0) {
				
				if (curr.getLeft() == null && curr.getRight() == null) { // case 1
					if (parent == null) {
						root = null;
					} else if (parent.getLeft() == curr) {
						parent.setLeft(null);
					} else {
						parent.setRight(null);
					}
					
				} else if (curr.getRight() == null) {// case 2
					if (parent == null) {
						root = curr.getLeft();
					} else if (parent.getLeft() == curr) {
						parent.setLeft(curr.getLeft());
					} else {
						parent.setRight(curr.getLeft());
					}
				} else if (curr.getLeft() == null) {// case 3
					if (parent == null) {
						root = curr.getRight();
					} else if (parent.getLeft() == curr) {
						parent.setLeft(curr.getRight());
					} else {
						parent.setRight(curr.getRight());
					}
				} else { // case 4
					BSTNode<t> successor = curr.getRight();
					while (successor.getLeft() != null) {
						successor = successor.getLeft();
					}
					t successorData = successor.getData();
					delete(successor.getData());
					curr.setData(successorData);
				}
				count--;
				return true; //Node was successfully removed
			} else if (curr.getData().compareTo(data) < 0) {
				parent = curr;
				curr = curr.getRight();
			} else {
				parent = curr;
				curr = curr.getLeft();
			}
		}
		return false; //Node not found
	}
	/*
	 * clearAll is a method used to get rid of the BST tree
	 *
	 * pre:
	 *
	 * returns:
	 * 		void
	 *
	 * pseuducode:
	 * root = null
	 * count = 0
	 */
	public void clearAll() {
		this.root = null;
		count = 0;
	}
	
	public boolean isEmpty() {
		if (this.root == null && count == 0) {
			return true;
		}
		return false;
	}
	/*
	 * insert is a method used to put data in the tree
	 *
	 * pre:
	 * 		data: Dollar object
	 * returns:
	 * 		void
	 *
	 * pseuducode:
	 * if tree = null
	 * 		set root data to Dollar object
	 * else
	 * 		while current node != null
	 * 			if current node data >	dollar and left of current node is null
	 * 					if left of current node = null
	 * 						set left of node data to the dollar object
	 * 					else
	 * 						move current to current left node
	 * 			else
	 * 					if current right node = null
	 * 						set right of node data to dollar object
	 * 					else
	 * 						move current to current right node
	 */
	public void insert(t data) {
		BSTNode<t> node = new BSTNode(data);
		if (root == null){
			root = node;
		} else {
			BSTNode<t> curr = root;
			while (curr != null) {
				if (node.getData().compareTo(curr.getData()) < 0) { //node < current
					if (curr.getLeft() == null) {
						curr.setLeft(node);
						curr = null;
					} else {
						curr = curr.getLeft();
					}
				} else {
					if (curr.getRight() == null) {
						curr.setRight(node);
						curr = null;
					} else {
						curr = curr.getRight();
					}
				}
			}
		}
		this.count++;
	}
	/*
	 * duplicate is a method used to put data in the tree
	 *
	 * pre:
	 * 		data: Dollar object
	 * returns:
	 * 		true if there is a duplicate of dollar object, false if not
	 *
	 * pseuducode:
	 * if count = 0
	 * 		return false
	 * 		while current node != null
	 * 			checks if current node equals target, then returns true
	 * 			checks if current node is less than  target, moves current node to left
	 * 			checks if current node is greater than target, moves current node to right
	 * returns false if it gets to end of loop
	 */
	public boolean duplicate(t target) {
		if (count == 0) {
			return false;
		}
		BSTNode<t> curr = root;
		while (curr != null) {
			if (curr.getData().compareTo(target) == 0) {
				return true;
			} else if (target.compareTo(curr.getData()) < 0) {
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		return false;
	}
	/*
	 * search is a method used to find the node holding the data
	 *
	 * pre:
	 * 		data: Dollar object
	 * returns:
	 * 		the node holding data
	 *
	 * pseuducode:
	 * if count = 0
	 * 		return an empty node
	 * 		while current node != null
	 * 			checks if current node equals target, then returns node
	 * 			checks if current node is less than  target, moves current node to left
	 * 			checks if current node is greater than target, moves current node to right
	 */
	public BSTNode<t> search(t target){
		if (count == 0) {
			BSTNode<t> empty = new BSTNode(-1);
			return empty;
		}
		BSTNode<t> curr = root;
		while (curr != null) {
			if (curr.getData().compareTo(target) == 0) {
				return curr;
			} else if (target.compareTo(curr.getData()) < 0) {
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		return null;
	}
	
	/*
	 * traversal methods
	 * 
	 */

	/*
	 * breadth is a method used to go through BST tree in a certain order
	 *
	 * pre:
	 * returns:
	 * 		Singly linked List
	 *
	 * pseuducode:
	 * if root = null
	 * 		returns empty list
	 * new linked list created with root at start
	 * 	while new linked list is not empty
	 * 			appends all the data in a row until it is null
	 * returns linked list
	 * returns false if it gets to end of loop
	 */
	public SinglyLinkedList<t> BreadthFirstTraversal(){
		SinglyLinkedList<t> traverse = new SinglyLinkedList<>();
		if (root == null) {
			return traverse;
		}
		SinglyLinkedList<BSTNode<t>> sll = new SinglyLinkedList<>();
		sll.append(root);
		while (!sll.isListEmpty()) {
			BSTNode<t> curr = sll.removeEnd();
			traverse.append(curr.getData()); //append node 
			if (curr.getLeft() != null) {
				sll.append(curr.getLeft());
			}
			if (curr.getRight() != null) {
				sll.append(curr.getRight());
			}
		}
	return traverse;	
	}

	/*
	 * inOrder is a method used to go through BST tree in a certain order
	 *
	 * pre:
	 * 		data: Dollar object, a queue
	 * returns:
	 * 		void
	 *
	 * pseuducode:
	 * if root = null
	 * 		return false
	 * call it recursively to move the current node left
	 * add the data to the queue
	 * call it recursively to move the current node right
	 */
	public void inOrder(BSTNode<t> curr, Queue<t> traverse){ //start from root node
		if (curr == null) {
		}
		else {
			inOrder(curr.getLeft(), traverse);
			traverse.enqueue(curr.getData());
			inOrder(curr.getRight(), traverse);
		}
	}
	/*
	 * preOrder is a method used to go through BST tree in a certain order
	 *
	 * pre:
	 * 		data: Dollar object, a queue
	 * returns:
	 * 		void
	 *
	 * pseuducode:
	 * if root = null
	 * 		return false
	 * add the data to the queue
	 * call it recursively to move the current node left
	 * call it recursively to move the current node right
	 */
	public void preOrder(BSTNode<t> curr, Queue<t> traverse){
		if (curr == null) {
			return;
		}
		traverse.enqueue(curr.getData());
		preOrder(curr.getLeft(), traverse);
		preOrder(curr.getRight(), traverse);
	}
	/*
	 * postOrder is a method used to go through BST tree in a certain order
	 *
	 * pre:
	 * 		data: Dollar object, a queue
	 * returns:
	 * 		void
	 *
	 * pseuducode:
	 * if root = null
	 * 		return false
	 * call it recursively to move the current node left
	 * call it recursively to move the current node right
	 * add the data to the queue
	 */
	public void postOrder(BSTNode<t> curr,Queue<t> traverse){
		if (curr == null) {
			return;
		}
		postOrder(curr.getLeft(), traverse);
		postOrder(curr.getRight(),traverse);
		traverse.enqueue(curr.getData());
	}
	/*
	 * findSmallest is a method used to find the smallest dollar amount in BST
	 *
	 * pre:
	 * 		data: BSt node
	 * returns:
	 * 		Dollar object
	 *
	 * pseuducode:
	 * while left of current node != null
	 * 		current node = left of current node
	 * return current node
	 */
	public t findSmallest(BSTNode<t> target){
		BSTNode<t> curr = target;
		while (curr.getLeft() != null) {
			curr = curr.getLeft();
		}
		return curr.getData();
	}
	/*
	 * findLargest is a method used to find the largest dollar amount in BST
	 *
	 * pre:
	 * 		data: BSt node
	 * returns:
	 * 		Dollar object
	 *
	 * pseuducode:
	 * while left of current node != null
	 * 		current node = right current node
	 * return current node
	 */
	public t findLargest(BSTNode<t> target){
		BSTNode<t> curr = target;
		while (curr.getRight() != null) {
			curr = curr.getRight();
		}
		return curr.getData();
	}
	/*
	 * getHeight is a method used to find the smallest dollar amount in BST
	 *
	 * pre:
	 * 		data: BSt node
	 * returns:
	 * 		int height
	 *
	 * pseuducode:
	 * checks height left of tree
	 * checks height right of tree
	 * checks which is greater
	 * adds 1 to greater amount then returns it
	 */
	public int getHeight(BSTNode<t> node) {

        if (node == null)
            return -1;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = getHeight(node.getLeft());
            int rDepth = getHeight(node.getRight());
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }
}
