package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.util.ParseToMediaUrl;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
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

        /// media link find
        String mediaUrl = null;
        Elements mediaElements = document.getElementsByClass("image-container image rel-soci");
        for(var ok : mediaElements) {
            mediaUrl = ParseToMediaUrl.parseToMediaURL(ok.select("img").toString());
        }

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
        if(elements.get(0).text().equals("্রিন্ট সংস্করণ")) {
            elements.remove(0);
        }
        if(elements.get(0).text().equals("ছবি: সংগৃহীত")) {
            elements.remove(0);
        }
        String details = elements.text();
        String shortDetails = details.substring(0, Math.min(900, details.length()));

        Article article = new Article();

        Timestamp date = new Timestamp(System.currentTimeMillis());
        article.setTimePublished(date);

        article.setTitle(title);
        article.setRawDetails(details);
        article.setDetails(shortDetails);
        article.setUrl(url);
        article.setCategory(category);
        article.setMediaUrl(mediaUrl);

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
