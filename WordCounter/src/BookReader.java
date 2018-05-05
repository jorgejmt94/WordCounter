import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


class BookReader {
  String bookFile;

    public BookReader(String bookFile) { 
    	this.bookFile = bookFile;
    }
    
    public String[] readFile() throws IOException {
    	try {
    		String[] words = null;
    	
	    	String data = "", info = "";
	    	
	    	FileInputStream file = new FileInputStream("llibres/"+bookFile+".txt");
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(file));
	    	
	    	//llegim el llibre
	    	while((data = reader.readLine()) != null) info += data;
	    	
	    	//treiem tots els signes de puntuacio
	    	words = info.split("\\W+");
	    
	    	//for(String s : words) System.out.println(s);
	    	
	    	
	    	//mostrar paraules
	    	//for(String s : words) System.out.println(s);
	    	
	    	return words;
	    }catch(FileNotFoundException e) {
			System.out.println("Fitxer no trobat");
	    }
		return null;
    }
}