import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
	@SuppressWarnings("unused")
	private final List<Node> nodes;
    private final List<Edge> edges;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Double> distance;
    private ArrayList<Edge> edgesCovered;
    private User user;

    public Dijkstra(Graph graph, User user) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
        this.user = user;
    }
    
    //Complexity O(s*(n+(e+a*e))) s : number of "unSettledNodes" / a:size of the "adjacentNode" List / e:size of the "edges" ArrayList
    //n: size of the "unSettledNodes" HashSet
    public void execute(Node source) {
        settledNodes = new HashSet<Node>();
        unSettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Double>();
        predecessors = new HashMap<Node, Node>();
        edgesCovered = new ArrayList<Edge>();
        distance.put(source, 0.);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }
    
    //Comlexity O(n) n: size of the "unSettledNodes" HashSet
    private Node getMinimum(Set<Node> unSetteledNodes) {
        Node minimum = null;
        for (Node node : unSetteledNodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }
    
	private Double getShortestDistance(Node destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }
    
    //Complexity O(e+a*e) a:number of adjacentNode / e:number of edges in the graph
    private void findMinimalDistances(Node node) {
    	List<Node> adjacentNodes = new ArrayList<Node>();
    	if(user.getLevel().equals("Expert"))
    		adjacentNodes = getNeighborsForExpert(node);
    	if(user.getLevel().equals("Beginner"))
    		adjacentNodes = getNeighborsForBeginner(node);
    	if(user.getLevel().equals("Intermediate"))
    		adjacentNodes = getNeighborsForNormal(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getTime(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getTime(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }
    
    private List<Node> getNeighborsForExpert(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }
    
    private List<Node> getNeighborsForBeginner(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination()) && !edge.getTransportType().equals("N") && !edge.getTransportType().equals("R")
                    && !edge.getTransportType().equals("SURF")) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }
    
    private List<Node> getNeighborsForNormal(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination()) && !edge.getTransportType().equals("N")) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }
    
    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }
    
    private Double getTime(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getTime();
            }
        }
        throw new RuntimeException("Take too long time");
    }
    
    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
	//Complexity O(p*e+c+w) p: size of the "predecessors" HashMap / e : size of the edges ArrayList / c : size of the "arcsCovered" ArrayList
	//w : size of the "path" Linkedlist
    public LinkedList<Node> getPath(Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
        	if(user.getLevel().equals("Expert"))
        		edgesCovered.add(getArcCoveredByExpertFor(predecessors.get(step), step));
        	if(user.getLevel().equals("Intermediate"))
        		edgesCovered.add(getArcCoveredByNormalFor(predecessors.get(step), step));
        	if(user.getLevel().equals("Beginner"))
        		edgesCovered.add(getArcCoveredByBeginnerFor(predecessors.get(step), step));
            step = predecessors.get(step);
            path.add(step);           
        }
        // Put it into the correct order
        Collections.reverse(edgesCovered);
        Collections.reverse(path);
        return path;
    }

    private Edge getArcCoveredByExpertFor(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge;
            }
        }
        throw new RuntimeException("Take too long time");
    }
    
    private Edge getArcCoveredByNormalFor(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target) && !edge.getTransportType().equals("N") && !edge.getTransportType().equals("SURF")) {
                return edge;
            }
        }
        throw new RuntimeException("Take too long time");
    }
    
    private Edge getArcCoveredByBeginnerFor(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target) && !edge.getTransportType().equals("N") && !edge.getTransportType().equals("R")
                    && !edge.getTransportType().equals("SURF")) {
                return edge;
            }
        }
        throw new RuntimeException("Take too long time");
    }

    public ArrayList<Edge> getArcsCovered() {
		return edgesCovered;
	}

	public void setArcsCovered(ArrayList<Edge> arcsCovered) {
		this.edgesCovered = arcsCovered;
	}

    
}
