package phonebook.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import phonebook.models.Nation;

@Stateless
public class NationService {

	@PersistenceContext(unitName = "persistenceUnit")
	EntityManager em;
	
	public List<Nation> getNations() {
		return em.createQuery("select n from Nation n", Nation.class)
			.getResultList();
	}
	
	public Nation getNation(String name) {
		return em.find(Nation.class, name);
	}
		
}
