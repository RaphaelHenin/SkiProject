import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestReadCSV {

	public static ArrayList<String[]> ReadCSVFile(String path) {
		// TODO Auto-generated method stub
		BufferedReader br;	
		ArrayList<String[]> linesCSV = new ArrayList<String[]>();
		try {
			br = new BufferedReader(new FileReader(path));
			br.readLine(); //Skip header
			String ligne = null;
			while ((ligne = br.readLine()) != null) {
				// Retourner la ligne dans le tableau
				String[] data = ligne.split(";");
				linesCSV.add(data);//add my line to an ArrayList
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linesCSV; //Return the arrayList
	}

}
