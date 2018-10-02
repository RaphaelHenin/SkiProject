import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class writeInCSVFile {
	public static void write(Node node, String path) throws FileNotFoundException {
		File csvFile = new File(path);
		if (!csvFile.exists())
			throw new FileNotFoundException("Le fichier n'existe pas");
		else {
			PrintStream l_out = new PrintStream(new FileOutputStream(path, true));
			for (int i = 1; i < 38; i++) {
				String connected ="";
				for (Arc arc : node.getAdjacentNodes()) {
					if (arc.destination.getId() == i)
						connected += "1";
					else
						connected += "0";
				}	
				if(connected.contains("1"))
					if(i<=36)
						l_out.print("1,");
					else
						l_out.print("1");
				else
					if(i<=36)
						l_out.print("0,");
					else
						l_out.print("0");
			}
			l_out.println();
			l_out.flush();
			l_out.close();
			l_out = null;
		}
	}
}
