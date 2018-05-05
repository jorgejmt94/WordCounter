import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class WordCounter {
	
	private String fileName;
	private String[] words;
	
	private int countMode;
	private int resultMode;
	
	private BookReader reader;
	
	private HeapTree  maxHeap;
	private HashTable hashTable;
	private PriorityList priorityList;
	private DirectAddressingTable directMatrix;
	private DirectWithList directList;

	
	private String[][] results;
	private Timer timerRead;
	private Timer timerSave;
	private Timer timerView;
	
	private double usedMemory;
	private static final int MB = 1024 * 1024;
	
	public WordCounter(){
		this.fileName = "res";
		this.countMode = -1;
		this.words = null;
	}
	
	
	
	
	public int menu() throws IOException{
		//titol
		System.out.println("\n//***********WordCounter************\\\n");
		
		
		//mostrar opcions
		System.out.println("Opcions:\n\t 1.Seleccionar fitxer d’entrada \n\t 2.Calcular aparicions\n"
				+ "\t 3.Visualitzar resultats\n\t 4.Sortir\n");
		//preguntar opcio
		try{
			System.out.println("Opcio: ");
			Scanner scannerOption = new Scanner(System.in);
			int option = scannerOption.nextInt();
			//System.out.println("option->"+option );
		
			switch (option) {
				case 1:
					words = null;
					//Seleccionar fitxer d’entrada
					//preguntem el nom del fitxer
					Scanner scannerfileName = new Scanner(System.in);
					System.out.println("Nom llibre: ");
					fileName = scannerfileName.nextLine();
					//llegim el llibre
					BookReader reader = new BookReader(fileName);
					timerRead = new Timer();
					timerRead.start();

					words = reader.readFile();
					timerRead.stop();
					//System.out.println("File name->"+fileName+".txt" );
					break;
				case 2:
					//Calcular aparicions
					if(fileName == "res"){
						System.out.println("Primer introdueix un fitxer d'entrada ");
						break;
					}else{
						//mostem opcions d'estructures de dades
						do{
							System.out.println("Opcions:\n\t 1.Llista ordenada \n\t 2.Hash\n"+ "\t 3.MaxHeap\n\t "
												+ "4.Vector acces directe amb llista\n\t 5.Vector acces directe (no funciona) \n");
							//preguntem opcio d'estructura de dades
							System.out.println("Opcio: ");
							Scanner scannerCountMode = new Scanner(System.in);
							countMode = scannerCountMode.nextInt();
							timerSave = new Timer();
							timerSave.start();
							
							switch(countMode){
								case 1:
									//llista amb prioritat
									priorityList = new PriorityList(words);
									break;
								case 2:
									//hash
									hashTable = new HashTable(words);
									break;
								case 3:
									//max heap
									maxHeap = new HeapTree(words);
									break;
								case 4:
									//vector acces directe
									directList = new DirectWithList(words);
									break;
								case 5:
									
									//vector acces directe
									directMatrix = new DirectAddressingTable(words);
									break;
								default:
									System.out.println("Torna a provar");
									break;
							}
						}while(countMode < 1 || countMode > 5);
						timerSave.stop();
						
					}
		             break;
				case 3:
					//Visualitzar resultats
					if(fileName == "res" || countMode < 1 || countMode > 5){
						System.out.println("Primer introdueix un fitxer d'entrada i metode d'ordenació ");
						break;
					}else{
				
						do{
							results = null;
							System.out.println("Opcions:\n\t 1.Ordenació per número d'aparicions \n\t 2.Ordenació alfabètica\n");
							//preguntem mode de mostrar resultats
							System.out.println("Opcio: ");
							Scanner scannerCountMode = new Scanner(System.in);
							resultMode = scannerCountMode.nextInt();
							timerView = new Timer();
							timerView.start();
							
							
							switch(resultMode){
								case 1:
									//ordre num repeticions
									//segons com s'havia ordenat
									switch(countMode){
										case 1:
											//llista amb prioritat
											results = priorityList.getNumberSort();
											break;
										case 2:
											//hash
											results = hashTable.getNumberSort();
											break;
										case 3:
											//max heap
											results = maxHeap.numberSort();
											if(results[0][0] == null) results = null;
											break;
										case 4:
											//directe i llista
											results = directList.numberSort();
											break;
										case 5:
											//matriu directe
											break;
										default:
											System.out.println("Torna a provar");
											break;
									}
									break;
								case 2:
									//ordre alfabetic
									//segons com s'havia ordenat
									switch(countMode){
										case 1:
											//llista amb prioritat
											results = priorityList.getWordSort();
											
											break;
										case 2:
											//hash
											results = hashTable.getWordSort();
											break;
										case 3:
											//max heap
											results = maxHeap.printAlf();
											if(results[0][0] == null) results = null;
											break;
										case 4:
											//directe llista
											results = directList.wordSort();
											break;
										case 5:
											//directa matriu
											break;
										default:
											System.out.println("Torna a provar");
											break;
									}
									break;
								default:
									System.out.println("Torna a provar");
									break;
							}
						}while(countMode < 1 || countMode > 5);
						if(results != null){
							timerView.stop();
							usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/MB;
							System.out.println("numParaules: "+words.length);
							//					resultat, temps, cost
							HTML html = new HTML(results,timerRead.getTimeInMiliSecs(),timerSave.getTimeInMiliSecs(),timerView.getTimeInMiliSecs(),usedMemory );
						}else{
							System.out.println("Torna a escollir una estructura de dades");
						}
					}
		        	break;
				case 4:
					//sortir
					break;
				default:
					System.out.println("Opcio incorrecte");
					break;
			}	
			return option;
		}catch (InputMismatchException e) {
		       System.out.println ("Entra un numero del 1 al 5");
		       return 0;
		}
		
	}
}