package rest.services;

import rest.services.Model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("people")
public class PersonResource {

    @PersistenceContext(unitName="demoPU")
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll(@QueryParam("page") int page) {
        return em
                .createNamedQuery("person.all", Person.class)
                .setMaxResults(3)
                .setFirstResult((page-1) * 3)
                .getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddPerson(Person person) {
        em.persist(person);
        return Response.ok(person.getId()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int id, Person person){

        Person result = em.createNamedQuery("person.id", Person.class)
                .setParameter("personId", id)
                .getSingleResult();

        if(result==null) {
            return Response.status(404).build();
        }

        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setEmail(person.getEmail());
        result.setGender(person.getGender());
        result.setAge(person.getAge());

        em.persist(result);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {

        Person result = em.createNamedQuery("person.id", Person.class)
                .setParameter("personId", id)
                .getSingleResult();

        em.remove(result);
        return Response.ok(result.getId()).build();
    }

}
