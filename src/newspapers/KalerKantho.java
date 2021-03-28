package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.util.ParseToMediaUrl;
import com.projectalchemy.util.UrlValidator;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class KalerKantho implements WebCrawler {

    private static String urlPattern = "^https://www.kalerkantho.com[\\/][a-z]+[\\/][a-z\\-]+[\\/][0-9]+[\\/][0-9]+[\\/][0-9]+[\\/][0-9]+$";
    @Override
    public Article getData(String url) throws IOException {
        Document document = null;

        try{
            document = Jsoup.connect(url).get();
        }catch (Exception ex) {
            return null;
        }

        String title = "";
        String tempTitle = document.select("title").text();
        for(int i = 0;  i < tempTitle.length(); i++) {
            if(tempTitle.charAt(i) == '|')break;
            title = title + tempTitle.charAt(i);
        }
        String category = CategoryCheck.categoryCheck(url);

        String medialUrl = document.getElementsByClass("img").toString();
        medialUrl = ParseToMediaUrl.parseToMediaURL(medialUrl.substring(16, medialUrl.length()));


        Elements elements = document.select("p");
        elements.remove(0); //// removing extra paragraph
        elements.remove(0);
        elements.remove(0);
        elements.remove(0);

        String rawDetails = elements.text();
        String shortDetails = rawDetails.substring(0, Math.min(700, rawDetails.length()));

        Article article = new Article();

        Timestamp date = new Timestamp(System.currentTimeMillis());
        article.setTimePublished(date);
        article.setCategory(category);
        article.setTitle(title);
        article.setDetails(shortDetails);
        article.setRawDetails(rawDetails);
        article.setMediaUrl(medialUrl);
        article.setUrl(url);

        return article;
    }

    @Override
    public List<String> getSublinks(String homeUrl) throws IOException {
        Document document = Jsoup.connect(homeUrl).get();
        Elements elements = document.select("a[href]");
        ArrayList<String> urls = new ArrayList<String>();
        for(var element : elements) {
            String url = element.absUrl("href");
            if(UrlValidator.isValid(url, urlPattern)) {
                urls.add(url);
            }
        }
        return urls;
    }
}
