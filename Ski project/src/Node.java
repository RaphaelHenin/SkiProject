import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {
	private String name;
	private Integer id;
	private Integer altitude;

	private List<Node> shortestPath = new LinkedList<>();
     
    private float distance = Float.MAX_VALUE;
     
    ArrayList<Arc> adjacentNodes = new ArrayList<Arc>();
 
    public Node(Integer id,String name, Integer altitude) {
        this.name = name;
        this.setId(id);
        this.setAltitude(altitude);
    }
    
    public void addDestination(Arc arc) {
        adjacentNodes.add(arc);
    }
    
    public ArrayList<Arc> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(ArrayList<Arc> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
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

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
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
}
