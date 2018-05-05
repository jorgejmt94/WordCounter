import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException {
		int option;
		WordCounter wordCounter = new WordCounter();		
		
		do{
			option = wordCounter.menu();
		}while (option != 4);
		System.out.println("Fins a la proxima!");
	}
}
