package de.medieninformatik.common.library;

import org.json.JSONObject;

import java.util.*;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse Book werden die verschiedenen Spalten in der Datenbank festgelegt. Mithilfe eines JSONObjects werden die Spalten gefüllt.
 */

public class Book {
    private String titel;
    private String autor;
    private String verlag;
    private String isbn;
    private int erscheinungsjahr;
    private int seitenzahl;
    private String teilgebiet;

    /**
     * In "Book" werden alle Inhalte der Tabelle auf null gesetzt.
     */
    public Book() {
        titel = null;
        autor = null;
        verlag = null;
        isbn = null;
        erscheinungsjahr = 0;
        seitenzahl = 0;
        teilgebiet = null;
    }

    /**
     * In der Methode "toString" werden Inhalte zu Strings hinzugefügt.
     * @return gibt String wieder
     */
    @Override
    public String toString() {
        var sj = new StringJoiner("; ", "Book<", ">");
        if (titel != null) sj.add(titel);
        if (autor != null) sj.add(autor);
        if (verlag != null) sj.add(verlag);
        if (isbn != null) sj.add(isbn);
        if (erscheinungsjahr != 0) sj.add("Jahr=" + erscheinungsjahr);
        if (seitenzahl != 0) sj.add("Seitenzahl=" + seitenzahl);
        if (teilgebiet != null) sj.add(teilgebiet);
        return sj.toString();
    }

    /**
     * In der Methode fromJSON wird die Tabelle gefüllt.
     * @param jsonObject Parameter für das JSONObject
     * @return gibt die Tabelle wieder
     */
    public Book fromJSON(JSONObject jsonObject) {
        Book book = new Book();
        jsonObject.keySet().forEach(s -> {
            switch (s) {
                case "titel" -> book.setTitel(jsonObject.getString(s));
                case "isbn" -> book.setIsbn(jsonObject.getString(s));
                case "erscheinungsjahr" -> book.setErscheinungsjahr(jsonObject.getInt(s));
                case "teilgebiet" -> book.setTeilgebiet(jsonObject.getString(s));
                case "seitenzahl" -> book.setSeitenzahl(jsonObject.getInt(s));
                case "autor" -> book.setAutor(jsonObject.getString(s));
                case "verlag" -> book.setVerlag(jsonObject.getString(s));
                default -> throw new IllegalStateException("Unexpected value: " + s);
            }
        });
        return book;
    }

    /**
     * Verschiedene Setter und Getter, für den Inhalt der Tabelle
     *
     */
    public void setTitel(String titel) {                                        //Setter: String für den Titel

        this.titel = titel;
    }
    public String getTitel() {                                                  //Getter: gibt Titel aus

        return titel;
    }
    public void setAutor(String autor) {                                        //Setter: String für den Autor

        this.autor = autor;
    }
    public String getAutor() {                                                  //Getter: gibt Autor aus

        return autor;
    }
    public void setVerlag(String verlag) {                                      //Setter: String für den Verlag

        this.verlag = verlag;
    }
    public String getVerlag() {                                                 //Getter: gibt Verlag aus

        return verlag;
    }
    public void setIsbn(String isbn) {                                          //Setter: String für die ISBN

        this.isbn = isbn;
    }
    public String getIsbn() {                                                   //Getter: gibt ISBN aus

        return isbn;
    }
    public void setErscheinungsjahr(int erscheinungsjahr) {                     //Setter: String für das Erscheinungsjahr

        this.erscheinungsjahr = erscheinungsjahr;
    }
    public int getErscheinungsjahr() {                                          //Getter: gibt Erscheinungsjahr aus

        return erscheinungsjahr;
    }
    public void setSeitenzahl(int seitenzahl) {                                 //Setter: String für die Seitenzahl

        this.seitenzahl = seitenzahl;
    }
    public int getSeitenzahl() {                                                //Getter: gibt Seitenzahl aus

        return seitenzahl;
    }
    public void setTeilgebiet(String teilgebiet) {                              //Setter: String für das Teilgebiet

        this.teilgebiet = teilgebiet;
    }
    public String getTeilgebiet() {                                             //Getter: gibt Teilgebiet aus

        return teilgebiet;
    }

}
