import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		// Store the workspace path
		String workspacePath = System.getProperty("user.dir");
		
		// Store nodes from the CSV "nodes.csv" to an ArrayList (ReadCSVFile function
		// return an arraylist in which, each item is a line of the csv).
		ArrayList<String[]> CSVnodes = ReadCSV.ReadCSVFile(workspacePath + "\\src\\vertices.csv");

		// Declare an ArrayList to store the nodes read in the csv file
		ArrayList<Node> nodes = new ArrayList<Node>();

		// Create Nodes and store them in the ArrayList
		// The value is the newest Node which his constructor is Node(Id, Name,
		// Altitude).
		// Name correspond to the second column of the CSV
		// Altitude correspond to the third column of the CSV
		// We need to parse the ID and Altitude to Integer because the CSV return String
		for (int i = 0; i < CSVnodes.size(); i++) {
			nodes.add(new Node(Integer.valueOf(CSVnodes.get(i)[0]), CSVnodes.get(i)[1],
					Integer.valueOf(CSVnodes.get(i)[2])));
		}

		// Store relations between each nodes from the CSV "data.csv" to an ArrayList
		ArrayList<String[]> CSVarc = ReadCSV.ReadCSVFile(workspacePath + "\\src\\relation_vertices.csv");
		
		// Declare an ArrayList to store the edges read in the csv file
		ArrayList<Arc> arcs = new ArrayList<Arc>();
		
		//Populating the arcs ArrayList with the values of the CSV file (CSVarc ArrayList).
		for (int i = 0; i < CSVarc.size(); i++) {
			arcs.add(new Arc(CSVarc.get(i)[0], CSVarc.get(i)[1], nodes.get(Integer.valueOf(CSVarc.get(i)[3]) - 1),
					nodes.get(Integer.valueOf(CSVarc.get(i)[2]) - 1)));
		}
		
		//Create the graph
		Graph graph = new Graph(nodes, arcs);
		
		//User IDs with his level
		User user = new User("Raph", "Henin", "Expert");
		
		Dijkstra dijkstra = new Dijkstra(graph, user);
		
		//Define the source node (Id node - 1)
		dijkstra.execute(nodes.get(3));
		
		//Store the path from source to the destination node (Id node - 1)
		LinkedList<Node> path = dijkstra.getPath(nodes.get(10));
		
		//Store the edges covered by Dijkstra
		ArrayList<Arc> arcCoveredByPath = dijkstra.getArcsCovered();
		
		if (path == null) {
			System.out.println("Path does not exist");
		} else {
			for (Node node : path) {
				System.out.println(node.getId());
			}
			double time = 0.0;
			DecimalFormat df = new DecimalFormat(".00");
			for(Arc arc : arcCoveredByPath) {
				System.out.println(arc.getName() + " "+ arc.getNiveauPisteOuModeTransport() + " "+df.format(arc.getTime()));
				time+=arc.getTime();
			}
			System.out.println("Total time = "+df.format(time)+ " minutes");
		}

	}
}
