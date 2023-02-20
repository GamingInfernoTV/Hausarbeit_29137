package de.medieninformatik.common.query;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse "Update" wird die Update-Funktion implementiert. Mithilfe eines JSON-Objects werden die bearbeiteten Daten weitergegeben.
 */

public class Update {
    String update;
    String whereISBN;
    private static JSONObject jsonObject;
    public static String json;

    /**
     * In der Methode "Update" werden die Strings Update und whereISBN implementiert
     * @param update Parameter für Update
     * @param whereISBN Parameter für den Where ISBN befehl. Der User muss das zu bearbeitende Buch vorher mit der ISBN angeben.
     */
    public Update(String update, String whereISBN) {
        this.update = update;
        this.whereISBN = whereISBN;
    }

    /**
     * In der  Methode "setJsonObject" wird ein JsonObject erstellt.
     * @param jsonObject Parameter für das JSONObject
     */
    public static void setJsonObject(JSONObject jsonObject) {
        Update.jsonObject = jsonObject;
    }

    /**
     * In der Methode JSONObject wird das JSONObject wiedergegeben.
     * @return Gibt JSONObject wieder
     */
    public static JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * In der Methode "toJSON" wird mithilfe eines Mappers das JSONObject zur Query hinzugefügt
     * @param query Parameter für die Query
     */
    public static void toJSON(Update query) {
       ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = mapper.writeValueAsString(query);
            Update.setJsonObject(new JSONObject(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
