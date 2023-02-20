package de.medieninformatik.common;

import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 * In der Klasse "Login" wird der per Username und Passwort implementierte Login erstellt.
 */

public class Login {

    String userName;

    String password;

    /**
     * In der Methode "Login" werden die Strings für den Benutzernamen und für das Passwort gesetzt.
     * @param userName Parameter für den Benutzernamen
     * @param password Parameter für das Passwort
     */
    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * In der Methode "UserLogin" werden die beiden Benutzer (Admin und Minf) impolementiert.
     * @return Gibt Boolean wieder.
     */
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
