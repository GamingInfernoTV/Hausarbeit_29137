package de.medieninformatik.client.gui;

import de.medieninformatik.client.rest.RestClient;
import de.medieninformatik.common.User;
import jakarta.ws.rs.core.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class LogInController {

    private static final String baseURI = "http://localhost:8080/rest";
    private final RestClient restClient = new RestClient(baseURI);

    public TextField userField;
    public PasswordField passwordField;
    public Button loginButton;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    public LogInController() {

    }

    public RestClient getRestClient() {
        return restClient;
    }

    public void sendLogin(ActionEvent actionEvent) throws IOException {
        User user = new User(userField.getText(), passwordField.getText());

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GuiController c = fxmlLoader.getController();

        stage.setTitle("Hausarbeit");
        stage.setScene(scene);
        stage.setResizable(false);


        if (user.login()) {

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
