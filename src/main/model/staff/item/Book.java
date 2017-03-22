package staff.item;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import staff.connection.DBConnection;
import staff.user.Client;

import static util.ClassNameUtil.getCurrentClassName;

public class Book {
    private static final Logger log = Logger.getLogger(getCurrentClassName());
    private DBConnection dbConnection = new DBConnection();
    private List<String> book = new ArrayList<>();
    private List<List<String>> books = new ArrayList<>();
    private String name;
    private String discription;
    private String autorName;
    private String autorPatronymic;
    private String autorSername;

    public Book() {

    }

    public Book(String name, String discription, String autorName, String autorPatronymic, String autorSername) {
        this.name = name.toLowerCase();
        this.discription = discription.toLowerCase();
        this.autorName = autorName.replace(" ", "").toLowerCase();
        this.autorPatronymic = autorPatronymic.replace(" ", "").toLowerCase();
        this.autorSername = autorSername.replace(" ", "").toLowerCase();
    }


    public List<List<String>> findBookById(int id) {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM books WHERE id = '" + id + "'")) {
            log.debug("Search in DB books where ID = " + id);
            addBookToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection book");
        return books;
    }

    public List<List<String>> findBook(int numberQuery) {
        log.debug("Number searching = " + numberQuery);
        String findBookAll = "SELECT * FROM books";
        String findBookByName = "SELECT * FROM books WHERE NAME = '" + name + "'";
        String findBookByDiscription = "SELECT * FROM books WHERE DISCRIPTION LIKE '%" + discription + "%'";
        String findBookByAutorName = "SELECT * FROM books WHERE AUTOR_NAME = '" + autorName + "'";
        String findBookByAutorPatronymic = "SELECT * FROM books WHERE AUTOR_PATRONYMIC = '" + autorPatronymic + "'";
        String findBookByAutorSername = "SELECT * FROM books WHERE AUTOR_SURNAME = '" + autorSername + "'";
        String findBookByAll = "SELECT * FROM books WHERE " +
                "name LIKE '%" + name + "%' OR " +
                "discription LIKE '%" + name + "%'OR " +
                "autor_name LIKE '%" + name + "%' OR " +
                "autor_patronymic LIKE '%" + name + "%' OR " +
                "autor_surname LIKE '%" + name + "%' ";
        String defaultQuery = "SELECT * FROM books WHERE ID = 0";
        String query;
        switch (numberQuery) {
            case 1:
                query = findBookAll;
                log.debug("Find all book");
                break;
            case 2:
                query = findBookByName;
                log.debug("Find book by  name  = " + name);
                break;
            case 3:
                query = findBookByDiscription;
                log.debug("Find book by  discription  = " + discription);
                break;
            case 4:
                query = findBookByAutorName;
                log.debug("Find book by  autorName  = " + autorName);
                break;
            case 5:
                query = findBookByAutorPatronymic;
                log.debug("Find book by  autorPatronymic  = " + autorPatronymic);
                break;
            case 6:
                query = findBookByAutorSername;
                log.debug("Find book by  autorSername  = " + autorSername);
                break;
            case 7:
                query = findBookByAll;
                log.debug("Find book by  all parametrs = " + name);
                break;
            default:
                query = defaultQuery;
                log.debug("Find book by  default");
                break;
        }
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                (query)) {

            addBookToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection books");
        return books;
    }

    private void addBookToCollection(ResultSet resultSet) {
        Client client = new Client();
        books.clear();
        log.debug("Clear collection books");
        try {
            while (resultSet.next()) {
                book.add(String.valueOf(resultSet.getInt("ID")));
                log.debug("Add search to collection ID = " + book.get(0));
                book.add(client.getfirstSumbolToUpperCsse(resultSet.getString("NAME")));
                log.debug("Add search to collection NAME = " + book.get(1));
                book.add(client.getfirstSumbolToUpperCsse(resultSet.getString("DISCRIPTION")));
                log.debug("Add search to collection DISCRIPTION = " + book.get(2));
                book.add(client.getfirstSumbolToUpperCsse(resultSet.getString("AUTOR_NAME")));
                log.debug("Add search to collection AUTOR_NAME = " + book.get(3));
                book.add(client.getfirstSumbolToUpperCsse(resultSet.getString("AUTOR_PATRONYMIC")));
                log.debug("Add search to collection AUTOR_PATRONYMIC = " + book.get(4));
                book.add(client.getfirstSumbolToUpperCsse(resultSet.getString("AUTOR_SURNAME")));
                log.debug("Add search to collection AUTOR_SURNAME = " + book.get(5));
                books.add(new ArrayList<>(book));
                log.debug("Add to collection books collection book ");
                book.clear();
                log.debug("Collection book clear");
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public boolean addBook() {
        boolean result = false;
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM books WHERE NAME = '" + name + "' AND AUTOR_SURNAME = '" + autorSername + "'");
             PreparedStatement prepInsert = dbConnection.getConnection().prepareStatement
                     ("INSERT INTO books(NAME, DISCRIPTION, AUTOR_NAME,AUTOR_PATRONYMIC,AUTOR_SURNAME) " +
                             "VALUES (?,?,?,?,?)")) {
            log.debug("Search in DB books name = " + name + " autorSername = " + autorSername);
            if (!resultSet.next()) {
                log.debug("Search in DB books name = " + name + " autorName = " + autorName + " don't exist");
                prepInsert.setString(1, name);
                prepInsert.setString(2, discription);
                prepInsert.setString(3, autorName);
                prepInsert.setString(4, autorPatronymic);
                prepInsert.setString(5, autorSername);
                prepInsert.execute();
                log.debug("Insert in DB books  name = " + name + " discription = " + discription +
                        " autorName = " + autorName + " autorPatronymic = " + autorPatronymic +
                        " autorSername = " + autorSername);
                result = true;
            } else {
                log.warn("Exist book by name = " + name + " and autorSername " + autorSername);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return result = " + result);
        return result;
    }
}




