package de.medieninformatik.common.library;

import org.json.JSONObject;

import java.util.*;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class Book {
    private String titel;
    private String autor;
    private String verlag;
    private String isbn;
    private int erscheinungsjahr;
    private int seitenzahl;
    private String teilgebiet;


    public Book() {
        titel = null;
        autor = null;
        verlag = null;
        isbn = null;
        erscheinungsjahr = 0;
        seitenzahl = 0;
        teilgebiet = null;
    }

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


    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setVerlag(String verlag) {
        this.verlag = verlag;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setErscheinungsjahr(int erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public void setSeitenzahl(int seitenzahl) {
        this.seitenzahl = seitenzahl;
    }

    public void setTeilgebiet(String teilgebiet) {
        this.teilgebiet = teilgebiet;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public String getVerlag() {
        return verlag;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public int getSeitenzahl() {
        return seitenzahl;
    }

    public String getTeilgebiet() {
        return teilgebiet;
    }
}
