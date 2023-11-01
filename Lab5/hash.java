public class hash<t> {
	
    private int index;
    private t data;
    
    public hash() {
    	index = -1;
    	data = null;
    }
    
    public hash(int index) {
    	this.index = index;
    }
    
    public hash(int index, t data) {
        this.index = index;
        this.data = data;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setData(t data) {
        this.data = data;
    }
    
    public int getIndex() {
        return index;
    }
    
    public t getData() {
        return data;
    }
}