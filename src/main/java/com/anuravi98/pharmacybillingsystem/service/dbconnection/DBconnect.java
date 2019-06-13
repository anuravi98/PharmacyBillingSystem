package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    public static Connection conn;
    static private String url;
    private String user;
    private String password;

    /**
     *
     * @param dbName dbname
     * @param user user name
     * @param password password
     */
    public DBconnect(String dbName, String user, String password) {
        url = "jdbc:derby:db/" + dbName + ";create=true";
        this.user = user;
        this.password = password;
    }

    /**
     *
     * @param dbName dbname
     */
    public DBconnect(String dbName) {
        url = "jdbc:derby:db/" + dbName + ";create=true";
    }

    /**
     *
     * @param dbName dbname
     * @return connection
     */
    public static Connection getConnection(String dbName) {
        url = "jdbc:derby:db/" + dbName + ";create=true";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
