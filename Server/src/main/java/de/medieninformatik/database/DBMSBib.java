package de.medieninformatik.database;

import de.medieninformatik.common.library.Book;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 * In der Klasse "DBMSBib" wird die Connection zur Datenbank aufgebaut und Befehle wie select, delete, insert und upadte implementiert.
 */

public class DBMSBib {
    private static DBMSBib instance = null;
    private Connection connection;

    private DBMSBib() {
    }
    //Bib = Bibliothek

    //TODO funktionen umbenennen und eventuell funktionsweise ändern

    /**
     * In der Methode "getInstance" wird die Instanz aufgerufen. Falls etwas nicht funktioniert, wird eine Fehlermeldung ausgeworfen.
     * @return Gibt Instanz wieder
     */
    public synchronized static DBMSBib getInstance() {
        if (instance == null) {
            try {
                DBMSBib db = new DBMSBib();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "minf", "prog3");
                instance = db;
                instance.connection = conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * In der Methode "selectData" wird der Select-Befehl implementiert.
     * @param select Paramater für select-Befehl
     * @param where Parameter für where-Befehl
     * @param query Parameter für die query
     * @return gibt die Liste wieder
     */
    public static List<Book> selectData(String select, String where, String query) {
        Connection conn = DBMSBib.getInstance().getConnection();
        List<Book> list = new ArrayList<>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT " + select + " FROM informatik.book ";
                if ("isbn".equals(where)) {
                    sql += "WHERE isbn like '%" + query + "%'";
                }
                if ("autor".equals(where)) {
                    sql += "WHERE autor like '%" + query + "%'";
                }
                if ("titel".equals(where)) {
                    sql += "WHERE titel like '%" + query + "%'";
                }
                if ("teilgebiet".equals(where)) {
                    sql += "WHERE teilgebiet like '%" + query + "%'";
                }
                sql += ";";
                ResultSet result = statement.executeQuery(sql);
                ResultSetMetaData resultMetaData = result.getMetaData();
//h
                while (result.next()) {
                    Book book = new Book();
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                        String columnName = resultMetaData.getColumnName(i);
                        switch (columnName.toLowerCase()) {
                            case "titel" -> jsonObject.put("titel", result.getString(i));
                            case "isbn" -> jsonObject.put("isbn", result.getString(i));
                            case "autor" -> jsonObject.put("autor", result.getString(i));
                            case "teilgebiet" -> jsonObject.put("teilgebiet", result.getString(i));
                            case "verlag" -> jsonObject.put("verlag", result.getString(i));
                            case "erscheinungsjahr" -> jsonObject.put("erscheinungsjahr", result.getInt(i));
                            case "seitenzahl" -> jsonObject.put("seitenzahl", result.getInt(i));
                            default -> throw new IllegalStateException("Unexpected value: " + columnName.toLowerCase());
                        }
                    }
                    list.add(book.fromJSON(jsonObject));
                }
                return list;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return list;
    }

    /**
     * In der Methode "deleteData" wird der delete-Befehl implementiert.
     * @param query Parameter für die Query
     * @return Gibt Boolean wieder
     */
    public static Boolean deleteData(String query) {
        Connection conn = DBMSBib.getInstance().getConnection();
        Boolean bool = false;
        if (conn != null) {

            try (Statement statement = conn.createStatement()) {
                String sql = "DELETE FROM informatik.book WHERE ISBN = '";
                sql += query;
                sql += "';";
                bool = statement.execute(sql);
                Objects.requireNonNull(statement).close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }


    /**
     * In der Methode "insertData" wird der insert-Befehl implementiert.
     * @param book Parameter für das Buch in der Datenbank
     * @return gibt Boolean wieder
     */
    public static Boolean insertData(Book book) {
        Connection conn = DBMSBib.getInstance().getConnection();
        Boolean bool = false;
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                String sql = "INSERT INTO informatik.book (Titel, Autor, Teilgebiet, ISBN, Verlag, Erscheinungsjahr, Seitenzahl) VALUES (";
                sql += "'" + book.getTitel() + "', ";
                sql += "'" + book.getAutor() + "', ";
                sql += "'" + book.getTeilgebiet() + "', ";
                sql += "'" + book.getIsbn() + "', ";
                sql += "'" + book.getVerlag() + "', ";
                sql += "'" + book.getErscheinungsjahr() + "', ";
                sql += "'" + book.getSeitenzahl() + "');";
                bool = statement.execute(sql);
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }

    /**
     * In der Methode "updateData" wird der update-Befehl implementiert.
     * @param query Parameter für die Query
     * @param whereParam Parameter für where
     * @return gibt Boolean wieder.
     */
    public static Boolean updateData(String query, String whereParam) {
        Connection conn = DBMSBib.getInstance().getConnection();
        Boolean bool = false;
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                String sql = "UPDATE informatik.book SET ";
                sql += query;
                sql += " WHERE informatik.book.ISBN = '";
                sql += whereParam + "';";
                bool = statement.execute(sql);
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }

    /**
     * In der Methode "getConnection" wird die Verbindung mit dem User implementiert
     * @return Gibt die Connection wieder
     */
    public synchronized Connection getConnection() {
        if (instance != null) {
            try {
                //TODO (ganz am ende) admin, admin durch minf, prog3 ersetzen
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "minf", "prog3");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Objects.requireNonNull(connection);
        return connection;
    }
}




