package staff.user;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import staff.connection.DBConnection;

import static util.ClassNameUtil.getCurrentClassName;


public class FinderUser extends Client {
    private DBConnection dbConnection = new DBConnection();
    private List<String> user = new ArrayList<>();
    private List<List<String>> users = new ArrayList<>();
    private static final Logger log = Logger.getLogger(getCurrentClassName());

    public FinderUser(String name, String patronymic, String surname, String phone) {
        super(name, patronymic, surname, phone);
    }

    public FinderUser() {
        super();
    }

    public List<List<String>> findUser(int numberQuery) {
        String query;
        String findUserAll = "SELECT * FROM users ";
        String findUserByName = "SELECT * FROM users WHERE NAME = '" + getName() + "' ";
        String findUserBySurname = "SELECT * FROM users WHERE SURNAME = '" + getSurname() + "' ";
        String findUserByPatronymic = "SELECT * FROM users WHERE PATRONYMIC = '" + getPatronymic() + "' ";
        String defaultQuery = "SELECT * FROM users WHERE ID = 0 ";
        switch (numberQuery) {
            case 1:
                query = findUserAll;
                log.debug("Find all users");
                break;
            case 2:
                query = findUserByName;
                log.debug("Find user by  name  = " + getName());
                break;
            case 3:
                query = findUserBySurname;
                log.debug("Find user by  surname  = " + getSurname());
                break;
            case 4:
                query = findUserByPatronymic;
                log.debug("Find user by  patronymic  = " + getPatronymic());
                break;
            default:
                query = defaultQuery;
                log.debug("Find user by  default");
                break;
        }
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                (query)) {
            addValueToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection users");
        return users;
    }

    public List<List<String>> findUserById(int id) {
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM users WHERE id = '" + id + "' ")) {
            log.debug("Search in DB users where ID = " + id);
            addValueToCollection(resultSet);
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection users");
        return users;
    }

    private void addValueToCollection(ResultSet resultSet) {
        users.clear();
        log.debug("Clear collection users");
        try {
            while (resultSet.next()) {
                user.add(getfirstSumbolToUpperCsse(String.valueOf(resultSet.getInt("id"))));
                log.debug("Add search to collection ID = "+ user.get(0));
                user.add(getfirstSumbolToUpperCsse(resultSet.getString("name")));
                log.debug("Add search to collection name = "+ user.get(1));
                user.add(getfirstSumbolToUpperCsse(resultSet.getString("patronymic")));
                log.debug("Add search to collection patronymic = "+ user.get(2));
                user.add(getfirstSumbolToUpperCsse(resultSet.getString("surname")));
                log.debug("Add search to collection surname = "+ user.get(3));
                user.add(getfirstSumbolToUpperCsse(resultSet.getString("phone")));
                users.add(new ArrayList<>(user));
                log.debug("Add to collection users collection book ");
                user.clear();
                log.debug("Collection user clear");
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
