/**
 * 
 * Node of the hash table
 *
 */
public class TableNode {


    private int repeatsNumber;
    private String word;
    
    public TableNode(int repeatsNumber, String word){
        this.repeatsNumber = repeatsNumber;
        this.word = word;
    }
    
    public void increaseRepeatsNumber(){
    	repeatsNumber++;
    }
    
    public String getWord(){
    	return this.word;
    }
    
    public void setWord(String word){
    	this.word = word;
    }
    public void setRepeatsNumber(int repeatsNumber){
    	this.repeatsNumber = repeatsNumber;
    }
    public int getRepeatNumber(){
    	return repeatsNumber;
    }
    public String getRepeatNumberString(){
    	return repeatsNumber + "";
    }
	
}
