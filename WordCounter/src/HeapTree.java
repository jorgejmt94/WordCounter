
public class HeapTree {

    
    private TreeNode[] heap;
    private int size;

    private int wordsSaved;
    private int current;//posicio actual

   // private int size;
   

    private static final int FIRST_ELEMENT  = 0; //fer llegible el codi


    public HeapTree(String[] wordsToSave) {
    	size=wordsToSave.length;
    	wordsSaved = 0;
        heap = new TreeNode[wordsToSave.length];
        for (int i = 0; i < wordsToSave.length; i++) heap[i] = null;

        current = 0;
        
        add(wordsToSave);
    }

    public int getLeftSonPos(int parentPos) {

        return (2*parentPos) + 1;
    }

    public int getRightSonPos(int parentPos) {

        return (2*parentPos) + 2;
    }

    private int getParentPos(int sonPos) {
        int parentPos;

        if (sonPos % 2 == 0) parentPos = (sonPos/2) - 1;
        else parentPos = sonPos/2;

        return parentPos;
    }
    
    private boolean isLeaf(int pos){
    	if (pos >=  (wordsSaved / 2)  &&  pos <= wordsSaved)
        {
            return true;
        }
        return false;
    }

    /**
     * afegir tots els elements de l'array d'strings
     */
    public void add(String[] words) {
    	boolean exist = false;
    	String w = "";
    	for(int i = 0; i<words.length; i++){
    		exist = false;
    		//mirar si ja existia
    		
    		//primer element
            if (current == FIRST_ELEMENT && heap[current] == null) {
                heap[current] = new TreeNode(words[i].replace("_", ""));
                exist = true;
            }
            for(int j = 0; j<words.length && !exist;j++){
    			if(heap[j] != null){
    				w = words[i].replace("_", "");
    				words[i]=w;
    				if (words[i].equalsIgnoreCase(heap[j].getWord())) {
            			exist = true;
            			heap[j].increaseRepeatNumber();
            		}
    			}        		
        	}
            if(!exist){
            	//Si no existia i no es el primer element a inserir
                int pos = getLeftSonPos(current);
                if (heap[pos] == null) {           //sense fill esq

                    heap[pos] = new TreeNode(words[i].replace("_", ""));
                    heap[current].setLeftSon(heap[pos]);
                }
                else {

                    pos = getRightSonPos(current);
                    if (heap[pos] == null) {           // per si acas
                        heap[pos] = new TreeNode(words[i].replace("_", ""));
                        heap[current].setRightSon(heap[pos]);
                        current++;
                    }
                }
                wordsSaved++;
                sort(pos);  // comprovar que l'element esta en la posicio correcte
            }
    	}
    	
    	//print();
        
    }


    /**
     * posar l'element a la posicio correcte
     *
     *
     */
 
