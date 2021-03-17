package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Samakal implements WebCrawler {
    @Override
    public Article getData(String url) throws IOException {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception ex) {
            return null;
        }
        String title = document.select("title").text();
        String category = CategoryCheck.categoryCheck(url);

        Elements elements = document.select("p");

        /// remove category tag

        for(int i = 0; i <= 19; i++) {
            String breakPoint = elements.get(0).text();
            if(breakPoint == "প্রকাশ:") {
                elements.remove(0);
                break;
            }
            elements.remove(0);
        }
        if(elements.get(0).text() == "্রিন্ট সংস্করণ") {
            elements.remove(0);
        }
        if(elements.get(0).text() == "ছবি: সংগৃহীত") {
            elements.remove(0);
        }
        String details = elements.text();
        String shortDetails = details.substring(0, Math.min(900, details.length()));

        Article article = new Article();

        article.setTitle(title);
        article.setRawDetails(details);
        article.setDetails(shortDetails);
        article.setUrl(url);
        article.setCategory(category);

        return article;
    }

    @Override
    public List<String> getSublinks(String newspaperHomeUrl) throws IOException {
        Document document = Jsoup.connect(newspaperHomeUrl).get();
        Elements links = document.select("a[href]");
        var urls = new ArrayList<String>();
        for(var link : links) {
            String url = link.absUrl("href");
            if(!url.contains("article")) { /// is it a news article or not
                continue;
            }
            urls.add(url);
        }
        return urls;
    }
}