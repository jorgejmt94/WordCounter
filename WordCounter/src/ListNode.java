
public class ListNode {
	private int repeatsNumber;
	private String word;
	private ListNode next;
	private ListNode prev;

	
	public ListNode(String word, ListNode next, ListNode prev){
		this.word = word;
		this.repeatsNumber = 1;
		this.next = next;
		this.prev = prev;
	}
	
	public ListNode(){
		this.word = null;
		this.repeatsNumber = -1;
		this.next = null;
		this.prev = null;
	}

	public int getRepeatsNumber() {
		return repeatsNumber;
	}

	public void setRepeatsNumber(int repeatsNumber) {
		this.repeatsNumber = repeatsNumber;
	}
	
	public void increaseRepeatsNumber(){
		this.repeatsNumber++;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}
	public ListNode getPrev() {
		return prev;
	}

	public void setPrev(ListNode prev) {
		this.prev = prev;
	}
	
	
	
}
