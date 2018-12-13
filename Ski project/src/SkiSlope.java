public class SkiSlope extends Edge{
	
	public SkiSlope(String name, Node source, Node destination, String skislopeDifficulty) {
		super(name, source, destination, skislopeDifficulty);
		this.time = calculTime();
	}

	@Override
	public Double calculTime() {
		Double time = null;
		int diffAltitudePiste = source.getAltitude() - destination.getAltitude();
		switch (this.transportType) {
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
		return time;
	}
}
