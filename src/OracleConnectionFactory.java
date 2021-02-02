
import java.sql.*;


public class OracleConnectionFactory {


    public static Connection getConnectionOracle() {
        Connection connection = null;
        try {
            if (connection == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String connectionUrl = "jdbc:oracle:thin:@//localhost:1521/XE";
                String userName = "NEWSPAPER";
                String pass = "12345678";
                connection = DriverManager.getConnection(connectionUrl, userName, pass);
            }
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
