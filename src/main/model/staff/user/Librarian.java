package staff.user;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import staff.connection.DBConnection;

import static util.ClassNameUtil.getCurrentClassName;

public class Librarian extends FinderUser {
    private static final Logger log = Logger.getLogger(getCurrentClassName());
    private DBConnection dbConnection = new DBConnection();
    private List<String> book = new ArrayList<>();
    private List<List<String>> books = new ArrayList<>();
    private List<String> user = new ArrayList<>();
    private List<List<String>> users = new ArrayList<>();
    private List<String> rent = new ArrayList<>();
    private List<List<String>> rents = new ArrayList<>();

    public Librarian() {
        super();
    }

    public Librarian(String name, String patronymic, String surname, String phone) {
        super(name, patronymic, surname, phone);
    }

    public boolean addClient() {
        boolean result = false;
        try (PreparedStatement prepInsert = dbConnection.getConnection().prepareStatement
                ("INSERT INTO users(NAME, PATRONYMIC, SURNAME, PHONE) VALUES (?,?,?,?)");
             ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                     ("SELECT * FROM users WHERE name = '" + getName() + "' " +
                             "AND surname ='" + getSurname() + "'")) {
            log.debug("Search in DB books name = " + getName() + " Sername = " + getSurname());
            if (!resultSet.next()) {
                prepInsert.setString(1, getName());
                prepInsert.setString(2, getPatronymic());
                prepInsert.setString(3, getSurname());
                prepInsert.setString(4, getPhone());
                prepInsert.execute();
                log.debug("Insert in DB users  name = " + getName() + " autorPatronymic = " + getPatronymic() +
                        " sername = " + getSurname() + " phone = " + getPhone());
                result = true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return result = " + result);
        return result;
    }

    public boolean editClient(int id) {
        boolean result = false;
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM users WHERE ID = " + id + "");
             PreparedStatement prepUpdate = dbConnection.getConnection().prepareStatement
                     ("UPDATE users SET NAME =  ? , PATRONYMIC = ?, SURNAME = ?, PHONE = ? " +
                             "WHERE  id = ?")) {
            log.debug("Search in DB users where id = " + id);
            if (resultSet.next()) {
                log.debug("Search in DB users where id = " + id + " exist");
                prepUpdate.setString(1, getName());
                prepUpdate.setString(2, getPatronymic());
                prepUpdate.setString(3, getSurname());
                prepUpdate.setString(4, getPhone());
                prepUpdate.setInt(5, id);
                prepUpdate.executeUpdate();
                log.debug("Update in DB books  name = " + getName() + " patronymic = " + getPatronymic() +
                        " sername = " + getSurname() + " phone = " + getPhone());
                result = true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return result = " + result);
        return result;
    }

    public boolean issueBook(int clientId, int bookId, LocalDate date) {
        boolean result = false;
        LocalDate today = LocalDate.now();
        if (date == null) {
            date = today.plusDays(14);
            log.debug("Date issue book = " + date);
        } else {
            log.debug("Date issue book = " + date);
        }
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM rent WHERE ID_BOOK = " + bookId + " AND RENT = 1");
             PreparedStatement statement = dbConnection.getConnection().prepareStatement
                     ("INSERT INTO rent (ID_USER, ID_BOOK, ISSUE_BOOK, RETURN_BOOK, RENT) VALUES (?,?, ?,?, ? )")) {
            log.debug("Search in DB rent where bookId = " + bookId+ "rent  = true");
            while (!resultSet.next()) {
                log.debug("Search don't exist in DB rent where bookId = " + bookId+ "rent  = true");
                statement.setInt(1, clientId);
                statement.setInt(2, bookId);
                statement.setString(3, String.valueOf(today));
                statement.setString(4, String.valueOf(date));
                statement.setBoolean(5, true);
                statement.execute();
                log.debug("Insert in DB rent  clientId = " + clientId + " bookId = " + bookId +
                        " issue book date = " + String.valueOf(today) +
                        " return book date = " + String.valueOf(date) +
                        " rent = true");
                result = true;
                break;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return result = " + result);
        return result;
    }

    public boolean returnBook(int rentId) {
        boolean result = false;
        LocalDate today = LocalDate.now();
        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement
                ("UPDATE  rent SET RETURN_BOOK = ? , RENT = ?  WHERE  id = ?")) {
            if (findByRent(Integer.valueOf(rentId)).size() > 0) {
                statement.setString(1, String.valueOf(today));
                statement.setBoolean(2, false);
                statement.setInt(3, rentId);
                statement.executeUpdate();
                log.debug("Update in DB rent  return book date = " + String.valueOf(today) +
                        " rent = true id = " + rentId);
                result = true;
            }
        } catch (SQLException e) {
            log.error(e);
            e.printStackTrace();
        }
        log.debug("Return result = " + result);
        return result;
    }

    public List<List<String>> findUserByRent(int id) {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT us.ID, u.NAME,  u.SURNAME, b.NAME, b.AUTOR_SURNAME " +
                        "FROM  users u   " +
                        " JOIN  rent us ON us.ID_USER = " + id + " AND u.ID = " + id + " " +
                        "AND us.RENT = 1 JOIN books b ON us.ID_BOOK = b.ID  ")) {
            log.debug("Search in DB rent where rent = true ");
            addUserToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection");
        return users;
    }

    public List<List<String>> findByRent(int id) {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM rent WHERE ID = " + id + "")) {
            log.debug("Search in DB rent where Id = " + id);
            addUserToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection");
        return users;

    }

    public List<List<String>> findBookByRent(int id) {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM  rent WHERE ID_BOOK = " + id + " ")) {
            log.debug("Search in DB rent where IdBook = " + id);
            addBookToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection");
        return books;
    }

    public List<List<String>> findAllBookByRent() {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT  u.AUTOR_NAME, u.AUTOR_PATRONYMIC, u.AUTOR_SURNAME, u.NAME " +
                        "FROM books u  " +
                        "INNER JOIN  rent us " +
                        "ON us.ID_BOOK = u.ID AND us.RENT = 1 ")) {
            log.debug("Search in DB rent all book where rent = true ");
            addBookToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection");
        return books;
    }

    public List<List<String>> findAllUserByRent() {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT distinct   u.NAME, u.PATRONYMIC, u.SURNAME, u.PHONE " +
                        "FROM users u  " +
                        "INNER JOIN  rent us ON us.ID_USER = u.ID  AND us.RENT = 1 ")) {
            log.debug("Search in DB rent all users where rent = true ");
            addBookToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection");
        return books;
    }

