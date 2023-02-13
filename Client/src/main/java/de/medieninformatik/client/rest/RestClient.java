package de.medieninformatik.client.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.medieninformatik.common.library.Book;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class RestClient {
    private final Client client;
    private final String baseURI;
    private ObjectMapper mapper = new ObjectMapper();
    public RestClient(String baseURI) {
        this.client = ClientBuilder.newClient();
        this.baseURI = baseURI;
    }

    public Response get(String uri) {
        WebTarget webTarget = getTarget("GET", uri);
        return webTarget.request().get();
    }

    public Response postSelect(JSONObject jsonObject, String uri){
        WebTarget target;
        String whereParam = null;
        String query = null;
        if (Objects.equals(jsonObject.getString("whereParam"), "isbn")) {
            whereParam = "isbn";
            query = jsonObject.getString("query");
        }
        if (Objects.equals(jsonObject.getString("whereParam"), "autor")) {
            whereParam = "autor";
            query = jsonObject.getString("query");
        }
        if (whereParam != null && query != null) {
             target = getTarget("POST", uri + "/" + jsonObject.getString("select") + "/" + whereParam + "/" + query);
        } else {
            target = getTarget("POST", uri + "/" + jsonObject.getString("select"));
        }
        Entity<JSONObject> list = Entity.json(jsonObject);
        return target.request(MediaType.APPLICATION_JSON).post(list);
    }

    public Response putInsert(Book book, String uri) {
        WebTarget target = getTarget("PUT", uri);
        Entity<Book> bookEntity = Entity.json(book);
        return target.request(MediaType.APPLICATION_JSON).put(bookEntity);
    }

    public Response postUpdate(String update, String whereParam, String uri) {
        WebTarget target = getTarget("POST", uri + "/" + update + "/" + whereParam);
        Entity<String> entity = Entity.json(update);
        return target.request(MediaType.APPLICATION_JSON).post(entity);
    }

    public Response putDelete(String query, String uri) {
        WebTarget target = getTarget("PUT", uri + "/" + query);
        Entity<String> entity = Entity.json(query);
        return target.request(MediaType.APPLICATION_JSON).put(entity);
    }

    public Response putUser(JSONObject jsonObject, String uri) {
        WebTarget target = getTarget("GET", uri + "/" +
                jsonObject.getString("userName") +
                "/" + jsonObject.getString("password"));
        return target.request(MediaType.APPLICATION_JSON).get();
    }

    private WebTarget getTarget(String crud, String uri) {
        System.out.printf("%n--- %s %s%s%n", crud, baseURI, uri);
        return client.target(baseURI + uri);
    }

    public int status(Response response) {
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        System.out.printf("Status: %d %s%n", code, reason);
        return code;
    }
}
