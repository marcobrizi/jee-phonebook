package phonebook.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nation {

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
