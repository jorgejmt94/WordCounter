/**
 * 
 * Cua ordenada per ordre alfabetic, conte fantasma
 *
 */
public class PriorityList {
	private ListNode first;
	private ListNode last;
	private ListNode pdi;
	private int size;
	
	public PriorityList(String[] wordsToSave){
		size = 1;
		first = new ListNode();
		last = new ListNode();
		first.setNext(last);
		last.setPrev(first);
		pdi = first;
		
		saveWords(wordsToSave);
	}
	
	public ListNode getFirstNode(){
		return first;
	}
	public ListNode getLastNode(){
		return last;
	}
	
	public PriorityList(){
		size = 1;
		first = new ListNode();
		last = new ListNode();
		first.setNext(last);
		last.setPrev(first);
		pdi = first;
		
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
		//return first == last;
	}
	
	
	public ListNode getPdi(){
		if(!isEmpty()){
			return pdi;
		}else{
			System.out.println("Llista buida");
			return null;
		}
	}
	
	public boolean findWord(String word){
		pdi = first;
		//mirem si estava a la llista
		while(pdi.getNext() != last){
			if(pdi.getNext().getWord().equalsIgnoreCase(word) && pdi.getNext().getWord() != null){
				pdi.getNext().increaseRepeatsNumber();;
				return true;
			}
			pdi = pdi.getNext();
			
		}
		return false;
	}
	
	public void addNewWord(String word){
		boolean exist = false;
		ListNode  newNode = null;
		String wordToSave = word;
		word = wordToSave.replace("_", "");
		boolean saved = false;
		//mirem si estava a la llista i incrementem les seves aparicions
		exist = findWord(word);
		//si no existia l'afegim en ordre alfabetic
		if(!exist){
			pdi = first;
			//busquem la posicio on l'hem dinserir
			while(pdi.getNext()!=last && pdi.getNext().getWord().compareToIgnoreCase(word) < 0){
				pdi = pdi.getNext();
			}
			//l'inserim, hi havia un problema amb "_" i per aixo el replace
			newNode = new ListNode(word.replace("_", ""),pdi.getNext(),pdi);
			pdi.setNext(newNode);
			size++;
		}
	}
	
	private void saveWords(String[] wordsToSave){
		
		for(int i = 0; i < wordsToSave.length; i++){
			addNewWord(wordsToSave[i]);
		}
	}
	
	public String[][] getWordSort(){
		String[][] wordsSaved = new String[size][2];
		int pos = 0;
		pdi=first;
		while(pdi.getNext() != last){
			wordsSaved[pos][0] = pdi.getNext().getWord();
			wordsSaved[pos][1] = pdi.getNext().getRepeatsNumber() + "";
			pos++;
			pdi=pdi.getNext();
		}
		return wordsSaved;
	}
	
	public String[][] getNumberSort(){
		String[][] wordsSaved = new String[size][2];
		int pos = 0;
		ListNode auxFirst = new ListNode();
		auxFirst.setNext(first.getNext());
		ListNode auxLast = new ListNode();
		auxLast.setPrev(last.getPrev());
		ListNode auxPdi = null;
		auxPdi = auxFirst;
		int max = 0;
		while(auxPdi.getNext().getWord() != null){
			//busco el maxim de repeticions
			if(auxPdi.getNext().getRepeatsNumber() > max){
				max = auxPdi.getNext().getRepeatsNumber();
			}
			auxPdi = auxPdi.getNext();
		}
		
		
		while(max != 0){			
			auxPdi = auxFirst;
			while(auxPdi.getNext().getWord() != null){
				if(auxPdi.getNext().getRepeatsNumber() == max){
					//comparem i si es el num de rep. la guardem
					wordsSaved[pos][0] = auxPdi.getNext().getWord();
					wordsSaved[pos][1] = auxPdi.getNext().getRepeatsNumber() + ""; 
					//auxPdi.getNext().setRepeatsNumber(-1);
					pos++;
				}
				auxPdi = auxPdi.getNext();
			}
			max--;	
		}
		return wordsSaved;
	}
	
}
