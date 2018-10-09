import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class writeInCSVFile {
	public static void write(ArrayList<Arc> arcs, String path) throws FileNotFoundException {
		File csvFile = new File(path);
		DecimalFormat df = new DecimalFormat(".00");
		String[][] adjacentMatrix = new String[37][37];
		if (!csvFile.exists())
			throw new FileNotFoundException("Le fichier n'existe pas");
		else {
			PrintStream l_out = new PrintStream(new FileOutputStream(path, true));
			for (Arc arc : arcs) {
				adjacentMatrix[arc.getSource().getId()-1][arc.getDestination().getId()-1] = df.format(arc.getTime());
			}
			for(int i = 0;i<adjacentMatrix.length;i++) {
				for(int j = 0; j< adjacentMatrix.length; j++) {
					if(adjacentMatrix[i][j]==null) {
						l_out.print("0;");
					}
					else {
						l_out.print(adjacentMatrix[i][j]+";");
					}
				}
				l_out.println();
			}
			l_out.flush();
			l_out.close();
			l_out = null;
		}
	}
}
