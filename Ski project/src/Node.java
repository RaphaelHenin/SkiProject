public class Node {
	private String name;
	private Integer id;
	private Integer altitude;
 
    public Node(Integer id,String name, Integer altitude) {
        this.name = name;
        this.setId(id);
        this.setAltitude(altitude);
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
}
