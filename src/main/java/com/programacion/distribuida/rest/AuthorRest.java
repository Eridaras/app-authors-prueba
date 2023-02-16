package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Authors;
import com.programacion.distribuida.servicios.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {

    @GET
    @Path("/{id}")
    public Authors findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @Inject AuthorRepository repository;

    @GET
    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @POST
    public void insert(Authors obj) {
        repository.persist(obj);
    }

    @PUT
    @Path("/{id}")
    public void update(Authors obj, @PathParam("id") Long id) {

        var author = repository.findById(id);

        author.setFirtName(obj.getFirtName());
        author.setLastName(obj.getLastName());
    }

    @DELETE
    @Path("/{id}")
    public void delete( @PathParam("id") Long id ) {
        repository.deleteById(id);
    }
}
