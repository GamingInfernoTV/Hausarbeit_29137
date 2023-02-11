package de.medieninformatik.common;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.lang.reflect.Array;
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

    @JsonSetter
    public void setTitel(String titel) {
        this.titel = titel;
    }

    @JsonSetter
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @JsonSetter
    public void setVerlag(String verlag) {
        this.verlag = verlag;
    }

    @JsonSetter
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonSetter
    public void setErscheinungsjahr(int erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    @JsonSetter
    public void setSeitenzahl(int seitenzahl) {
        this.seitenzahl = seitenzahl;
    }

    @JsonSetter
    public void setTeilgebiet(String teilgebiet) {
        this.teilgebiet = teilgebiet;
    }

    @JsonGetter
    public String getTitel() {
        return titel;
    }

    @JsonGetter
    public String getAutor() {
        return autor;
    }

    @JsonGetter
    public String getVerlag() {
        return verlag;
    }

    @JsonGetter
    public String getIsbn() {
        return isbn;
    }

    @JsonGetter
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    @JsonGetter
    public int getSeitenzahl() {
        return seitenzahl;
    }

    @JsonGetter
    public String getTeilgebiet() {
        return teilgebiet;
    }


}
