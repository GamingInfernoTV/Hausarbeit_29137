package de.medieninformatik.client.gui;

import de.medieninformatik.client.rest.RestClient;
import de.medieninformatik.common.library.Book;
import de.medieninformatik.common.query.Select;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.json.JSONObject;

import java.util.List;

import java.util.StringJoiner;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse "InterfaceController" werden die Funktionen des Interfaces implementiert.
 */

public class InterfaceController {
    public GridPane structure;

    public TableView<Book> tableView;

    public Pane all;
    public Pane update;
    public Pane insert;
    public Pane delete;
    public Pane select;
    public Pane admin;

    public TextField isbnTextField;
    public TextField autorTextField;

    public TextField deleteTextFieldCheckISBN;
    public TextField updateTextFieldCheckISBN;
    public TextField infotextTextField;
    public TextField userInfoTextField;
    public TextField teilgebieteTextField;
    public TextField titelTextField;

    public TextField updateTextFieldSeitenzahl;
    public TextField updateTextFieldErscheinungsjahr;
    public TextField updateTextFieldVerlag;
    public TextField updateTextFieldTeilgebiet;
    public TextField updateTextFieldAutor;
    public TextField updateTextFieldTitel;
    public TextField updateTextFieldISBN;

    public TextField insertTextFieldTitel;
    public TextField insertTextFieldAutor;
    public TextField insertTextFieldTeilgebiet;
    public TextField insertTextFieldISBN;
    public TextField insertTextFieldVerlag;
    public TextField insertTextFieldErscheinungsjahr;
    public TextField insertTextFieldSeitenzahl;

    public RadioButton radioAll;
    public RadioButton radioISBN;
    public RadioButton radioTitel;
    public RadioButton radioAutor;
    public RadioButton radioVerlag;
    public RadioButton radioSeiten;
    public RadioButton radioTeilgebiet;
    public RadioButton radioJahr;

    public Button deleteBut;
    public Button updateBut;
    public Button insertBut;
    public Button selectBut;


    LogInController logInController = new LogInController();

    private final RestClient restClient = logInController.getRestClient();

    Alert alert = new Alert(Alert.AlertType.ERROR);

    /**
     * In der Methode "showAdmin" wird geregelt, welche Inhalte des Interfaces für den Adminuser sichtbar sein sollen.
     */
    public void showAdmin() {
        all.setVisible(true);
        structure.setVisible(true);
        selectBut.setVisible(true);
        admin.setVisible(true);
        infotextTextField.setVisible(true);
        setAll(new ActionEvent());

    }

    /**
     * In der Methode "showMinf" wird geregelt, welche Inhalte des Interfaces für den Gastuser sichtbar sein sollen.
     */
    public void showMinf() {
        all.setVisible(true);
        admin.setVisible(false);
        structure.setVisible(true);
        selectBut.setVisible(true);
        userInfoTextField.setVisible(true);
        setAll(new ActionEvent());
    }

