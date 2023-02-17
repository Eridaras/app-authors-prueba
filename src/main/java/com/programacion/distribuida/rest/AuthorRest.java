package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Authors;
import com.programacion.distribuida.servicios.AuthorRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Tag(name = "Authors Rest", description = "El API de Author")
public class AuthorRest {

    @Inject AuthorRepository repository;
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    private Integer port;
    @GET
    @Path("/puerto")
    @Operation(
            operationId = "protocolo",
            summary = "obtenemos puerto",
            description = "obetenemos los puertos, para ver el funcionamiento del balanceador"

    )
    @APIResponse(
            responseCode = "200",
            description = "Prueba completada",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Metodo no valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Produces(MediaType.APPLICATION_JSON)
    public String prueba() {
        return "Consumido en el puerto: " + port;
    }



    @GET
    @Path("/{id}")
    @Operation(
            operationId = "Obtener un Author",
            summary = "Un author",
            description = "Obtiene un author"

    )
    @APIResponse(
            responseCode = "200",
            description = "Obtención completada",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Metodo no valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )

    @Produces(MediaType.APPLICATION_JSON)
    //@Retry(maxRetries = 4)
    public Authors findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "OBTENER TODOS",
            summary = "Lista de authors",
            description = "obtenemos todas los authors"

    )
    @APIResponse(
            responseCode = "200",
            description = "Obtención completada",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Método no valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @POST
    @Operation(
            operationId = "CREAR AUTHOR",
            summary = "Se crea un author",
            description = "Ingreso de un nuevo author"

    )
    @APIResponse(
            responseCode = "204",
            description = "Author Creado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Metodo no valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void insert(
            @RequestBody(
                    description = "Author a crear",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Authors.class))
            )
            Authors obj) {
        repository.persist(obj);
    }

    @PUT
    @Path("/{id}")
    @Operation(
            operationId = "ACTUALIZAR AUTHOR",
            summary = "actualiza un author",
            description = "Se actualiza un author existente"

    )
    @APIResponse(
            responseCode = "204",
            description = "Author Actualizado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Metodo no valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(
            @Parameter(
            description = "Author Id",
            required = true
            ) Authors obj, @PathParam("id") Long id) {

        var author = repository.findById(id);
        System.out.println(author.getId());
        author.setFirtName(obj.getFirtName());
        author.setLastName(obj.getLastName());
        System.out.println(author.toString());
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(
            operationId = "BORRAR AUTHORS",
            summary = "borrar un author",
            description = "Se a borrado un author existente"

    )
    @APIResponse(
            responseCode = "204",
            description = "Author Borrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Metodo no valido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public void delete(
            @Parameter(
            description = "Perdona Id",
            required = true
    ) @PathParam("id") Long id ) {
        repository.deleteById(id);
    }
}