    public List<List<String>> findAllRent() {
        String[] r = new String[2];
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT b.AUTOR_NAME, b.AUTOR_PATRONYMIC, b.AUTOR_SURNAME,  b.NAME, u.NAME,  " +
                        "u.PATRONYMIC, u.SURNAME, u.PHONE " +
                        "FROM  users u   " +
                        "JOIN  rent us ON us.ID_USER = u.ID AND  us.RENT = 1 " +
                        "JOIN books b ON us.ID_BOOK = b.ID  ")) {
            log.debug("Search in DB rent all");
            addRentToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection");
        return rents;
    }

    public void addBookToCollection(ResultSet r) {
        books.clear();
        log.debug("Clear collection ");
        try {
            while (r.next()) {
                book.add(getfirstSumbolToUpperCsse(r.getString(1)));
                book.add(getfirstSumbolToUpperCsse(r.getString(2)));
                book.add(getfirstSumbolToUpperCsse(r.getString(3)));
                book.add(getfirstSumbolToUpperCsse(r.getString(4)));
                log.debug("Add search to collection  findings = [" + book.get(0)+","+ book.get(1)+","
                        + book.get(2)+","+ book.get(3)+"]");
                books.add(new ArrayList<>(book));
                log.debug("Add to collection this collection");
                book.clear();
                log.debug("Collection clear");
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void addUserToCollection(ResultSet r) {
        users.clear();
        log.debug("Clear collection ");
        try {
            while (r.next()) {
                user.add(getfirstSumbolToUpperCsse(r.getString(1)));
                user.add(getfirstSumbolToUpperCsse(r.getString(2)));
                user.add(getfirstSumbolToUpperCsse(r.getString(3)));
                user.add(getfirstSumbolToUpperCsse(r.getString(4)));
                user.add(getfirstSumbolToUpperCsse(r.getString(5)));
                log.debug("Add search to collection  findings = [" + user.get(0)+","+ user.get(1)+","
                        + user.get(2)+","+ user.get(3)+","+ user.get(4)+"]");
                users.add(new ArrayList<>(user));
                log.debug("Add to collection this collection");
                user.clear();
                log.debug("Collection clear");
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void addRentToCollection(ResultSet r) {
        users.clear();
        try {
            while (r.next()) {
                rent.add(getfirstSumbolToUpperCsse(r.getString(1)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(2)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(3)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(4)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(5)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(6)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(7)));
                rent.add(getfirstSumbolToUpperCsse(r.getString(8)));
                log.debug("Add search to collection  findings = [" + rent.get(0)+","+ rent.get(1)+","
                        + rent.get(2)+","+ rent.get(3)+","+ rent.get(4)+","+ rent.get(5)+","
                        + rent.get(6)+","+ rent.get(7)+"]");
                rents.add(new ArrayList<>(rent));
                log.debug("Add to collection this collection");
                rent.clear();
                log.debug("Collection clear");
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}



