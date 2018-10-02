
public class Arc {
	String name;
	Node destination;
	float time;
	String niveauPisteOuModeTransport;
	
	public Arc(String name, String niveauPisteOuModeTransport, Node destination, float time) {
		super();
		this.name = name;
		this.destination = destination;
		this.time = time;
		this.niveauPisteOuModeTransport = niveauPisteOuModeTransport;
	}

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

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}	
}
