package staff.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static util.ClassNameUtil.getCurrentClassName;

public class DBConnection {

    private static final Logger log = Logger.getLogger(getCurrentClassName());
    private final static String URL = "jdbc:sqlite:C:\\Users\\LIGA\\projects\\LibraryWebApp\\src\\main\\resources\\library.db";
//    private final static String URL = "jdbc:sqlite::resource:library.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private Connection connection;
    public Connection getConnection() {
        if (connection != null) {
            log.warn("Connection don't close, get this connection");
            return connection;
        }
        try {Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            log.debug("Get connection DB");
        } catch (SQLException|ClassNotFoundException e) {
            log.error(e);
        }
        return connection;
    }

}
