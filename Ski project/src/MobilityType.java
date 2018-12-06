public class MobilityType {
	private String type;
	private String name;
	private int id;
	
	public MobilityType(int id, String name, String type) {
		this.type = type;
		this.name = name;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
