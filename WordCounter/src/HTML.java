import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class HTML {
	public HTML (String[][] words, double timeRead, double timeSave, double timeView, double cost){
		FileOutputStream html;
		PrintStream p;
		int j = 0;
		try {
			//crear html
			html = new FileOutputStream("wordCounter.html");
			p = new PrintStream(html);
			System.out.println("Generant HTML...");

			p.println("<html>");
			p.println("<head>");
			p.println("<title> WordCounter </title>");
			p.println("<style> </style>");
			p.println("</head>");
			p.println("<body>");
			//taula cost i temps
			p.println("<table border=5px>");
			p.println("<tr>");
			p.println("<td> Temps llegir text(ms) </td>");
			p.println("<td> Temps guardar info(ms) </td>");
			p.println("<td> Temps extreure dades(ms) </td>");
			p.println("<td> Cost(MB) </td>");
			
			p.println("<tr>");
			p.println("<td>"+timeRead+"</td>");
			p.println("<td>"+timeSave+"</td>");
			p.println("<td>"+timeView+"</td>");
			p.println("<td>"+cost +"</td>");
			p.println("</tr>");
			p.println("</table>");
		
			
			//taula paraules
			p.println("<table border=5px>");
			p.println("<tr>");
			p.println("<td> Num </td>");
			p.println("<td> Paraula </td>");
			p.println("<td> Repeticions </td>");
			
	
			p.println("</tr>");
			//escriure resultats
			for (int i = 0; i < words.length; i++){
				//comprovem que hi hagi alguna cosa escrita
				if(words[i][0] != null && words[i][1] != "-1" && words[i][0] != "" && !words[i][0].isEmpty()){
					
					p.println("<tr>");
					p.println("<td>"+j+"</td>");
					p.println("<td>"+words[i][0] +"</td>");
					p.println("<td>"+words[i][1] +"</td>");
					p.println("</tr>");
					j++;
				}
				
			}
			
			p.println("</table>");
			p.println("</body>");
			p.println("</html>");
			p.close();
			//obrir l'html
			File link = new File("wordCounter.html");
			Desktop.getDesktop().browse(link.toURI());
			//mostrem que s'ha generat
			System.out.println("HTML GENERAT!");

		} catch (FileNotFoundException e){ 
			
			System.out.println("No s'ha trobat el html.");
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
