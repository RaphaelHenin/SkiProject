public class Edge {
	private String name;
	private Node destination;
	private Node source;
	private Double time;
	private MobilityType mobility_type;

	public MobilityType getMobility_type() {
		return mobility_type;
	}

	public void setMobility_type(MobilityType mobility_type) {
		this.mobility_type = mobility_type;
	}

	public Edge(String name, MobilityType mobility_type, Node source, Node destination) {
		super();
		this.name = name;
		this.destination = destination;
		this.source = source;
		this.mobility_type = mobility_type;
		this.time = calculTime();
	}

	private Double calculTime() {
		Double time = null;
		int diffAltitudePiste = source.getAltitude() - destination.getAltitude();
		int diffAltitudeRemonterMeca = -diffAltitudePiste;
		if (Bus.class.isInstance(mobility_type)) {
			if (name.equals("navette1600-1800") || name.equals("navette1800-1600"))
				time = 30.0;
			else
				time = 40.0;
		}
		if (SkiSlope.class.isInstance(mobility_type)) {
			switch (mobility_type.getType()) {
			case "V":
				time = 0.05 * (diffAltitudePiste);
				break;
			case "B":
				time = 0.04 * (diffAltitudePiste);
				break;
			case "R":
				time = 0.03 * (diffAltitudePiste);
				break;
			case "N":
				time = 0.02 * (diffAltitudePiste);
				break;
			case "KL":
				time = (10.0 / 60) * (diffAltitudePiste);
				break;
			case "SURF":
				time = 0.1 * (diffAltitudePiste);
				break;
			default:
				time = null;
				break;
			}
		}
		if (SkiLift.class.isInstance(mobility_type)) {
			switch (mobility_type.getType()) {
			case "TPH":
				time = 4 + 0.02 * (diffAltitudeRemonterMeca);
				break;
			case "TC":
				time = 2 + 0.03 * (diffAltitudeRemonterMeca);
				break;
			case "TSD":
				time = 1 + 0.03 * (diffAltitudeRemonterMeca);
				break;
			case "TS":
				time = 1 + 0.04 * (diffAltitudeRemonterMeca);
				break;
			case "TK":
				time = 1 + 0.04 * (diffAltitudeRemonterMeca);
				break;
			default:
				time = null;
				break;
			}
		}
		return time;
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
}
