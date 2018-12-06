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
		ArrayList<String[]> csv_edge = ReadCSV.ReadCSVFile(workspacePath + "\\src\\relation_vertices.csv");
		
		// Declare an ArrayList to store the edges read in the csv file
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		//Populating the arcs ArrayList with the values of the CSV file (CSVarc ArrayList).
		for (int i = 0; i < csv_edge.size(); i++) {
			String name = csv_edge.get(i)[0];
			String typeTransport = csv_edge.get(i)[1];
			Node sourceNode = nodes.get(Integer.valueOf(csv_edge.get(i)[3]) - 1);
			Node destinationNode = nodes.get(Integer.valueOf(csv_edge.get(i)[2]) - 1);
			MobilityType mobility_type;
			//If it's a Ski Lift
			if(typeTransport.startsWith("T")) {
				mobility_type = new SkiLift(i, csv_edge.get(i)[0], csv_edge.get(i)[1]);
				edges.add(new Edge(name, mobility_type, sourceNode, destinationNode));
			}					
			else {
				//If it's a BUS
				if(typeTransport.equals("BUS")) {
					mobility_type = new Bus(i, csv_edge.get(i)[0], csv_edge.get(i)[1]);
					edges.add(new Edge(name, mobility_type, sourceNode, destinationNode));
				}		
				//Else a Ski Slope
				else {
					mobility_type = new SkiSlope(i, csv_edge.get(i)[0], csv_edge.get(i)[1]);
					edges.add(new Edge(name, mobility_type, sourceNode, destinationNode));
				}
			}	
		}
		
		//Create the graph
		Graph graph = new Graph(nodes, edges);
		
		//User IDs with his level
		User user = new User("Raph", "Henin", "Expert");
		
		Dijkstra dijkstra = new Dijkstra(graph, user);
		
		//Define the source node (Id node - 1)
		dijkstra.execute(nodes.get(3));
		
		//Store the path from source to the destination node (Id node - 1)
		LinkedList<Node> path = dijkstra.getPath(nodes.get(10));
		
		//Store the edges covered by Dijkstra
		ArrayList<Edge> arcCoveredByPath = dijkstra.getArcsCovered();
		
		if (path == null) {
			System.out.println("Path does not exist");
		} else {
			for (Node node : path) {
				System.out.println(node.getId());
			}
			double time = 0.0;
			DecimalFormat df = new DecimalFormat(".00");
			for(Edge arc : arcCoveredByPath) {
				System.out.println(arc.getName() + " "+ arc.getMobility_type().getType() + " "+df.format(arc.getTime()));
				time+=arc.getTime();
			}
			System.out.println("Total time = "+df.format(time)+ " minutes");
		}

	}
}
