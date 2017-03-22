package staff.user;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import staff.connection.DBConnection;

import static util.ClassNameUtil.getCurrentClassName;

public class Administrator extends FinderUser {
    private DBConnection dbConnection = new DBConnection();
    private List<String> type = new ArrayList<>();
    private List<List<String>> types = new ArrayList<>();
    private static final Logger log = Logger.getLogger(getCurrentClassName());

    public List<List<String>> findAllAutorization() {
        types.clear();
        log.debug("Clear collection types");
        try (ResultSet resultSet = dbConnection.getConnection().createStatement().executeQuery
                ("SELECT  t.TYPER, a.LOGIN, a.PASSWORD " +
                "FROM authorization a  " +
                "INNER JOIN  type_user t " +
                "ON a.ID_TYPE = t.ID_TYPE  ")){
            log.debug("Search all by DB authorization");
            while (resultSet.next()) {
                type.add(resultSet.getString(1));
                log.debug("add search to collection type = " + type.get(0));
                type.add(resultSet.getString(2));
                log.debug("add search to collection login = " + type.get(1)  );
                type.add(resultSet.getString(3));
                log.debug("add search to collection password = " + type.get(2)  );
                types.add(new ArrayList<>(type));
                log.debug("Add collection type in collection types");
                type.clear();
                log.debug("Clear collection type");
            }
        } catch (SQLException e) {
            log.error(e);
        }
        log.debug("Return collection types");
        return types;

    }
}

