

public class TreeNode{

    private String word;
    private int repeatsNumber;
    private TreeNode leftSon;
    private TreeNode rightSon;
    private boolean out;//flag per saber si ja l'haviem printat

    public TreeNode(String word) {

        this.word = word;
        this.repeatsNumber = 1;
        this.leftSon = this.rightSon = null;
        out = false;
    }

    public TreeNode(String word, TreeNode leftSon, TreeNode rightSon) {

        this.word = word;
        this.repeatsNumber = 1;
        this.leftSon = leftSon;
        this.rightSon = rightSon;
        out = false;
    }
    public void increaseRepeatNumber(){
    	this.repeatsNumber++;
    }

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getRepeatsNumber() {
		return repeatsNumber;
	}

	public void setRepeatsNumber(int repeatsNumber) {
		this.repeatsNumber = repeatsNumber;
	}

	public TreeNode getLeftSon() {
		return leftSon;
	}

	public void setLeftSon(TreeNode leftSon) {
		this.leftSon = leftSon;
	}

	public TreeNode getRightSon() {
		return rightSon;
	}

	public void setRightSon(TreeNode rightSon) {
		this.rightSon = rightSon;
	}
	
	public boolean getOut(){
		return out;
	}
	public void setOut(){
		out = true;
	}


}
