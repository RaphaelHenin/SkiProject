import java.util.List;

public class Graph {
	private List<Node> nodes;
	private List<Arc> arcs;
	
	public Graph(List<Node> nodes, List<Arc> arcs) {
        this.nodes = nodes;
        this.arcs = arcs;
    }
	
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Arc> getArcs() {
		return arcs;
	}

	public void setArcs(List<Arc> arcs) {
		this.arcs = arcs;
	}
	
}
