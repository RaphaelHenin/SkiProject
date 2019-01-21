import java.util.List;
import java.util.ArrayList;

public class Graph {
	private List<Node> nodes;
	private List<Edge> edges;
	
	public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
	
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public static ArrayList<Node> getNeighbors(List<Edge> edges,Node n) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
		for(Edge edge : edges) {
			if(edge.getSource().equals(n)) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}
	
	public static Double getDistanceFrom(List<Edge> edges, Node start, Node end) {
		for(Edge e : edges) {
			if(e.getSource().equals(start) && e.getDestination().equals(end)) {
				System.out.println(e.getName() + " " +e.getTime());
				return e.getTime();
			}
		}
		return null;
	}
}
