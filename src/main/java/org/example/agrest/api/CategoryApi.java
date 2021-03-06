package org.example.agrest.api;

import io.agrest.Ag;
import io.agrest.DataResponse;
import io.agrest.SimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.example.agrest.persistent.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Tags(value = @Tag(name = "category", description = "API serving bookstore categories"))
@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryApi {

    @Context
    private Configuration config;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation
    public SimpleResponse create(
            @RequestBody(description = "JSON object representing category") String data
    ) {
        return Ag.create(Category.class, config).sync(data);
    }

    @GET
    @Operation(description = "Gets categories per supplied criteria. Supports all Agrest keys")
    public DataResponse<Category> getAll_UriInfo(
            @Context UriInfo uriInfo
    ) {
        return Ag.select(Category.class, config).uri(uriInfo).get();
    }
}
