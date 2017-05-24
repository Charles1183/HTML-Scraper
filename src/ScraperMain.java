/*This code goes to the Bloodborne Wiki Enemies site and scrapes 
 * all the text associated with the enemies listed on the page*/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//Imported Jsoup which is used for the web scraping 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScraperMain {
	private Document doc;
	private Elements block;
	//This array holds the values of the items scraped from the website
	private ArrayList <String> items = new ArrayList<>();
	/*File to write the scraped data to. 
	 * Put the file path in FILENAME. 
	 * The program only write to a file and can not create one. 
	 * The file path must be defined and the file must exist for the program to work.*/
	private String FILENAME = "C:\\Users\\Metal Gear\\Desktop\\Bloodborne Common Enemies.txt";
	private BufferedWriter bw;
	private FileWriter fw;
	
	ScraperMain(){
		bw = null;
		fw = null;
	}
	
	public void doParse(){
		try{
			//The url for the page to be scraped
			doc = Jsoup.connect("http://bloodborne.wiki.fextralife.com/Enemies").get();
			block = doc.getElementsByTag("td");
			for (int i = 0; i<block.size()-1; i++) {
				items.add(block.get(i).text());
			}
			
			} catch (IOException e){
				e.printStackTrace();
			}
	}
	//Writes the scrape items to the file
	public void writeFile() {
		try{
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			//Writes a string of = signs after each enemy entire
			for(int i = 0; i<items.size();i++){
				if((i==0)||((i%10)==0)){
					bw.write("====================================================================================");
					bw.newLine();
					bw.write("#");
					}
				//Writes two dashes to make the enemy data easier to read
				if(items.get(i).equals("Location")||items.get(i).equals("Drops")
						||items.get(i).equals("Weaknesses")||items.get(i).equals("Beast/Kin")){
					bw.write("--");
				}
				bw.write(items.get(i));
				bw.newLine();
				}
			
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				//Finally closes the file when done
			if(bw !=null){bw.close();}
			if(fw !=null){fw.close();}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public static void main(String[] args) {
		
		ScraperMain scrp = new ScraperMain();
		scrp.doParse();
		scrp.writeFile();

	}

}
