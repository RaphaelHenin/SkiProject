import java.awt.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// Store the workspace path
		String workspacePath = System.getProperty("user.dir");
		// Store nodes from the CSV "nodes.csv" to an ArrayList (ReadCSVFile function
		// return an arraylist
		// in which, each item is a line of the csv).
		ArrayList<String[]> CSVnodes = TestReadCSV.ReadCSVFile(workspacePath + "\\src\\vertices.csv");

		// Declare an ArrayList to store the nodes read in the csv file
		ArrayList<Node> nodes = new ArrayList<Node>();

		// Create Nodes and store them in the ArrayList
		// The value is the newest Node which his constructor is Node(Id, Name,
		// Altitude).
		// Name correspond to the second column of the CSV
		// Altitude correspond to the third column of the CSV
		// We need to parse the ID and Altitude to Integer because the CSV return String
		for (int i = 0; i < CSVnodes.size(); i++) {
			nodes.add(new Node(Integer.valueOf(CSVnodes.get(i)[0]), CSVnodes.get(i)[1], Integer.valueOf(CSVnodes.get(i)[2])));
		}

		// Store relations between each nodes from the CSV "data.csv" to an ArrayList
		ArrayList<String[]> CSVarc = TestReadCSV.ReadCSVFile(workspacePath + "\\src\\relation_vertices.csv");
		ArrayList<Arc> arcs = new ArrayList<Arc>();
		// For each nodes in the ArrayList CSVarc, we associate to them the nodes which it
		// is in relation with.
		// Each node has a Map of his "adjacent" node.
		// We associate them by calling the Node.addDestination function which takes in
		// argument :
		// The associated node (Third column of the csv)
		// The time in minutes to go to the source node to the associated node.
		// The travel time, depend on the type (V,B,R,N, etc..) -- TO DO --
		for (int i = 0; i < CSVarc.size(); i++) {
			arcs.add(new Arc(CSVarc.get(i)[0], CSVarc.get(i)[1],nodes.get(Integer.valueOf(CSVarc.get(i)[3])-1),nodes.get(Integer.valueOf(CSVarc.get(i)[2])-1), 1.0));//Integer.valueOf(destination.get(i)[6])));
		}

		Graph graph = new Graph(nodes, arcs);


		writeInCSVFile.write(arcs, workspacePath+"\\src\\adjacentMatrix.csv");

		
		Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Node> path = dijkstra.getPath(nodes.get(12));

        for (Node node : path) {
            System.out.println(node.getId());
        }

	}
}
