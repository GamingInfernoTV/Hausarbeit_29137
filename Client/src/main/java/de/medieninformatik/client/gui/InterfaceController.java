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

    public TableView<Book> tableView;


    public TextField deleteFieldCheckISBN;
    public TextField infotextfield;
    public TextField userInfoField;
    public TextField teilgebieteField;
    public TextField titelField;
    public RadioButton radioAll;
    public RadioButton radioISBN;
    public RadioButton radioTitel;
    public RadioButton radioAutor;
    public RadioButton radioVerlag;
    public RadioButton radioSeiten;
    public RadioButton radioTeilgebiet;
    public RadioButton radioJahr;



    LogInController logInController = new LogInController();

    private final RestClient restClient = logInController.getRestClient();



    public Pane allPane;
    public Pane updatePane;

    public Pane deletePane;

    public TextField updateFieldCheckISBN;

    public TextField updateSeitenzahl;

    public TextField updateErscheinungsjahr;

    public TextField updateVerlag;

    public TextField updateTeilgebiet;

    public TextField updateAutor;

    public TextField updateTitel;

    public TextField updateISBN;

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

    /**
     * In der Methode "showAdmin" wird geregelt, welche Inhalte des Interfaces für den Adminuser sichtbar sein sollen.
     */
    public void showAdmin() {
        allPane.setVisible(true);
        basePane.setVisible(true);
        selectBut.setVisible(true);
        adminPane.setVisible(true);
        infotextfield.setVisible(true);
        setAll(new ActionEvent());

    }

    /**
     * In der Methode "showMinf" wird geregelt, welche Inhalte des Interfaces für den Gastuser sichtbar sein sollen.
     */
    public void showMinf() {
        allPane.setVisible(true);
        adminPane.setVisible(false);
        basePane.setVisible(true);
        selectBut.setVisible(true);
        userInfoField.setVisible(true);
        setAll(new ActionEvent());
    }

    /**
     * In der Methode "visibleSelect" wird geregelt, welche Inhalte des Interfaces für den Select-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleSelect(ActionEvent actionEvent) {

        autorField.clear();
        isbnField.clear();
        teilgebieteField.clear();
        titelField.clear();
        basePane.setVisible(true);
        insertPane.setVisible(false);
        updatePane.setVisible(false);
        deletePane.setVisible(false);
        selectPane.setVisible(true);
        infotextfield.setVisible(false);
        userInfoField.setVisible(false);
    }

    /**
     * In der Methode "visibleInsert" wird geregelt, welche Inhalte des Interfaces für den Insert-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleInsert(ActionEvent actionEvent) {

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
        infotextfield.setVisible(false);
        userInfoField.setVisible(false);
    }

    /**
     * In der Methode "visibleUpdate" wird geregelt, welche Inhalte des Interfaces für den Update-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleUpdate(ActionEvent actionEvent) {
        selectPane.setVisible(false);
        insertPane.setVisible(false);
        deletePane.setVisible(false);
        updateISBN.clear();
        updateTitel.clear();
        updateAutor.clear();
        updateTeilgebiet.clear();
        updateVerlag.clear();
        updateErscheinungsjahr.clear();
        updateSeitenzahl.clear();
        updatePane.setVisible(true);
        infotextfield.setVisible(false);
        userInfoField.setVisible(false);
    }

    /**
     * In der Methode "visibleDelete" wird geregelt, welche Inhalte des Interfaces für den Delete-Befehl sichtbar sein sollen.
     * @param actionEvent Parameter für das Action Event
     */
    public void visibleDelete(ActionEvent actionEvent) {
        selectPane.setVisible(false);
        insertPane.setVisible(false);
        updatePane.setVisible(false);
        deleteFieldCheckISBN.clear();
        deletePane.setVisible(true);
        infotextfield.setVisible(false);
        userInfoField.setVisible(false);
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

        if (!isbnField.getText().isEmpty()) {
            whereParam = "isbn";
            query = isbnField.getText();
        }
        if (!autorField.getText().isEmpty()){
            whereParam = "autor";
            query = autorField.getText();

        }
        if (!titelField.getText().isEmpty()) {
            whereParam = "titel";
            query = titelField.getText();

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
        System.out.println(jsonObject);
        Response r = restClient.putSelect(jsonObject, "/informatik/select");
        restClient.status(r);
        List<Book> result = r.readEntity(new GenericType<>() {});
        for (Book book : result) {
            tableView.getItems().add(book);
        }

        autorField.clear();
        isbnField.clear();
        titelField.clear();
        teilgebieteField.clear();

    }

    /**
     * In der Methode "update" ist die Funktionalität, Inhalte zu updaten, implementiert. Um ein Buch zu updaten, muss es per ISBN ausgewählt werden
     * und kann dann an jeder Stelle verändert werden.
     * @param actionEvent Parameter für das Action Event
     */
    public void update(ActionEvent actionEvent) {
        String update = getString(updateISBN, updateTitel, updateAutor, updateTeilgebiet, updateVerlag, updateErscheinungsjahr, updateSeitenzahl);
        String whereParam = null;

        if (updateFieldCheckISBN.getText().isEmpty()) {
            alert.show();
        }
        else {
            whereParam = updateFieldCheckISBN.getText();
        }

        Response r = restClient.putUpdate(update, whereParam, "/informatik/update");
        restClient.status(r);
        select(actionEvent);
        updateISBN.clear();
        updateTitel.clear();
        updateAutor.clear();
        updateTeilgebiet.clear();
        updateVerlag.clear();
        updateErscheinungsjahr.clear();
        updateSeitenzahl.clear();
        updatePane.setVisible(false);
    }

    /**
     * In der Methode "delete" ist die Funktionalität, Inhalte zu löschen, implementiert. Um ein Buch zu löschen, muss der User die ISBN des Buches eingeben.
     * @param actionEvent Parameter für das Action Event
     */
    public void delete(ActionEvent actionEvent) {

        if (deleteFieldCheckISBN.getText().isEmpty()) {
            alert.show();
        }
        else {
            String query = deleteFieldCheckISBN.getText();
            Response r = restClient.putDelete(query, "/informatik/delete");
            restClient.status(r);
        }
        select(actionEvent);
        deleteFieldCheckISBN.clear();
        deletePane.setVisible(false);
    }


    /**
     * In der Methode "insert" ist die Funktionalität, Inhalte hinzuzufügen, implementiert. Um ein Buch hinzuzufügen, muss der User jedes Feld ausfüllen.
     * @param actionEvent Parameter für das Action Event
     */
    public void insert(ActionEvent actionEvent) {
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

        Response r = restClient.putInsert(newBook, "/informatik/insert");
        restClient.status(r);
        select(actionEvent);
        insertISBN.clear();
        insertAutor.clear();
        insertVerlag.clear();
        insertTitel.clear();
        insertTeilgebiet.clear();
        insertErscheinungsjahr.clear();
        insertSeitenzahl.clear();
        insertPane.setVisible(false);
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
