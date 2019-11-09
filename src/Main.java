import com.projectalchemy.DatabaseStorage.OracleDatabase;
import com.projectalchemy.webCrawler.NewsperCrawler;
import com.projectalchemy.webCrawler.TestCrawler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Main {
    public static void main(String args[]) throws IOException, SQLException {
        /// connect to DB
        OracleConnectionFactory dbConnection = new OracleConnectionFactory();
        var connection = dbConnection.getConnectionOracle();

        /*Table myTable = new Table();
        myTable.CreatTable();*/

        String samakal = "https://samakal.com";
        String prothomAlo = "https://www.prothomalo.com";
        String dailyinqilab = "https://www.dailyinqilab.com";
        String dailyStar = "https://www.thedailystar.net";
        String alJazeera = "https://www.aljazeera.com";

        NewsperCrawler newsperCrawler =
                new NewsperCrawler(prothomAlo,new OracleDatabase(connection),new TestCrawler());
        newsperCrawler.Crawl();

        var statement = connection.prepareStatement("select  * from tabletest");
        ResultSet result = statement.executeQuery();

        while (result.next()){
            var blob = result.getBlob(2);
            var details = new String(blob.getBytes(1, (int) blob.length()));
            System.out.println(details);
            break;
        }
        connection.close();
    }
}
