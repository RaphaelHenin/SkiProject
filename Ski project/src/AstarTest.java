import java.util.ArrayList;

public class AstarTest {
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	//private Graph graph;
	//private User user;
	
	public void testExecute(){
		// Store the workspace path
		String workspacePath = System.getProperty("user.dir");

		// Store nodes from the CSV "nodes.csv" to an ArrayList (ReadCSVFile
		// function
		// return an arraylist in which, each item is a line of the csv).
		ArrayList<String[]> CSVnodes = ReadCSV.ReadCSVFile(workspacePath + "\\resources\\vertices.csv");

		// Declare an ArrayList to store the nodes read in the csv file
		nodes=new ArrayList<Node>();

		String[] listOfNodes = new String[CSVnodes.size()];

		// Create Nodes and store them in the ArrayList
		// The value is the newest Node which his constructor is Node(Id, Name,
		// Altitude).
		// Name correspond to the second column of the CSV
		// Altitude correspond to the third column of the CSV
		// We need to parse the ID and Altitude to Integer because the CSV
		// return String
		for(int i = 0;i<CSVnodes.size();i++)
		{
			int id = Integer.valueOf(CSVnodes.get(i)[0]);
			String name = CSVnodes.get(i)[1];
			int altitude = Integer.valueOf(CSVnodes.get(i)[2]);
			nodes.add(new Node(id, name, altitude));
			listOfNodes[i] = name;

		}

		// Store relations between each nodes from the CSV "relation_vertices.csv"
		// to an
		// ArrayList
		ArrayList<String[]> csv_edge = ReadCSV.ReadCSVFile(workspacePath + "\\resources\\relation_vertices.csv");

		// Declare an ArrayList to store the edges read in the csv file
		edges=new ArrayList<Edge>();

		// Populating the arcs ArrayList with the values of the CSV file (csv_edge
		// ArrayList).
		for(int i = 0;i<csv_edge.size();i++)
		{
			String name = csv_edge.get(i)[0];
			String typeTransport = csv_edge.get(i)[1];
			Node sourceNode = nodes.get(Integer.valueOf(csv_edge.get(i)[3]) - 1);
			Node destinationNode = nodes.get(Integer.valueOf(csv_edge.get(i)[2]) - 1);
			// If it's a Ski Lift
			if (typeTransport.startsWith("T"))
				edges.add(new SkiLift(name, sourceNode, destinationNode, typeTransport));
			else {
				// If it's a BUS
				if (typeTransport.equals("BUS"))
					edges.add(new Bus(name, sourceNode, destinationNode));
				// Else a Ski Slope
				else
					edges.add(new SkiSlope(name, sourceNode, destinationNode, typeTransport));
			}
		}
		
		Astar a = new Astar(new Graph(nodes, edges));
        Node start = nodes.get(1);
        Node end = nodes.get(9);
        
        //Calculate shortest path to given node for all nodes.
        a.run(start, end);
	}

	
	public static void main(String[] args) {
		new AstarTest().testExecute();
	}
	
}
