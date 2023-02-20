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
 * In der Klasse "Select" wird die Select-Funktion implementiert. Mithilfe eines JSON-Objects werden die bearbeiteten Daten weitergegeben.
 */

public class Select {
    String select;
    String whereParam;
    String query;
    private static JSONObject jsonObject;
    public static String json;

    /**
     * In der Methode "Select" werden die Strings Select, WhereParam und query implementiert.
     * @param select Parameter für Select
     * @param whereParam Parameter für whereParam
     * @param query Parameter für die Query
     */
    public Select(String select, String whereParam, String query) {
        this.select = select;
        this.whereParam = whereParam;
        this.query = query;
    }

    /**
     * In der Methode "setJsonObject" wird das JSONObject implementiert.
     * @param jsonObject Parameter für das JSONObject
     */
    public static void setJsonObject(JSONObject jsonObject) {
        Select.jsonObject = jsonObject;
    }

    /**
     * In der Methode "JSONObject" wird das JSONObject wiedergegeben.
     * @return gibt JSONObject wieder.
     */
    public static JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * In der Methode "toJSON" wird mithilfe eines Mappers das JSONObject zur Query hinzugefügt
     * @param query Parameter für die Query
     */
    public static void toJSON(Select query) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = mapper.writeValueAsString(query);
            Select.setJsonObject(new JSONObject(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
