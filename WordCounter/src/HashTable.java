/**
 * 
 *Hash table
 *

 *
 */
public class HashTable {
	
	private String[] wordsToSave;
	private final int size;
	private TableNode hashTable[];
	
	/**
	 * rep les paraules del text i les guarda en un array de TableNode
	 * @param initWords -> paraules del text (repetides)
	 */
	public HashTable(String[] initWords) {
        this.size = initWords.length;
		this.hashTable = new TableNode[size];
		
		for(int i =0; i < size ; i++){
			hashTable[i] = new TableNode(-1,null);
		}
        this.wordsToSave = initWords;
        //paraules a guardar
       //for(String s : wordsToSave) System.out.println(s);
        
        saveWords();
        /*
        for(int i = 0; i< size ; i++){
        	if(hashTable[i].getWord() != null) System.out.println("paraula->"+ hashTable[i].getWord()+" repeticions->"+hashTable[i].getRepeatNumber());
        }*/
    }
	
	/**
	 * funcio de hash amb el tamany de la paraula mod tamany
	 * @param word
	 * @param tryNumber
	 * @return
	 */
	public int hashFunction(String word, int tryNumber){
        return ((word.length() + tryNumber) % size);
    }
	
	public int getTableSize(){
		return size;
	}
	
	public String getWord(int position){
		return hashTable[position].getWord();
	}
	public int getRepeatNumber(int position){
		return hashTable[position].getRepeatNumber();
	}
	
	public void saveWords(){
		int tryNumber = 0;
		int savePosition = 0;
		String word = "";
		boolean savedOk = false;
		
		for(int i = 0; i<wordsToSave.length; i++){
			savedOk = false;
			tryNumber = 0;
			word=wordsToSave[i].replace("_", "");
			wordsToSave[i] = word;
			while( !savedOk ){
				savePosition = hashFunction(wordsToSave[i],tryNumber);
				//si no havia res guardem la paraula i posem les repeticions a 1
				
				if(hashTable[savePosition].getWord() == null && savedOk == false){
					hashTable[savePosition] = new TableNode(0, wordsToSave[i]);
					savedOk = true;
				}
				//si es la mateixa parala incrementem les repeticions
				word = wordsToSave[i].replace("_", "");
				if(hashTable[savePosition].getWord().equalsIgnoreCase(wordsToSave[i]) ) {
					hashTable[savePosition].increaseRepeatsNumber();
					savedOk = true;
				}
				
				//si esta ocupada la casella i no es la mateixa paraula fem rehash!
				if(!hashTable[savePosition].getWord().equalsIgnoreCase(wordsToSave[i]) && savedOk == false){
					tryNumber++;
					savePosition = hashFunction(wordsToSave[i],tryNumber);
				}
				
			}
		}
		
	}
	
	public String[][] getWordSort(){
		String[][] wordSort = new String[hashTable.length][2];
		//copia de la taula
		TableNode aux[] = hashTable;
		
		int posMax = 0;
		int posToSave = 0;
		String max = "";
	
		
		
		while(posToSave<size){
			//busquem el mes gran i posem null
			for(int i = 0; i < hashTable.length; i++){
				
				if(aux[i].getWord() != null && aux[i].getWord().compareToIgnoreCase(max) < 0 ){
					max = aux[i].getWord();
					posMax = i;
				}
			}
			//guardem la paraula
			wordSort[posToSave][0] = aux[posMax].getWord();
			wordSort[posToSave][1] = aux[posMax].getRepeatNumberString();
			posToSave++;
			//la posem a null per no tornar a comprovarla
			aux[posMax].setWord(null);
			aux[posMax].setRepeatsNumber(-1);
			//inicialitzem
			posMax = 0;
			max = "zzzz";
		}
		
		return wordSort;
	}
	
	public String[][] getNumberSort(){
		String[][] numberSort = new String[hashTable.length][2];
		int max = 0;
		
		for(int i = 0; i < hashTable.length; i++){
			if(hashTable[i].getRepeatNumber() > max){
				max = hashTable[i].getRepeatNumber();
			}
		}
		int pos = 0;
		for(int numeroXRepeticions = max ;numeroXRepeticions > 0; numeroXRepeticions--){
			for(int j = 0; j<hashTable.length; j++){
				if(hashTable[j].getRepeatNumber() == numeroXRepeticions){
					numberSort[pos][0] = hashTable[j].getWord();
					numberSort[pos][1] = hashTable[j].getRepeatNumberString();
					pos++;
				}
			}
		}
		
		
		return numberSort;
	}


}
