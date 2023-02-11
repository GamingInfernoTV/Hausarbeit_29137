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

public class SelectQuery {
    String select;
    String whereParam;
    String query;
    private static JSONObject jsonObject;
    public static String json;


    public SelectQuery(String select, String whereParam, String query) {
        this.select = select;
        this.whereParam = whereParam;
        this.query = query;
    }

    public static void setJsonObject(JSONObject jsonObject) {
        SelectQuery.jsonObject = jsonObject;
    }

    public static JSONObject getJsonObject() {
        return jsonObject;
    }

    public static void toJSON(SelectQuery query) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = mapper.writeValueAsString(query);
            SelectQuery.setJsonObject(new JSONObject(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
