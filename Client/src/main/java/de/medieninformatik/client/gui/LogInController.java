package de.medieninformatik.client.gui;

import de.medieninformatik.client.rest.RestClient;
import de.medieninformatik.common.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse "LogInController" wird die Funktion hinter dem Login implementiert. Der Controller steht in Verbindung mit der Interface.fxml. Je nach User
 * werden andere Funktionen angezeigt.
 */

public class LogInController {

    private static final String baseURI = "http://localhost:8080/rest";
    private final RestClient restClient = new RestClient(baseURI);

    public TextField userField;
    public PasswordField passwordField;
    public Button loginButton;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    /**
     * Methode "LogInController" um die Verbindung zum InterfaceController zu schaffen
     */
    public LogInController() {

    }

    /**
     * In der Methode "getRestClient" wird der restClient wiedergegeben.
     * @return gibt restClient wieder
     */
    public RestClient getRestClient() {
        return restClient;
    }

    /**
     * In der Methode "sendLogin" wird implementiert, welche User sich einloggen können. Benutzername oder Passwort können willkürlich sein.
     * Hier wird auch die Verbindung (Bei erfolgreichem Login) zur Interface.fxml geschaffen.
     * @param actionEvent Parameter für action Event
     * @throws IOException gibt Fehlermeldung wieder
     */
    public void sendLogin(ActionEvent actionEvent) throws IOException {
        Login login = new Login(userField.getText(), passwordField.getText());

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        InterfaceController c = fxmlLoader.getController();

        stage.setTitle("Hausarbeit Datenbankmanagementsystem");
        stage.setScene(scene);
        stage.setResizable(false);


        if (login.UserLogin()) {

            if (Objects.equals(userField.getText(), "admin")
                    && Objects.equals(passwordField.getText(), "admin")) {
                Stage curr = (Stage) loginButton.getScene().getWindow();
                curr.close();
                stage.show();
                c.showAdmin();
            }
            if (Objects.equals(userField.getText(), "minf")
                    && Objects.equals(passwordField.getText(), "prog3")) {
                Stage curr = (Stage) loginButton.getScene().getWindow();
                curr.close();
                stage.show();
                c.showMinf();
            }
        } else {
            alert.setContentText("Benutzername oder Passwort falsch!");
            alert.show();
        }
    }
}
