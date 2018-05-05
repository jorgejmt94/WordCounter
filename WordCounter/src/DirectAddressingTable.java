/*
 * taula d'acces directe que tindra de la 'a' a la 'z' (27 posicions) mes els numeros
 * un total de 28 posicions
 * 
 * sera una matriu de 28xtamany de l'array de paraules
 * 
 */
public class DirectAddressingTable {
	private String[] wordsToSave;
	private static final int MAX_AB = 29;
	private static final char INIT_CHAR = 'a';
	private TableNode direct[][];
	
	public DirectAddressingTable(String[] initWords){
		this.direct = new TableNode[MAX_AB][initWords.length];
		char init = 'a';
		for(int i =0; i < MAX_AB ; i++){
			direct[i][0] = new TableNode(-1,null);
		}
        this.wordsToSave = initWords;
        //paraules a guardar
       //for(String s : wordsToSave) System.out.println(s);
        
        saveWords();
	}
	
	private void saveWords(){
		char firstLetter;
		boolean isDigit = false;
		boolean exist = false;
		int last = 0;

		for(int i = 0; i<wordsToSave.length; i++){
			isDigit = false;
			last = 0;
			exist = false;
			firstLetter = wordsToSave[i].charAt(0);
			isDigit = (firstLetter >= '0' && firstLetter <= '9');
			//mirem si es un numero (anira a la 28)
		
			if(isDigit){
				System.out.println("digiiit");
				//comprovem si ja existia
				for(int j = 0; j<wordsToSave.length; j++){
					if(direct[MAX_AB-1][j] != null){
						last++;
						if(direct[MAX_AB-1][j].getWord().compareToIgnoreCase(wordsToSave[i]) == 0){
							exist = true;
							direct[MAX_AB-1][j].increaseRepeatsNumber();
						}
					}
				}
				//sino lafegim a last
				if(!exist){
					direct[MAX_AB-1][last].setWord(wordsToSave[i]); 
					direct[MAX_AB-1][last].setRepeatsNumber(1);
				}
			}
			else {
				//sino es numero anem a la seva casella
				//comprovem si ja existia
				System.out.println(wordsToSave[i].charAt(0) - INIT_CHAR);
				System.out.println(wordsToSave[i].charAt(0));
				
				for(int j = (wordsToSave[i].charAt(0) - INIT_CHAR); j<wordsToSave.length; j++){

					if(direct[wordsToSave[i].charAt(0) - INIT_CHAR][j] != null){
						last++;
						System.out.println("no null");
						System.out.println(direct[wordsToSave[i].charAt(0) - INIT_CHAR][j].getWord() +"  -  "+ wordsToSave[i]);
						if(direct[wordsToSave[i].charAt(0) - INIT_CHAR][j].getWord().compareToIgnoreCase(wordsToSave[i]) == 0){
							exist = true;
							System.out.println("lletra existeix");
							direct[wordsToSave[i].charAt(0) - INIT_CHAR][j].increaseRepeatsNumber();
						}
					}
				}
				//sino lafegim a last
				if(!exist){
					
					direct[wordsToSave[i].charAt(0) - INIT_CHAR][last].setWord(wordsToSave[i]); 
					direct[wordsToSave[i].charAt(0) - INIT_CHAR][last].setRepeatsNumber(1);
					System.out.println("no existia: "+ direct[wordsToSave[i].charAt(0) - INIT_CHAR][last].getWord());
				}
			}
		}/*
		for(int i = 0;i<MAX_AB; i++){
			for(int j = 0;j<wordsToSave.length; j++){
				if(direct[i][j] != null && direct[i][j].getWord() != null ){
					System.out.println(direct[i][j].getWord() +":"+direct[i][j].getRepeatNumber());
				}
			}
		}*/
	}
}