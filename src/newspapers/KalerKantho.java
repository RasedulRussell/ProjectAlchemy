package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class KalerKantho implements WebCrawler {

    @Override
    public Article getData(String url) throws IOException {
        return null;
    }

    @Override
    public List<String> getSublinks(String homeUrl) throws IOException {
        Document document = Jsoup.connect(homeUrl).get();
        Elements elements = document.select("a[href]");
        System.out.println(elements.size());
        for(var element : elements) {
            String url = element.absUrl("href");
            System.out.println(url);
        }
        System.exit(0);
        return null;
    }
}
