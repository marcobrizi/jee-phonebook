package phonebook.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import phonebook.models.Person;
import phonebook.models.Telephone;

@Stateless
public class TelephoneService {

	@PersistenceContext(unitName = "persistenceUnit")
	EntityManager em;
	
	public List<Telephone> getTelephones(Person person) {
		return em.createQuery("select t from Telephone where t.person.id = :personId", Telephone.class)
			.setParameter("personId", person.getId())
			.getResultList();
	}
	
	public void addTelephone(Person person, Long number) {
		Telephone telephone = new Telephone();
		telephone.setPerson(person);
		telephone.setNumber(number);
		em.persist(telephone);
	}
	
	public void deleteTelephone(Telephone telephone) {
		
		em.createQuery("delete from Telephone t where t.id = :telephoneId")
			.setParameter("telephoneId", telephone.getId())
			.executeUpdate();
	}
		
}
