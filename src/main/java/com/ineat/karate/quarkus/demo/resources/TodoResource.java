package com.ineat.karate.quarkus.demo.resources;

import com.ineat.karate.quarkus.demo.dtos.TodoDTO;
import com.ineat.karate.quarkus.demo.repositories.TodoPanacheRepository;
import com.ineat.karate.quarkus.demo.services.TodoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "todo")
public class TodoResource {

    @Inject
    TodoService todoService;

    @GET
    @Operation(description = "Get todos")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Todos has been found"),
            @APIResponse(responseCode = "404", description = "Todo not found")
    })
    public Response get(@QueryParam("title") Optional<String> title) {
        List<TodoDTO> todos = todoService.find(title);
        return Response
                .status(Response.Status.OK)
                .entity(todos)
                .build();
    }

    @GET
    @Path("/{id}")
    @Operation(description = "Get todo by id")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Todo has been found"),
            @APIResponse(responseCode = "404", description = "Todo not found")
    })
    public Response getById(@PathParam("id") String id) {
        return Response
                .status(Response.Status.OK)
                .entity(todoService.getById(id))
                .build();
    }

    @POST
    @Operation(description = "Create todo")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Created")
    })
    public Response create(@Valid TodoDTO todoDTO) {
        String id = todoService.create(todoDTO);
        return Response
                .status(Response.Status.CREATED)
                .location(URI.create("todos/" + id))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete todo")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Deleted"),
            @APIResponse(responseCode = "404", description = "Not found")
    })
    public Response delete(@PathParam("id") String id) {
        return Optional.of(todoService.delete(id))
                .filter(result -> result)
                .map(result -> Response.status(Response.Status.NO_CONTENT).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
