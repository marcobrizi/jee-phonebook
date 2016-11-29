package phonebook.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import phonebook.ejb.PersonService;
import phonebook.models.Person;

@Path("/persons")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class PersonResource {
 
	@Inject
	private PersonService personSessionBean;
	
    @GET
    public Collection<Person> getPersons(@QueryParam(value="name") String name, 
    		@QueryParam(value="surname") String surname, 
    		@QueryParam(value="hasTelephone") boolean hasTelephone) {
    	return personSessionBean.searchPersons(name, surname, hasTelephone);
    }
    
    @POST
    public void addPerson(Person person) {
    	personSessionBean.addPerson(person);
    }
    
}