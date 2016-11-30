package phonebook.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import phonebook.ejb.NationService;
import phonebook.ejb.PersonService;
import phonebook.models.Nation;
import phonebook.models.Person;

@ManagedBean(name = "personWebBean")
@ViewScoped
public class PersonWebBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private NationService nationService;
	@Inject
	private PersonService personService;

	private String nameInput;
	private String surnameInput;
	private Date birthInput;
	private String nationInput;
	
	private String nameFilter;
	private String surnameFilter;
	private Boolean hasTelephoneFilter;

	private ListDataModel<Person> personListDM;
	private List<SelectItem> nationItems;
	
	private Person selectedPerson;

	@PostConstruct
	public void init() {
		nationItems = new ArrayList<SelectItem>();
		for (Nation nation : nationService.getNations()) {
			nationItems.add(new SelectItem(nation.getName()));
		}
		filter();
	}

	public NationService getNationService() {
		return nationService;
	}

	public void setNationService(NationService nationService) {
		this.nationService = nationService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public String getNameInput() {
		return nameInput;
	}

	public void setNameInput(String nameInput) {
		this.nameInput = nameInput;
	}

	public String getSurnameInput() {
		return surnameInput;
	}

	public void setSurnameInput(String surnameInput) {
		this.surnameInput = surnameInput;
	}

	public Date getBirthInput() {
		return birthInput;
	}

	public void setBirthInput(Date birthInput) {
		this.birthInput = birthInput;
	}

	public String getNationInput() {
		return nationInput;
	}

	public void setNationInput(String nationInput) {
		this.nationInput = nationInput;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public String getSurnameFilter() {
		return surnameFilter;
	}

	public void setSurnameFilter(String surnameFilter) {
		this.surnameFilter = surnameFilter;
	}

	public Boolean getHasTelephoneFilter() {
		return hasTelephoneFilter;
	}

	public void setHasTelephoneFilter(Boolean hasTelephoneFilter) {
		this.hasTelephoneFilter = hasTelephoneFilter;
	}

	public ListDataModel<Person> getPersonListDM() {
		return personListDM;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	// select

	public List<SelectItem> getNationItems() {
		return nationItems;
	}

	// actions

	public void addPerson() {
		Person person = new Person();
		person.setName(nameInput);
		person.setSurname(surnameInput);
		person.setDateOfBirth(birthInput);
		person.setNationOfBirth(nationService.getNation(nationInput));
		
		personService.addPerson(person);
		filter();
	}
	
	public void filter() {
		List<Person> personList = personService.searchPersons(nameFilter, surnameFilter, hasTelephoneFilter);
		personListDM = new ListDataModel<Person>(personList);
	}

	public String selectPerson() {
		selectedPerson = personListDM.getRowData();
		return "showTelephones?faces-redirect=true&includeViewParams=true";
	}
	
	public void addTelephone() {
		
	}
	
	public void back() {
		selectedPerson = null;
	}
	
}
