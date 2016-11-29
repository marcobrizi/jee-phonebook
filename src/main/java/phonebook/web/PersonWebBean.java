package phonebook.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import phonebook.ejb.NationService;
import phonebook.ejb.PersonService;
import phonebook.models.Nation;
import phonebook.models.Person;

@ManagedBean(value = "personWebBean")
@ViewScoped
public class PersonWebBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private NationService nationService;
	@Inject
	private PersonService personService;
	
	private Person personInput;

	private String name;
	private String surname;
	private Boolean hasTelephone;

	private List<Person> personList;
	private List<Nation> nationList;

	public PersonWebBean() {
		personInput = new Person();
		personList = new ArrayList<Person>();
		nationList = nationService.getNations();
	}
	
	public Person getPersonInput() {
		return personInput;
	}

	public void setPersonInput(Person personInput) {
		this.personInput = personInput;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Boolean getHasTelephone() {
		return hasTelephone;
	}

	public void setHasTelephone(Boolean hasTelephone) {
		this.hasTelephone = hasTelephone;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public List<Nation> getNationList() {
		return nationList;
	}

	public void setNationList(List<Nation> nationList) {
		this.nationList = nationList;
	}
}
