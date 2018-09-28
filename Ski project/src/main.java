import java.util.ArrayList;
import java.util.HashMap;

public class main {

	public static void main(String[] args) {
		// Store the workspace path
		String workspacePath = System.getProperty("user.dir");
		
		// Store nodes from the CSV "nodes.csv" to an ArrayList (ReadCSVFile function return an arraylist
		// in which, each item is a line of the csv).
		ArrayList<String[]> nodes = TestReadCSV.ReadCSVFile(workspacePath + "\\src\\nodes.csv");

		// Declare an HashMap to store the nodes read in the csv file
		HashMap<Integer, Node> datas = new HashMap<Integer, Node>();
		
		// Create Nodes and store them in the HashTable
		// The key is the ID of the node (correspond to the first column of the CSV
		// The value is the newest Node which his constructor is Node(Id, Name,
		// Altitude).
		// Name correspond to the second column of the CSV
		// Altitude correspond to the third column of the CSV
		// We need to parse the ID and Altitude to Integer because the CSV return String
		for (int i = 0; i < nodes.size(); i++) {
			datas.put(Integer.valueOf(nodes.get(i)[0]),
					new Node(Integer.valueOf(nodes.get(i)[0]), nodes.get(i)[1], Integer.valueOf(nodes.get(i)[2])));
		}

		// Store relations between each nodes from the CSV "data.csv" to an ArrayList
		ArrayList<String[]> destination = TestReadCSV.ReadCSVFile(workspacePath + "\\src\\data.csv");
		
		//For each nodes in the HashMap datas, we associate to them the nodes which it is in relation with.
		//Each node has a Map of his "adjacent" node.
		//We associate them by calling the Node.addDestination function which takes in argument :
		//The associated node (Third column of the csv)
		//The time in minutes to go to the source node to the associated node.
		//The travel time, depend on the type (V,B,R,N, etc..) -- TO DO --
		for (int i = 0; i < destination.size(); i++) {
			datas.get(Integer.valueOf(destination.get(i)[2])).addDestination(
					datas.get(Integer.valueOf(destination.get(i)[3])), Integer.valueOf(destination.get(i)[6]));
		}

		Graph graph = new Graph();

		for (int i = 0; i < datas.size(); i++) {
			graph.addNode(datas.get(i));
		}

	}

}
