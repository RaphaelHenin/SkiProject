public class User {
	private String name;
	private String firstname;
	private String level;
	
	public User (String name, String firstname, String level) {
		this.name = name;
		this.firstname = firstname;
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	};
	
	
	
}
