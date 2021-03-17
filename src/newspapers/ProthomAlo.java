package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProthomAlo implements WebCrawler {

    @Override
    public Article getData(String url) throws IOException {
        return null;
    }

    @Override
    public List<String> getSublinks(String newsPaperHomeUrl) throws IOException {
        Document document = Jsoup.connect(newsPaperHomeUrl).get();
        System.out.println(document.title());

        System.out.println(document);

        System.exit(0);
        return null;
    }
}
