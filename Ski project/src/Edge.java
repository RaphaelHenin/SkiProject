public abstract class Edge {
	protected String name;
	protected Node destination;
	protected Node source;
	protected Double time;
	protected String transportType;

	public Edge(String name, Node source, Node destination) {
		this.name = name;
		this.destination = destination;
		this.source = source;
	}

	public Edge(String name, Node source, Node destination, String transportType) {
		this.name = name;
		this.destination = destination;
		this.source = source;
		this.transportType = transportType;
	}
	
	public abstract Double calculTime();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}
	
	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
}
