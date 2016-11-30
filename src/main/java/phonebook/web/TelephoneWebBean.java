package phonebook.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import phonebook.ejb.PersonService;
import phonebook.ejb.TelephoneService;
import phonebook.models.Person;
import phonebook.models.Telephone;

@ManagedBean(name = "telephoneWebBean")
@ViewScoped
public class TelephoneWebBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TelephoneService telephoneService;
	@Inject
	private PersonService personService;

	private Long numberInput;
	
	private Long personId;
	private Person selectedPerson;
	
	private ListDataModel<Telephone> telephoneListDM;
	Telephone selectedTelephone;
	
	public Long getPersonId() {
		return personId;
	}
	
	public void setPersonId(Long personId) {
		this.personId = personId;
		selectedPerson = personService.getPerson(personId);
		telephoneListDM = new ListDataModel<Telephone>(selectedPerson.getTelephones());
	}
	
	public Person getSelectedPerson() {
		return selectedPerson;
	}
	
	public Long getNumberInput() {
		return numberInput;
	}
	
	public void setNumberInput(Long numberInput) {
		this.numberInput = numberInput;
	}
	
	public ListDataModel<Telephone> getTelephoneListDM() {
		return telephoneListDM;
	}
	
	public void addTelephone() {
		telephoneService.addTelephone(selectedPerson, numberInput);
		selectedPerson = personService.getPerson(personId);
		telephoneListDM = new ListDataModel<Telephone>(selectedPerson.getTelephones());
	}
	
	public void selectTelephone() {
		selectedTelephone = telephoneListDM.getRowData();
	}
	
	public void deleteTelephone() {
		telephoneService.deleteTelephone(selectedTelephone);
		selectedPerson = personService.getPerson(personId);
		telephoneListDM = new ListDataModel<Telephone>(selectedPerson.getTelephones());
	}
	
}
