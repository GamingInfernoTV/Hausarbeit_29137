package de.medieninformatik.rest;

import de.medieninformatik.common.library.Book;
import de.medieninformatik.database.DBMSBib;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.sql.Connection;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse "DBMSRest" wird die Verbindung zwischen der Datenbank und der Funktionalität geschaffen.
 */

// TODO funktionen umbenennen und evtl abändern
//h
@Path("informatik")
public class DBMSRest {


//TODO login abändern sodass überprüft wird ob user = teil der userklasse in common
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login() {
        Connection result = DBMSBib.getInstance().getConnection();
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
        var result = DBMSBib.selectData(select, where, query);
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("select/{query}")
    public Response selectData(
            @PathParam("query") String select
    ) {
        var result = DBMSBib.selectData(select, "", "");
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("update/{query}/{whereParam}")
    public Response updateData(@PathParam("query") String query, @PathParam("whereParam") String whereParam) {
        var result = DBMSBib.updateData(query, whereParam);
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insert")
    public Response insertData(Book book) {
        Boolean result = DBMSBib.insertData(book);
        return Response.ok(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{query}")
    public Response deleteData(@PathParam("query") String query) {
        var result = DBMSBib.deleteData(query);
        return Response.ok(result).build();
    }

}
