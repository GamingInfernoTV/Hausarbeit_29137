package de.medieninformatik.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse "Interface" wird das Loginfenster aufgerufen und somit auch die Login.fxml
 */
//h
public class Interface extends Application {
    public Stage loginStage;
    private LogInController logInController;


    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(Interface.class.getResource("/Login.fxml"));
        logInController = loader.getController();
        loginStage = stage;
        try {
            Scene loginScene = new Scene(loader.load());
            loginStage.setTitle("Login Datenbankmanagementsystem");
            loginStage.setScene(loginScene);
            loginStage.setResizable(false);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
