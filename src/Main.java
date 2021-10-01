import com.projectalchemy.DatabaseStorage.OracleDatabase;
import com.projectalchemy.webCrawler.NewsperCrawler;
import newspapers.*;

import java.io.IOException;
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
        String bdnews24 = "https://bangla.bdnews24.com/news";
        String kalerkantho = "https://www.kalerkantho.com";
        String ittefaq = "https://www.ittefaq.com.bd/";
        String nayadiganta = "https://www.dailynayadiganta.com/";
        String jugantor = "https://www.jugantor.com/";
        String inqilab = "https://www.dailyinqilab.com/";

        NewsperCrawler newsperCrawler =
                new NewsperCrawler(bdnews24, new OracleDatabase(connection), new Bdnews24());
        newsperCrawler.Crawl();
        System.out.println("new");
        newsperCrawler =
                new NewsperCrawler(jugantor, new OracleDatabase(connection), new Jugantor());
        newsperCrawler.Crawl();
        System.out.println("new");
        newsperCrawler =
                new NewsperCrawler(nayadiganta, new OracleDatabase(connection), new Nayadiganta());
        newsperCrawler.Crawl();
        System.out.println("new");
        newsperCrawler =
                new NewsperCrawler(ittefaq, new OracleDatabase(connection), new Ittefaq());
        newsperCrawler.Crawl();
        System.out.println("new");
        newsperCrawler =
                new NewsperCrawler(kalerkantho, new OracleDatabase(connection), new KalerKantho());
        newsperCrawler.Crawl();
        System.out.println("new");
    }
}
