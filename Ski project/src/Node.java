import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
	private String name;
	private Integer id;
	private Integer altitude;

	private List<Node> shortestPath = new LinkedList<>();
     
    private Integer distance = Integer.MAX_VALUE;
     
    Map<Node, Integer> adjacentNodes = new HashMap<>();
 
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
  
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

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
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
	
	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
}
