package de.medieninformatik.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.medieninformatik.common.Book;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */

public class LibraryDB {
    private static LibraryDB instance = null;
    private Connection connection;
    static ObjectMapper mapper = new ObjectMapper();

    //TODO Mapper löschen oder anwenden
    private LibraryDB() {
    }

    //TODO funktionen umbenennen und eventuell funktionsweise ändern
    public synchronized static LibraryDB getInstance() {
        if (instance == null) {
            try {
                LibraryDB db = new LibraryDB();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "admin", "admin");
                instance = db;
                instance.connection = conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    public static List<Book> selectData(String select, String whereParam, String query) {
        Connection conn = LibraryDB.getInstance().getConnection();
        List<Book> list = new ArrayList<>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT " + select + " FROM informatik.book ";
                if ("isbn".equals(whereParam)) {
                    sql += "WHERE isbn like '%" + query + "%'";
                }
                if ("autor".equals(whereParam)) {
                    sql += "WHERE autor like '%" + query + "%'";
                }
                sql += ";";
                ResultSet result = statement.executeQuery(sql);
                ResultSetMetaData resultMetaData = result.getMetaData();

                while (result.next()) {
                    Book bink = new Book();
                    for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                        String columnName = resultMetaData.getColumnName(i);
                        switch (columnName.toLowerCase()) {
                            case "titel" -> bink.setTitel(result.getString(i));
                            case "isbn" -> bink.setIsbn(result.getString(i));
                            case "autor" -> bink.setAutor(result.getString(i));
                            case "teilgebiet" -> bink.setTeilgebiet(result.getString(i));
                            case "verlag" -> bink.setVerlag(result.getString(i));
                            case "erscheinungsjahr" -> bink.setErscheinungsjahr(result.getInt(i));
                            case "seitenzahl" -> bink.setSeitenzahl(result.getInt(i));
                            default -> throw new IllegalStateException("Unexpected value: " + columnName.toLowerCase());
                        }
                    }
                    JSONObject jsonObject = new JSONObject(bink);
                    System.out.println(jsonObject);
                    list.add(bink);
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

    public static Boolean deleteData(String query) {
        Connection conn = LibraryDB.getInstance().getConnection();
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

    public synchronized Connection getConnection() {
        if (instance != null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "admin", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Objects.requireNonNull(connection);
        return connection;
    }

    public static Boolean insertData(Book book) {
        Connection conn = LibraryDB.getInstance().getConnection();
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

    public static Boolean updateData(String query, String whereParam) {
        Connection conn = LibraryDB.getInstance().getConnection();
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
}




