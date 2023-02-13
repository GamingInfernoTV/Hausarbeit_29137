package de.medieninformatik.client.gui;

import de.medieninformatik.client.rest.RestClient;
import de.medieninformatik.common.library.Book;
import de.medieninformatik.common.query.Select;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

import java.util.List;

import java.util.StringJoiner;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class InterfaceController {

    public TableView<Book> tableView;

    public CheckBox selectTeilgebiet;

    public CheckBox selectSeiten;

    public CheckBox selectJahr;

    public CheckBox selectVerlag;

    public CheckBox selectISBN;

    public CheckBox selectAutor;

    public CheckBox selectTitel;

    public CheckBox selectAll;

    public TextField deleteField;
    public TextField infotextfield;
    public TextField userInfoField;
    public TextField teilgebieteField;
    public TextField verlagField;


    LogInController logInController = new LogInController();

    private final RestClient restClient = logInController.getRestClient();




    public Pane updatePane;

    public Pane deletePane;

    public TextField updateWhereColumnField;

    public TextField updateSetSeitenzahl;

    public TextField updateSetErscheinungsjahr;

    public TextField updateSetVerlag;

    public TextField updateSetTeilgebiet;

    public TextField updateSetAutor;

    public TextField updateSetTitel;

    public TextField updateSetISBN;

    public Button deleteBut;

    public Button updateBut;

    public Button insertBut;

    public Button selectBut;

    public TextField insertTitel;

    public TextField insertAutor;

    public TextField insertTeilgebiet;

    public TextField insertISBN;

    public TextField insertVerlag;

    public TextField insertErscheinungsjahr;

    public TextField insertSeitenzahl;

    public Pane insertPane;

    public GridPane basePane;

    public Pane adminPane;

    public TextField isbnField;

    public TextField autorField;

    public Pane selectPane;


    Alert alert = new Alert(Alert.AlertType.ERROR);


    public void showAdmin() {
        basePane.setVisible(true);
        selectBut.setVisible(true);
        adminPane.setVisible(true);
        infotextfield.setVisible(true);
        setAll(new ActionEvent());

    }

    public void showInsert(ActionEvent actionEvent) {

        insertTitel.clear();
        insertSeitenzahl.clear();
        insertISBN.clear();
        insertVerlag.clear();
        insertErscheinungsjahr.clear();
        insertAutor.clear();
        selectPane.setVisible(false);
        updatePane.setVisible(false);
        deletePane.setVisible(false);
        insertPane.setVisible(true);
    }

    public void showMinf() {

        adminPane.setVisible(false);
        setAll(new ActionEvent());
        basePane.setVisible(true);
        selectBut.setVisible(true);
        userInfoField.setVisible(true);
    }


    public void showSelect(ActionEvent actionEvent) {

        autorField.clear();
        isbnField.clear();
        teilgebieteField.clear();
        verlagField.clear();
        basePane.setVisible(true);
        insertPane.setVisible(false);
        updatePane.setVisible(false);
        deletePane.setVisible(false);
        selectPane.setVisible(true);
    }

    public void sendSelect(ActionEvent actionEvent) {

        tableView.getColumns().clear();
        tableView.getItems().clear();
        String whereParam = "";
        String query = "";
        /*if (isbnField.isVisible()){
            if (isbnField.getText().isEmpty()) {
                alert.setContentText("ISBN fehlt!");
                alert.show();
            } else {
                whereParam = "isbn";
                query = isbnField.getText();
            }
        }*/
        if (!isbnField.getText().isEmpty()) {
            whereParam = "isbn";
            query = isbnField.getText();
        }
        if (!autorField.getText().isEmpty()){
            whereParam = "autor";
            query = autorField.getText();

        }
        if (!verlagField.getText().isEmpty()) {
            whereParam = "verlag";
            query = verlagField.getText();

        }
        if (!teilgebieteField.getText().isEmpty()) {
            whereParam = "teilgebiet";
            query = teilgebieteField.getText();
        }



        String select;
        TableColumn<Book, String> isbn = new TableColumn<>("Isbn");
        TableColumn<Book, String> titel = new TableColumn<>("Titel");
        TableColumn<Book, String> autor = new TableColumn<>("Autor");
        TableColumn<Book, String> verlag = new TableColumn<>("Verlag");
        TableColumn<Book, String> teilgebiet = new TableColumn<>("Teilgebiet");
        TableColumn<Book, String> erscheinungsjahr = new TableColumn<>("Erscheinungsjahr");
        TableColumn<Book, String> seitenzahl = new TableColumn<>("Seitenzahl");

        var sl = new StringJoiner(", ");

        if (selectISBN.isSelected()) {
            sl.add("ISBN");
            isbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
            tableView.getColumns().add(isbn);
        }

        if (selectTitel.isSelected()) {
            sl.add("Titel");
            titel.setCellValueFactory(new PropertyValueFactory<>("Titel"));
            tableView.getColumns().add(titel);
        }

        if (selectAutor.isSelected()) {
            sl.add("Autor");
            autor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
            tableView.getColumns().add(autor);
        }

        if (selectVerlag.isSelected()) {
            sl.add("Verlag");
            verlag.setCellValueFactory(new PropertyValueFactory<>("Verlag"));
            tableView.getColumns().add(verlag);
        }

        if (selectTeilgebiet.isSelected()) {
            sl.add("Teilgebiet");
            teilgebiet.setCellValueFactory(new PropertyValueFactory<>("Teilgebiet"));
            tableView.getColumns().add(teilgebiet);
        }

        if (selectJahr.isSelected()) {
            sl.add("Erscheinungsjahr");
            erscheinungsjahr.setCellValueFactory(new PropertyValueFactory<>("Erscheinungsjahr"));
            tableView.getColumns().add(erscheinungsjahr);
        }

        if (selectSeiten.isSelected()) {
            sl.add("Seitenzahl");
            seitenzahl.setCellValueFactory(new PropertyValueFactory<>("Seitenzahl"));
            tableView.getColumns().add(seitenzahl);
        }

        if (selectAll.isSelected()) {
            select = "*";
            isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            tableView.getColumns().add(isbn);
            titel.setCellValueFactory(new PropertyValueFactory<>("titel"));
            tableView.getColumns().add(titel);
            autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableView.getColumns().add(autor);
            verlag.setCellValueFactory(new PropertyValueFactory<>("verlag"));
            tableView.getColumns().add(verlag);
            teilgebiet.setCellValueFactory(new PropertyValueFactory<>("teilgebiet"));
            tableView.getColumns().add(teilgebiet);
            erscheinungsjahr.setCellValueFactory(new PropertyValueFactory<>("erscheinungsjahr"));
            tableView.getColumns().add(erscheinungsjahr);
            seitenzahl.setCellValueFactory(new PropertyValueFactory<>("seitenzahl"));
            tableView.getColumns().add(seitenzahl);
        }
        else {
            select = sl.toString();
        }

        Select pick = new Select(select, whereParam, query);
        Select.toJSON(pick);
        JSONObject jsonObject = Select.getJsonObject();
        System.out.println(jsonObject);
        Response r = restClient.postSelect(jsonObject, "/database/select");
        restClient.status(r);
        List<Book> result = r.readEntity(new GenericType<>() {});
        for (int i = 0; i < result.size(); i++) {
            Book book = result.get(i);
            tableView.getItems().add(book);
        }

        autorField.clear();
        isbnField.clear();
        verlagField.clear();
        teilgebieteField.clear();

    }


    public void sendUpdate(ActionEvent actionEvent) {
        String update = getString(updateSetISBN, updateSetTitel, updateSetAutor, updateSetTeilgebiet, updateSetVerlag, updateSetErscheinungsjahr,updateSetSeitenzahl);
        String whereParam = null;

        if (updateWhereColumnField.getText().isEmpty()) {
            alert.setContentText("WHERE Condition darf nicht leer sein!");
            alert.show();
        }
        else {
            whereParam = updateWhereColumnField.getText();
        }

        Response r = restClient.postUpdate(update, whereParam, "/database/update");
        restClient.status(r);
        sendSelect(actionEvent);
        updateSetISBN.clear();
        updateSetTitel.clear();
        updateSetAutor.clear();
        updateSetTeilgebiet.clear();
        updateSetVerlag.clear();
        updateSetErscheinungsjahr.clear();
        updateSetSeitenzahl.clear();
        updatePane.setVisible(false);
    }

    private String getString(TextField tf1, TextField tf2, TextField tf3, TextField tf4, TextField tf5, TextField tf6, TextField tf7) {
        var sj = new StringJoiner(", ", "", "");
        if (!tf1.getText().isEmpty()) sj.add("ISBN='" + tf1.getText() + "'");
        if (!tf2.getText().isEmpty()) sj.add("Titel='" + tf2.getText() + "'");
        if (!tf3.getText().isEmpty()) sj.add("Autor='" + tf3.getText() + "'");
        if (!tf4.getText().isEmpty()) sj.add("Teilgebiet='" + tf4.getText() + "'");
        if (!tf5.getText().isEmpty()) sj.add("Verlag='" + tf5.getText() + "'");
        if (!tf6.getText().isEmpty()) sj.add("Erscheinungsjahr='" + tf6.getText() + "'");
        if (!tf7.getText().isEmpty()) sj.add("Seitenzahl='" + tf7.getText() + "'");
        return sj.toString();
    }

    public void sendDelete(ActionEvent actionEvent) {

        if (deleteField.getText().isEmpty()) {
            alert.setContentText("Delete Condition darf nicht leer sein!");
            alert.show();
        }
        else {
            String query = deleteField.getText();
            Response r = restClient.putDelete(query, "/database/delete");
            restClient.status(r);
        }
        sendSelect(actionEvent);
        deleteField.clear();
        deletePane.setVisible(false);
    }

    public void showUpdate(ActionEvent actionEvent) {
        selectPane.setVisible(false);
        insertPane.setVisible(false);
        deletePane.setVisible(false);
        updateSetISBN.clear();
        updateSetTitel.clear();
        updateSetAutor.clear();
        updateSetTeilgebiet.clear();
        updateSetVerlag.clear();
        updateSetErscheinungsjahr.clear();
        updateSetSeitenzahl.clear();
        updatePane.setVisible(true);
    }

    public void removeAll(ActionEvent actionEvent) {
        selectAll.setSelected(false);
    }

    public void setAll(ActionEvent actionEvent) {
        selectAutor.setSelected(false);
        selectISBN.setSelected(false);
        selectTitel.setSelected(false);
        selectVerlag.setSelected(false);
        selectSeiten.setSelected(false);
        selectTeilgebiet.setSelected(false);
        selectJahr.setSelected(false);
        selectAll.setSelected(true);
    }

    public void sendInsert(ActionEvent actionEvent) {
        Book newBook = new Book();
        if (
                insertISBN.getText().isEmpty() ||
                        insertTitel.getText().isEmpty() ||
                        insertAutor.getText().isEmpty() ||
                        insertVerlag.getText().isEmpty() ||
                        insertErscheinungsjahr.getText().isEmpty() ||
                        insertSeitenzahl.getText().isEmpty() ||
                        insertTeilgebiet.getText().isEmpty()
        ) {
            alert.setContentText("Es müssen alle Felder ausgefüllt werden!");
            alert.show();
        }
        else {
            newBook.setIsbn(insertISBN.getText());
            newBook.setTitel(insertTitel.getText());
            newBook.setAutor(insertAutor.getText());
            newBook.setVerlag(insertVerlag.getText());
            newBook.setErscheinungsjahr(Integer.parseInt(insertErscheinungsjahr.getText()));
            newBook.setSeitenzahl(Integer.parseInt(insertSeitenzahl.getText()));
            newBook.setTeilgebiet(insertTeilgebiet.getText());
        }

        Response r = restClient.putInsert(newBook, "/database/insertInto");
        restClient.status(r);
        sendSelect(actionEvent);
        insertISBN.clear();
        insertAutor.clear();
        insertVerlag.clear();
        insertTitel.clear();
        insertTeilgebiet.clear();
        insertErscheinungsjahr.clear();
        insertSeitenzahl.clear();
        insertPane.setVisible(false);
    }

    public void showDelete(ActionEvent actionEvent) {
        selectPane.setVisible(false);
        insertPane.setVisible(false);
        updatePane.setVisible(false);
        deleteField.clear();
        deletePane.setVisible(true);
    }
}
