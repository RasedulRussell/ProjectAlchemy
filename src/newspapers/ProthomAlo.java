package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ProthomAlo implements WebCrawler {

    @Override
    public Article getData(String url) throws IOException {
        return null;
    }

    @Override
    public List<String> getSublinks(String newsPaperHomeUrl) throws IOException {
        newsPaperHomeUrl = "https://en.prothomalo.com/";
        Document document = Jsoup.connect(newsPaperHomeUrl).get();
        Elements elements = document.select("a[href]");
        for(var str : elements) {
            System.out.println(str.absUrl("href"));
        }
        System.exit(0);
        return null;
    }
}
