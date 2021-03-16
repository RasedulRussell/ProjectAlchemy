import com.projectalchemy.DatabaseStorage.OracleDatabase;
import com.projectalchemy.DatabaseStorage.TestDatabase;
import com.projectalchemy.webCrawler.JsoupCrawler;
import com.projectalchemy.webCrawler.NewsperCrawler;
import com.projectalchemy.webCrawler.TestCrawler;
import newspapers.ProthomAlo;
import newspapers.Samakal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {
    public static void main(String args[]) throws IOException, SQLException {
        /// connect to DB
        OracleConnectionFactory dbConnection = new OracleConnectionFactory();
        var connection = dbConnection.getConnectionOracle();

        String samakal = "https://samakal.com";
        String prothomAlo = "https://www.prothomalo.com";
        String dailyinqilab = "https://www.dailyinqilab.com";
        String dailyStar = "https://www.thedailystar.net";
        String alJazeera = "https://www.aljazeera.com";

        NewsperCrawler newsperCrawler =
                new NewsperCrawler(samakal, new OracleDatabase(connection), new Samakal());
        newsperCrawler.Crawl();

        var statement = connection.prepareStatement("select  * from Article");
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            var blob = result.getBlob(3);
            var details = new String(blob.getBytes(3, (int) blob.length()));
            System.out.println(details);
            System.out.println();
        }
        connection.close();
    }
}
