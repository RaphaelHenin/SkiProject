public class Bus extends Edge{
	public Bus(String name, Node source, Node destination) {
		super(name, source, destination);
		this.time = calculTime();
		this.transportType = "BUS";
	}

	@Override
	public Double calculTime() {
		if (this.name.equals("navette1600-1800") || this.name.equals("navette1800-1600"))
			time = 30.0;
		else
			time = 40.0;
		return time;
	}
}