    private void sort(int addedPos) {
        int parentPos = getParentPos(addedPos);
        boolean ordered = false;

        while (parentPos >= 0 && !ordered) {

        	//el nou element es mes petit
            if (heap[addedPos].getWord().compareToIgnoreCase(heap[parentPos].getWord()) < 0) {

                String auxWord =  heap[addedPos].getWord();
                int auxNumber =  heap[addedPos].getRepeatsNumber();
                
                heap[addedPos].setWord(heap[parentPos].getWord());
                heap[addedPos].setRepeatsNumber(heap[parentPos].getRepeatsNumber());

                heap[parentPos].setWord(auxWord);
                heap[parentPos].setRepeatsNumber(auxNumber);
                addedPos = parentPos;
            } else ordered = true;

            parentPos = getParentPos(addedPos);
        }
    }
    //fer un swap
    public void swap(int a, int b){
		//System.out.println("swappinh" + mH[a] + " and " + mH[b]);
    	String auxWord =  heap[a].getWord();
        int auxNumber =  heap[a].getRepeatsNumber();
     
        heap[a].setWord(heap[b].getWord());
        heap[a].setRepeatsNumber(heap[b].getRepeatsNumber());
       
        heap[b].setWord(auxWord);
        heap[b].setRepeatsNumber(auxNumber);
	}
    //enfonsar-lo
   /* public void goDown(int pos, int last){
    	int smallest = pos;
    	boolean nullDone = false;
    	
    	if(heap[pos] == null || heap[pos].getRightSon() == null || heap[pos].getLeftSon() == null){
    		nullDone=true; 		
    	}
    	
    	if(!nullDone && (heap[pos].getLeftSon().getWord().compareToIgnoreCase(heap[pos].getRightSon().getWord()) < 0) && getLeftSonPos(pos)<last){
    		smallest = getLeftSonPos(pos);

    	}
    	
    	if(!nullDone && (heap[pos].getLeftSon().getWord().compareToIgnoreCase(heap[pos].getRightSon().getWord()) > 0)&&getRightSonPos(pos)<last){
    		smallest = getRightSonPos(pos);

    	}
    	if(smallest!=pos && !nullDone && smallest<last){
    		swap(pos,smallest);
    		goDown(smallest, last);
    	}
    }*/
    
    private void goDown(int pos, int last) {

    	//sino es fulla
        if (!isLeaf(pos)){ 

        	//si algun dels fills es mes petit
            if ( heap[pos].getWord().compareToIgnoreCase(heap[getLeftSonPos(pos)].getWord()) < 0 || 
            	 heap[pos].getWord().compareToIgnoreCase(heap[getRightSonPos(pos)].getWord()) > 0) {
                //si el fill esq es mes petit
            	if (heap[getLeftSonPos(pos)].getWord().compareToIgnoreCase(heap[getRightSonPos(pos)].getWord()) < 0){
                    swap(pos, getLeftSonPos(pos));
                    goDown(getLeftSonPos(pos), last);
                }
                else{
                	//si fill dret es mes petit
                    swap(pos, getRightSonPos(pos));
                    goDown(getRightSonPos(pos), last);
                }
            }
        }
        if( heap[getLeftSonPos(pos)] != null && heap[getRightSonPos(pos)] == null && heap[pos].getWord().compareToIgnoreCase(heap[getLeftSonPos(pos)].getWord()) > 0 ){
        	swap(pos, getLeftSonPos(pos));
    		goDown(getLeftSonPos(pos), last);
    		
        	

    	}
        if( heap[getLeftSonPos(pos)] != null && heap[getRightSonPos(pos)] != null && heap[pos].getWord().compareToIgnoreCase(heap[getLeftSonPos(pos)].getWord()) > 0 ){
        	swap(pos, getLeftSonPos(pos));
    		goDown(getLeftSonPos(pos), last);

    	}
        if( heap[getLeftSonPos(pos)] != null && heap[getRightSonPos(pos)] != null && heap[pos].getWord().compareToIgnoreCase(heap[getRightSonPos(pos)].getWord()) > 0 ){

        	swap(pos, getRightSonPos(pos));
    		goDown(getRightSonPos(pos), last);
    	}
    }
    
    
    public String[][] printAlf() {
    	int k = 0;
		String[][] wordsSortAlf = new String[heap.length][2];

		while (heap[FIRST_ELEMENT] != null) {
			int parentPos = getParentPos(FIRST_ELEMENT);
			//guardem per l'html
			wordsSortAlf[k][0] = heap[FIRST_ELEMENT].getWord();
			wordsSortAlf[k][1] = heap[FIRST_ELEMENT].getRepeatsNumber() + "";
			k++;
			//mostrem el mes petit
			//System.out.println("SAVE-->" +heap[FIRST_ELEMENT].getWord() + ":" + heap[FIRST_ELEMENT].getRepeatsNumber());
			//restem un
			wordsSaved--;
		
			//si no es fulla reordene
			if(heap[FIRST_ELEMENT].getLeftSon() != null && heap[FIRST_ELEMENT].getRightSon() != null){
				reOrder(heap);
			}else{
				heap[FIRST_ELEMENT] = null;
			}
			
		}
		return wordsSortAlf;
	}

    
	public String[][] print() {
		String[][] words = null;
		words = printAlf();
		return words;
	}

	
	private void reOrder(TreeNode[] auxHeap) {
		int last = FIRST_ELEMENT;
		
		//1r buscar ultim
		
		for(last = FIRST_ELEMENT; last < auxHeap.length; last++){
			if(auxHeap[last]==null){
				break;
			}
		}
		//estem a la seguent de la fulla i restem un per estar en la fulla
		last--;

		//2n  posar l'ultim a primer i lultim a null
		auxHeap[FIRST_ELEMENT].setWord(auxHeap[last].getWord());
		auxHeap[FIRST_ELEMENT].setRepeatsNumber(auxHeap[last].getRepeatsNumber());
		
		auxHeap[last] = null;
		//auxHeap[getParentPos(currentPos)].setRightSon(null);
		
		//3r enfonsar-lo a la seva posicio
		/*for(int i = 0; i<10;i++){
			if(heap[i]!=null){
				System.out.println(i+"                                    ->"+heap[i].getWord()+"->"+heap[i].getRepeatsNumber());
			}else{
			}
		}*/
		if(last!=FIRST_ELEMENT) goDown(FIRST_ELEMENT, last);
		
	}
	
