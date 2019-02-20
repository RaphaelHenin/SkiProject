public class Node {
	private String name;
	private Integer id;
	private Integer altitude;
	private Double distance = Double.MAX_VALUE;
	private int x, y;

	public Node(Integer id, String name, Integer altitude) {
		this.name = name;
		this.setId(id);
		this.setAltitude(altitude);
	}

	public Node(Integer id, String name, Integer altitude, int x, int y) {
		this.name = name;
		this.setId(id);
		this.setAltitude(altitude);
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
