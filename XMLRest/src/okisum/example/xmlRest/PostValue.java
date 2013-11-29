package okisum.example.xmlRest;

/**
 * A class for getters and setters for post
 * */
public class PostValue {
	String username, password,id;
	//id;

	public String getTitle() {
		return username;
	}

	public PostValue(String username, String password, String id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public void setTitle(String title) {
		this.username = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String idd) {
		this.id = idd;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String ppass) {
		this.password = ppass;
	}

}
