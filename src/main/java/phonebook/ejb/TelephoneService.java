package phonebook.ejb;

import java.util.List;

import javax.ejb.Stateless;

import phonebook.models.Person;
import phonebook.models.Telephone;

@Stateless
public class TelephoneService {

	public List<Telephone> getTelephones(Person person) {
		return null;
	}
	
	public void addTelephone(Person person, Telephone number) {
		
	}
		
}