	public String[][] numberSort(){
		String[][] words = new String[size][2];
		int max = 0;
		int posSaved = 0;
		int pos = FIRST_ELEMENT;
		boolean nullDone = true;
		while(max != 1){
			//busquem la max de repetits
			max=0;
			for (int i = 0; i < heap.length/2; i++){
				//parent
				if(heap[i] != null && heap[i].getRepeatsNumber() > max && !heap[i].getOut()){
					max = heap[i].getRepeatsNumber();
				}
				//left
				if(heap[i] != null && heap[i].getLeftSon() != null && heap[i].getLeftSon().getRepeatsNumber() > max 
						&& !heap[i].getLeftSon().getOut()){
					max = heap[i].getLeftSon().getRepeatsNumber();
				}
				//right
				if(heap[i] != null && heap[i].getRightSon() != null && heap[i].getRightSon().getRepeatsNumber() > max 
						&& !heap[i].getRightSon().getOut()){
					max = heap[i].getRightSon().getRepeatsNumber();
				}
			}
			//tots els que tinguin el max els mostrem i marquem
			for (int i = 0; i < heap.length/2; i++){
				//parent
				//System.out.println(posSaved + " "+heap.length);
				if(heap[i] != null && heap[i].getRepeatsNumber() == max && !heap[i].getOut()){
					words[posSaved][0] = heap[i].getWord();
					words[posSaved][1] = heap[i].getRepeatsNumber() + "";
					posSaved++;
					heap[i].setOut();
				}
				//left
				if(heap[i] != null && heap[i].getLeftSon() != null && heap[i].getLeftSon().getRepeatsNumber() == max 
						&& !heap[i].getLeftSon().getOut()){
					words[posSaved][0] = heap[i].getLeftSon().getWord();
					words[posSaved][1] = heap[i].getLeftSon().getRepeatsNumber() + "";
					posSaved++;
					heap[i].getLeftSon().setOut();
				}
				//right
				if(heap[i] != null && heap[i].getRightSon() != null && heap[i].getRightSon().getRepeatsNumber() == max 
						&& !heap[i].getRightSon().getOut()){
					words[posSaved][0] = heap[i].getRightSon().getWord();
					words[posSaved][1] = heap[i].getRightSon().getRepeatsNumber() + "";
					posSaved++;
					heap[i].getRightSon().setOut();
				}
			}
			
		}
		return words;
	}
	
}
