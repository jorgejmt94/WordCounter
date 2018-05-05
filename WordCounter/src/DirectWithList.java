/*
 * taula d'acces directe de la a a la z amb un per numeros on a cada casella hi ha una llista
 * 
 */
public class DirectWithList {
	private String[] wordsToSave;
	private static final int MAX_AB = 29;
	private static final int NUMBERS_POSITION = 28;

	private static final char INIT_CHAR = 'a';
	private static final char INIT_MAJUS = 'A';

	private PriorityList directList[];
	
	public DirectWithList(String[] initWords){
		this.directList = new PriorityList[MAX_AB];
		char init = 'a';
		for(int i =0; i < MAX_AB ; i++){
			directList[i] = new PriorityList();
		}
        this.wordsToSave = initWords;
        //paraules a guardar
       //for(String s : wordsToSave) System.out.println(s);
        
        saveWords();
	}
	
	private void saveWords(){
		char firstLetter = 'a';
		String word = "";
		boolean isDigit = false;

		for(int i = 0; i<wordsToSave.length; i++){
			word = wordsToSave[i].replace("_", "");
			//wordsToSave[i].replace("_", "");
			/////////  //////////////////
			isDigit = false;
			if(word!=null && !word.equalsIgnoreCase("")){
				firstLetter = word.charAt(0);
			}
			//firstLetter = word.charAt(0);
			isDigit = (firstLetter >= '0' && firstLetter <= '9');
			//mirem si es un numero (anira a la 28)
		
			if(isDigit){
				directList[NUMBERS_POSITION].addNewWord(word);
			}
			else {
				if(!word.equalsIgnoreCase("") && word.charAt(0) >= 'a' && word.charAt(0) <= 'z'){
					directList[word.charAt(0) - INIT_CHAR].addNewWord(word);
				}
				else if(!word.equalsIgnoreCase("") && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z'){
					directList[word.charAt(0) - INIT_MAJUS].addNewWord(word);
				}
			}
				
		}
		//numberSort();
	}
	
	public String[][] wordSort(){
		String[][] a = null;
		String[][] result = new String[wordsToSave.length][2];
		int addPos = 0;
		//primer trec els numeros
		a = directList[28].getWordSort();
		for(int j = 0; j< a.length; j++){
			if(a[j][0]!=null){
				//System.out.println(a[j][0] +":"+a[j][1] );
				result[addPos][0] = a[j][0];
				result[addPos][1] = a[j][1];
				addPos++;
			}
		}
		//ara les lletres
		for(int i = 0; i<MAX_AB-1;i++){
			a = directList[i].getWordSort();
			for(int j = 0; j< a.length; j++){
				if(a[j][0]!=null){
					//System.out.println(a[j][0] +":"+a[j][1] );
					result[addPos][0] = a[j][0];
					result[addPos][1] = a[j][1];
					addPos++;
				}
			}
			a=null;
		}
		return result;
	}
	
	public String[][] numberSort(){
		//busco el mes gran
		String[][] result = new String[wordsToSave.length][2];
		String[][] a = null;
		PriorityList pl = new PriorityList();
		int max = 1;
		int addPos = 0;
		int number = 0;
		String word = "zzz";
		//busquem el max
		for(int i = 0; i<MAX_AB;i++){
			a = directList[i].getNumberSort();
			for(int j = 0; j< a.length; j++){
				if(a[j][1]!=null){
					number = Integer.parseInt(a[j][1]);
					if(number>max){
						//System.out.println(a[j][0] +":"+a[j][1] );
						max = number;
					}
				}
			}
		}
		while(max != 0){
			//ara mostrem tots els que tinguin aquell max
			for(int i = 0; i<MAX_AB;i++){
				a = directList[i].getNumberSort();
				for(int j = 0; j< a.length; j++){
					if(a[j][1]!=null && a[j][0]!=null && word != null){
						number = Integer.parseInt(a[j][1]);
						if(number==max){
							result[addPos][0] = a[j][0];
							result[addPos][1] = a[j][1];
							addPos++;
						}
					}
				}
				a=null;
			}
			max--;
		}
		/*comprovaciofor(int i = 0; i<result.length;i++){
			if(result[i][0]!=null){
				System.out.println(result[i][0]+" "+result[i][1]);
			}
		}*/
		return result;
	}
	
}
