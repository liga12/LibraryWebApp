package staff.autorization;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import staff.connection.DBConnection;

import static util.ClassNameUtil.getCurrentClassName;

public class Security {
    private static final Logger log = Logger.getLogger(getCurrentClassName());
    private DBConnection dbConnection = new DBConnection();
    private String login;
    private String password;
    private Integer type;

    public Security(String login, String password, Integer type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public String getAuthorization() {
        String result = null;
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT  t.TYPER " +
                        "FROM type_user t " +
                        "INNER JOIN  authorization a " +
                        "ON   t.ID_TYPE = a.ID_TYPE " +
                        "AND a.LOGIN = '" + login + "' " +
                        "AND a.PASSWORD = '" + password + "'")) {
            log.debug("get authorization login = " + login + "  password = " + password);
            if (resultSet.next()) {
                log.debug("authorization success");
                result = resultSet.getString("TYPER");
                log.debug("get type user  = " + result);
            } else {
                log.warn("authorization failed");
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return result;
    }


    public boolean addAuthorization() {
        boolean result = false;
        String typeUser = null;
        try (PreparedStatement prepInsert = dbConnection.getConnection().prepareStatement
                ("INSERT INTO authorization (LOGIN, PASSWORD, ID_TYPE) VALUES (?,?,?)")) {
            if (loginExist()) {
                prepInsert.setString(1, login);
                prepInsert.setString(2, password);
                prepInsert.setInt(3, type);
                prepInsert.execute();
                if (type == 1) {
                    typeUser = "Admin";
                }
                if (type == 2) {
                    typeUser = "Librarian";
                }
                if (type == 3) {
                    typeUser = "Client";
                }
                log.debug("add to authorization login = " + login + " password  = " + password + "typeUser  = " + typeUser);
                result = true;
            } else {
                log.warn("Attempt to add an existing login");
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Retern result = " + result);
        return result;

    }


    public boolean loginExist() {
        boolean result = false;
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT * FROM authorization WHERE login = '" + login + "'")) {
            log.debug("Check for existence of a login = " + login);
            if (!resultSet.next()) {
                result = true;
                log.debug("Does not exist login = " + login);
            }
            else {
                log.warn("Exist login = " + login);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Retern result = " + result);
        return result;
    }
}


