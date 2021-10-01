package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Inqilab implements WebCrawler {
    @Override
    public Article getData(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        String title = document.select("title").text();
        String category = CategoryCheck.categoryCheck(url);

        Elements elements = document.getElementsByClass("image_block").select("img");


        return null;
    }

    @Override
    public List<String> getSublinks(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("a[href]");

        var list = new HashSet<>();

        for(var element : elements) {
            url = (element == null) ? "hello Bangladesh" : element.absUrl("href");
            if(url.contains("article")) {
                list.add(url);
            }
        }

        var result = new ArrayList();
        result.addAll(list);
        return result;
    }
}
