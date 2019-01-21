import java.util.ArrayList;
import java.util.Stack;

public class Node implements Comparable<Node>{
	private String name;
	private Integer id;
	private Integer altitude;
	private Double distance = Double.MAX_VALUE;
	private Integer heuristic;
	private Node previous = null;


	public Node(Integer id,String name, Integer altitude) {
        this.name = name;
        this.setId(id);
        this.setAltitude(altitude);
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
	public void setHeuristic(Node destination) {
		this.heuristic = Node.distanceFrom(this, destination);
	}
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
    public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
		
	/*
	 * Static helper function used to print the path of a given destination node.
	 */
	public static Integer distanceFrom(Node n1, Node n2) {
		return n2.getAltitude() - n1.getAltitude();
	}
	
	public static void printPath(Node destination) {
		System.out.println("Total distance traveled: " + ConvertDoubleToTime.displayTime(ConvertDoubleToTime.convertDoubleToTime(destination.getDistance(), new ArrayList<String>())));
		Node current = destination;
		Stack<Node> path = new Stack<Node>();
		path.push(destination);
		
		//Enqueue all path nodes to a stack (so we can easily print in reverse order)
		while(current.getPrevious() != null) {
			current = current.getPrevious();
			path.push(current);
		}
		
		//Print out the path in the correct order
		int i = 0;
		do {
			System.out.println(++i + ": " + path.pop().getName());
		}
		while(!path.isEmpty());
	}

	@Override
	public int compareTo(Node n) {
		return Double.compare(heuristic + distance, n.heuristic + n.distance);
	}
}
