package de.medieninformatik.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class UpdateQuery {
    String update;
    String whereISBN;
    private static JSONObject jsonObject;
    public static String json;

    public UpdateQuery(String update, String whereISBN) {
        this.update = update;
        this.whereISBN = whereISBN;
    }

    public static void setJsonObject(JSONObject jsonObject) {
        UpdateQuery.jsonObject = jsonObject;
    }

    public static JSONObject getJsonObject() {
        return jsonObject;
    }

    public static void toJSON(UpdateQuery query) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = mapper.writeValueAsString(query);
            UpdateQuery.setJsonObject(new JSONObject(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
