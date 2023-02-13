package de.medieninformatik.common;

import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class Login {

    String userName;

    String password;
    public static String json;

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Boolean UserLogin() {
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