    /**
     * In der Methode "visibleSelect" wird geregelt, welche Inhalte des Interfaces für den Select-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleSelect(ActionEvent actionEvent) {

        autorTextField.clear();
        isbnTextField.clear();
        teilgebieteTextField.clear();
        titelTextField.clear();
        structure.setVisible(true);
        insert.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        select.setVisible(true);
        infotextTextField.setVisible(false);
        userInfoTextField.setVisible(false);
    }

    /**
     * In der Methode "visibleInsert" wird geregelt, welche Inhalte des Interfaces für den Insert-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleInsert(ActionEvent actionEvent) {

        insertTextFieldTitel.clear();
        insertTextFieldSeitenzahl.clear();
        insertTextFieldISBN.clear();
        insertTextFieldVerlag.clear();
        insertTextFieldErscheinungsjahr.clear();
        insertTextFieldAutor.clear();
        select.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        insert.setVisible(true);
        infotextTextField.setVisible(false);
        userInfoTextField.setVisible(false);
    }

    /**
     * In der Methode "visibleUpdate" wird geregelt, welche Inhalte des Interfaces für den Update-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleUpdate(ActionEvent actionEvent) {
        select.setVisible(false);
        insert.setVisible(false);
        delete.setVisible(false);
        updateTextFieldISBN.clear();
        updateTextFieldTitel.clear();
        updateTextFieldAutor.clear();
        updateTextFieldTeilgebiet.clear();
        updateTextFieldVerlag.clear();
        updateTextFieldErscheinungsjahr.clear();
        updateTextFieldSeitenzahl.clear();
        update.setVisible(true);
        infotextTextField.setVisible(false);
        userInfoTextField.setVisible(false);
    }

    /**
     * In der Methode "visibleDelete" wird geregelt, welche Inhalte des Interfaces für den Delete-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleDelete(ActionEvent actionEvent) {
        select.setVisible(false);
        insert.setVisible(false);
        update.setVisible(false);
        deleteTextFieldCheckISBN.clear();
        delete.setVisible(true);
        infotextTextField.setVisible(false);
        userInfoTextField.setVisible(false);
    }


    /**
     * In der Methode "select" wird die Funktion hinter dem Select-Befehl implementiert.
     * Mit Radiobuttons kann man filtern, welche Inhalte (Spalten) man aus der Datenbank einsehen möchte.
     * Zusätzlich kann man über vier Textfelder direkt nach ISBN, Autor, Titel und/oder Teilgebiet suchen
     * @param actionEvent Parameter für das Action Event
     */
    public void select(ActionEvent actionEvent) {

        tableView.getColumns().clear();
        tableView.getItems().clear();
        String whereParam = "";
        String query = "";

        if (!isbnTextField.getText().isEmpty()) {
            whereParam = "isbn";
            query = isbnTextField.getText();
        }
        if (!autorTextField.getText().isEmpty()){
            whereParam = "autor";
            query = autorTextField.getText();

        }
        if (!titelTextField.getText().isEmpty()) {
            whereParam = "titel";
            query = titelTextField.getText();

        }
        if (!teilgebieteTextField.getText().isEmpty()) {
            whereParam = "teilgebiet";
            query = teilgebieteTextField.getText();
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


        if (radioISBN.isSelected()) {
            sl.add("ISBN");
            isbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
            tableView.getColumns().add(isbn);
        }



        if (radioTitel.isSelected()) {
            sl.add("Titel");
            titel.setCellValueFactory(new PropertyValueFactory<>("Titel"));
            tableView.getColumns().add(titel);
        }


        if (radioAutor.isSelected()) {
            sl.add("Autor");
            autor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
            tableView.getColumns().add(autor);
        }

        if (radioVerlag.isSelected()) {
            sl.add("Verlag");
            verlag.setCellValueFactory(new PropertyValueFactory<>("Verlag"));
            tableView.getColumns().add(verlag);
        }


        if (radioTeilgebiet.isSelected()) {
            sl.add("Teilgebiet");
            teilgebiet.setCellValueFactory(new PropertyValueFactory<>("Teilgebiet"));
            tableView.getColumns().add(teilgebiet);
        }



        if (radioJahr.isSelected()) {
            sl.add("Erscheinungsjahr");
            erscheinungsjahr.setCellValueFactory(new PropertyValueFactory<>("Erscheinungsjahr"));
            tableView.getColumns().add(erscheinungsjahr);
        }


        if (radioSeiten.isSelected()) {
            sl.add("Seitenzahl");
            seitenzahl.setCellValueFactory(new PropertyValueFactory<>("Seitenzahl"));
            tableView.getColumns().add(seitenzahl);
        }

        if (radioAll.isSelected()) {
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
        Response r = restClient.putSelect(jsonObject, "/informatik/select");
        restClient.status(r);
        List<Book> result = r.readEntity(new GenericType<>() {});
        for (Book book : result) {
            tableView.getItems().add(book);
        }

        autorTextField.clear();
        isbnTextField.clear();
        titelTextField.clear();
        teilgebieteTextField.clear();

    }

    /**
     * In der Methode "update" ist die Funktionalität, Inhalte zu updaten, implementiert. Um ein Buch zu updaten, muss es per ISBN ausgewählt werden
     * und kann dann an jeder Stelle verändert werden.
     * @param actionEvent Parameter für das Action Event
     */
    public void update(ActionEvent actionEvent) {
        String update = getString(updateTextFieldISBN, updateTextFieldTitel, updateTextFieldAutor, updateTextFieldTeilgebiet, updateTextFieldVerlag, updateTextFieldErscheinungsjahr, updateTextFieldSeitenzahl);
        String whereParam = null;

        if (updateTextFieldCheckISBN.getText().isEmpty()) {
            alert.show();
        }
        else {
            whereParam = updateTextFieldCheckISBN.getText();
        }

        Response r = restClient.putUpdate(update, whereParam, "/informatik/update");
        restClient.status(r);
        select(actionEvent);
        updateTextFieldISBN.clear();
        updateTextFieldTitel.clear();
        updateTextFieldAutor.clear();
        updateTextFieldTeilgebiet.clear();
        updateTextFieldVerlag.clear();
        updateTextFieldErscheinungsjahr.clear();
        updateTextFieldSeitenzahl.clear();
        this.update.setVisible(false);
    }

    /**
     * In der Methode "delete" ist die Funktionalität, Inhalte zu löschen, implementiert. Um ein Buch zu löschen, muss der User die ISBN des Buches eingeben.
     * @param actionEvent Parameter für das Action Event
     */
    public void delete(ActionEvent actionEvent) {

        if (deleteTextFieldCheckISBN.getText().isEmpty()) {
            alert.show();
        }
        else {
            String query = deleteTextFieldCheckISBN.getText();
            Response r = restClient.putDelete(query, "/informatik/delete");
            restClient.status(r);
        }
        select(actionEvent);
        deleteTextFieldCheckISBN.clear();
        delete.setVisible(false);
    }


    /**
     * In der Methode "insert" ist die Funktionalität, Inhalte hinzuzufügen, implementiert. Um ein Buch hinzuzufügen, muss der User jedes Feld ausfüllen.
     * @param actionEvent Parameter für das Action Event
     */
    public void insert(ActionEvent actionEvent) {
        Book newBook = new Book();
        if (
                insertTextFieldISBN.getText().isEmpty() ||
                        insertTextFieldTitel.getText().isEmpty() ||
                        insertTextFieldAutor.getText().isEmpty() ||
                        insertTextFieldVerlag.getText().isEmpty() ||
                        insertTextFieldErscheinungsjahr.getText().isEmpty() ||
                        insertTextFieldSeitenzahl.getText().isEmpty() ||
                        insertTextFieldTeilgebiet.getText().isEmpty()
        ) {
            alert.show();
        }
        else {
            newBook.setIsbn(insertTextFieldISBN.getText());
            newBook.setTitel(insertTextFieldTitel.getText());
            newBook.setAutor(insertTextFieldAutor.getText());
            newBook.setVerlag(insertTextFieldVerlag.getText());
            newBook.setErscheinungsjahr(Integer.parseInt(insertTextFieldErscheinungsjahr.getText()));
            newBook.setSeitenzahl(Integer.parseInt(insertTextFieldSeitenzahl.getText()));
            newBook.setTeilgebiet(insertTextFieldTeilgebiet.getText());
        }

        Response r = restClient.putInsert(newBook, "/informatik/insert");
        restClient.status(r);
        select(actionEvent);
        insertTextFieldISBN.clear();
        insertTextFieldAutor.clear();
        insertTextFieldVerlag.clear();
        insertTextFieldTitel.clear();
        insertTextFieldTeilgebiet.clear();
        insertTextFieldErscheinungsjahr.clear();
        insertTextFieldSeitenzahl.clear();
        insert.setVisible(false);
    }

//h
    /**
     * In der Methode "removeAll" wird implementiert, dass der "All" RadioButton im select-Befehl deaktiviert wird
     * (Wenn ein anderer RadioButton ausgewählt wird)
     *
     * @param actionEvent Parameter für das Action Event
     */
    public void removeAll(ActionEvent actionEvent) {

        radioAll.setSelected(false);
    }

    /**
     * In der Methode "setAll" wird implementiert, dass die RadioButtons deaktiviert werden, wenn der "All" RadioButton ausgewählt wird
     * @param actionEvent Parameter für das Action Event
     */
    public void setAll(ActionEvent actionEvent) {


        radioAll.setSelected(true);
        radioAutor.setSelected(false);
        radioISBN.setSelected(false);
        radioTitel.setSelected(false);
        radioVerlag.setSelected(false);
        radioSeiten.setSelected(false);
        radioTeilgebiet.setSelected(false);
        radioJahr.setSelected(false);

    }


    /**
     * In der Methode "getString" werden Strings für die Methode "update" generiert.
     * @param field1 Parameter für Textfeld 1
     * @param field2 Parameter für Textfeld 2
     * @param field3 Parameter für Textfeld 3
     * @param field4 Parameter für Textfeld 4
     * @param field5 Parameter für Textfeld 5
     * @param field6 Parameter für Textfeld 6
     * @param field7 Parameter für Textfeld 7
     * @return gibt String wieder
     */
    private String getString(TextField field1, TextField field2, TextField field3, TextField field4, TextField field5, TextField field6, TextField field7) {
        var sj = new StringJoiner(", ", "", "");
        if (!field1.getText().isEmpty()) sj.add("ISBN='" + field1.getText() + "'");
        if (!field2.getText().isEmpty()) sj.add("Titel='" + field2.getText() + "'");
        if (!field3.getText().isEmpty()) sj.add("Autor='" + field3.getText() + "'");
        if (!field4.getText().isEmpty()) sj.add("Teilgebiet='" + field4.getText() + "'");
        if (!field5.getText().isEmpty()) sj.add("Verlag='" + field5.getText() + "'");
        if (!field6.getText().isEmpty()) sj.add("Erscheinungsjahr='" + field6.getText() + "'");
        if (!field7.getText().isEmpty()) sj.add("Seitenzahl='" + field7.getText() + "'");
        return sj.toString();
    }

}
