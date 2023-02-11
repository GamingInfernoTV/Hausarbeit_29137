package de.medieninformatik.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class User {

    String userName;

    String password;
    public static String json;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Boolean login() {
        Boolean bool = null;
        switch (userName) {
            case "minf":
                if (Objects.equals(password, "prog3")) bool = true;
                break;
            case "admin":
                if (Objects.equals(password, "admin")) bool = true;
                break;
            default:
                bool = false;
        }
        Objects.requireNonNull(bool);
        return bool;
    }
}
