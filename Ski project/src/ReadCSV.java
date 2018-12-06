import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {

	public static ArrayList<String[]> ReadCSVFile(String path) {
		BufferedReader bufferReader;	
		ArrayList<String[]> linesCSV = new ArrayList<String[]>();
		try {
			bufferReader = new BufferedReader(new FileReader(path));
			bufferReader.readLine(); //Skip header
			String ligne = null;
			while ((ligne = bufferReader.readLine()) != null) {
				//Return the line in the table
				String[] data = ligne.split(";");
				linesCSV.add(data);//add my line to an ArrayList
			}
			bufferReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return linesCSV; //Return the arrayList
	}

}
