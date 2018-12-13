public class SkiLift extends Edge{
	
	public SkiLift(String name, Node source, Node destination, String skiliftType) {
		super(name, source, destination, skiliftType);
		this.time = calculTime();
	}

	@Override
	public Double calculTime() {
		Double time = null;
		int diffAltitudeRemonterMeca = destination.getAltitude() - source.getAltitude();
		switch (this.transportType) {
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
	return time;
	}
}
