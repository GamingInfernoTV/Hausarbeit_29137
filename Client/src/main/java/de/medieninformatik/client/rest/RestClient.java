package de.medieninformatik.client.rest;

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
 * In der Klasse "RestClient"
 */

public class RestClient {
    private final Client client;
    private final String baseURI;

    /**
     * In der Methode "RestClient" wird der ClientBuilder und die URI implementiert.
     * @param baseURI Parameter für die URI
     */
    public RestClient(String baseURI) {
        this.client = ClientBuilder.newClient();
        this.baseURI = baseURI;
    }

    /**
     * In der Methode "putSelect" werden die Filter eingerichtet
     * @param jsonObject Parameter für das JSONObject
     * @param uri Parameter für die URI
     * @return Gibt die Liste wieder
     */
    //h
    public Response putSelect(JSONObject jsonObject, String uri){
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
        if (Objects.equals(jsonObject.getString("whereParam"), "titel")) {
            whereParam = "titel";
            query = jsonObject.getString("query");
        }
        if (Objects.equals(jsonObject.getString("whereParam"), "teilgebiet")) {
            whereParam = "teilgebiet";
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

    /**
     * In der Methode "putInsert" wird das Einfügen in die Tabelle implementiert
     * @param book Parameter für die Tabelle
     * @param uri Parameter für URI
     * @return gibt den Tabelleneintrag wieder
     */
    public Response putInsert(Book book, String uri) {
        WebTarget target = getTarget("PUT", uri);
        Entity<Book> bookEntity = Entity.json(book);
        return target.request(MediaType.APPLICATION_JSON).put(bookEntity);
    }

    /**
     * In der Methode "putUpdate" wird das Updaten von Inhalten implementiert
     * @param update Parameter für Update
     * @param whereParam Parameter
     * @param uri Parameter der URI
     * @return gibt entity (json Update) wieder
     */
    public Response putUpdate(String update, String whereParam, String uri) {
        WebTarget target = getTarget("POST", uri + "/" + update + "/" + whereParam);
        Entity<String> entity = Entity.json(update);
        return target.request(MediaType.APPLICATION_JSON).post(entity);
    }

    /**
     * In der Methode "putDelete" wird das Löschen von Inhalten implementiert
     * @param query Parameter für die Query
     * @param uri Parameter für die URI
     * @return gibt entity (json Update) wieder
     */
    public Response putDelete(String query, String uri) {
        WebTarget target = getTarget("PUT", uri + "/" + query);
        Entity<String> entity = Entity.json(query);
        return target.request(MediaType.APPLICATION_JSON).put(entity);
    }


    /**
     * In der Methode "getTarget" wird die Uri zur BaseUri gefügt
     * @param crud Parameter
     * @param uri Parameter für die URI
     * @return gibt BaseUri+Uri wieder
     */
    private WebTarget getTarget(String crud, String uri) {
       // System.out.printf("%n--- %s %s%s%n", crud, baseURI, uri);
        return client.target(baseURI + uri);
    }

    /**
     * In der Methode "status" wird der Status des Vorgangs überprüft
     * @param response Parameter für die Meldung
     * @return gibt code wieder
     */
    public int status(Response response) {
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        System.out.printf("Status: %d %s%n", code, reason);
        return code;
    }
}
