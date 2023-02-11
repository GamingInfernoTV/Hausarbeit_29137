package de.medieninformatik.rest;

import de.medieninformatik.common.Book;
import de.medieninformatik.database.LibraryDB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */
//TODO funktionen umbenennen und evtl abändern
@Path("database")
public class DatabaseRest {
    private static final Logger LOGGER = Logger.getLogger("org.glassfish");

    public DatabaseRest() {
        // TODO document why this constructor is empty
    }
//TODO login abändern sodass überprüft wird ob user = teil der userklasse in common
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login() {
        Connection result = LibraryDB.getInstance().getConnection();
        if (result != null) {
            return Response.ok().build();
        }
        return Response.notAcceptable(null).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("select/{select}/{where}/{query}")
    public Response selectData(
            @PathParam("select") String select,
            @PathParam("where") String where,
            @PathParam("query") String query
            ) {
        var result = LibraryDB.selectData(select, where, query);
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("select/{query}")
    public Response selectData(
            @PathParam("query") String select
    ) {
        var result = LibraryDB.selectData(select, "", "");
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("update/{query}/{whereParam}")
    public Response updateData(@PathParam("query") String query, @PathParam("whereParam") String whereParam) {
        var result = LibraryDB.updateData(query, whereParam);
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insertInto")
    public Response insertData(Book book) {
        Boolean result = LibraryDB.insertData(book);
        return Response.ok(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{query}")
    public Response deleteData(@PathParam("query") String query) {
        var result = LibraryDB.deleteData(query);
        return Response.ok(result).build();
    }


//TODO putUser kann gelöscht werden
//TODO testData löschen

}
