package phonebook.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import phonebook.models.Person;
import phonebook.models.Telephone;

@Stateless
public class PersonService {

	@PersistenceContext(unitName = "persistenceUnit")
	EntityManager em;

	/**
	 * Add a new person
	 * @param person
	 */
	public void addPerson(Person person) {
		em.persist(person);
	}
	
	public Person getPerson(Long id) {
		return em.find(Person.class, id);
	}

	/**
	 * search person filtering by name, surname or telephone presence
	 * @param name
	 * @param surname
	 * @param hasTelephone
	 * @return
	 */
	public List<Person> searchPersons(String name, String surname, Boolean hasTelephone) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> personRoot = criteriaQuery.from(Person.class);
		
		criteriaQuery.select(personRoot);
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if (name != null) {
	        criteria.add(criteriaBuilder.like(personRoot.<String>get("name"),"%"+name+"%"));
		}
		if (surname != null) {
	        criteria.add(criteriaBuilder.like(personRoot.<String>get("surname"),"%"+surname+"%"));
		}
		if (hasTelephone != null && hasTelephone) {
			Subquery<Telephone> telephoneSubquery = criteriaQuery.subquery(Telephone.class);
			Root<Telephone> telephoneRoot = telephoneSubquery.from(Telephone.class);
			Join<Telephone,Person> telephoneJoin = personRoot.join("telephones");
			telephoneSubquery.select(telephoneRoot);
			telephoneSubquery.where(criteriaBuilder.equal(telephoneJoin, personRoot));
			
			criteria.add(criteriaBuilder.exists(telephoneSubquery));
		}
		
		if (criteria.size() == 1) {
			criteriaQuery.where(criteria.get(0));
	    } else {
	    	criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
	    }
		
		return em.createQuery(criteriaQuery).getResultList();
		
	}

}
