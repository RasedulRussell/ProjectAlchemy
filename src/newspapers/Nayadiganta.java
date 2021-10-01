package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Nayadiganta implements WebCrawler {
    @Override
    public Article getData(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        String title = document.select("title").text();

        String category = CategoryCheck.categoryCheck(url);

        Elements el = document.getElementsByClass("img-responsive");
        String medialUrl = "";
        if(el.size() >= 2) {
            el.get(1).attr("src");
        }

        Elements articleParagrape = document.select("p");

        String rawDetails = articleParagrape.text();
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


    private boolean articleUrlCheck(String url) {
        int cnt = 0;
        for(int i = 0; i < url.length(); i++) {
            if(url.charAt(i) == '/') cnt++;
        }
        return cnt == 5;
    }

    @Override
    public List<String> getSublinks(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("a[href]");

        var list = new HashSet<>();

        for(var element : elements) {
            String tempUrl = element.absUrl("href");
            if(articleUrlCheck(tempUrl) && !tempUrl.contains("video") && !tempUrl.contains("pdf")) {
                list.add(element.absUrl("href"));
            }
        }

        var result = new ArrayList<>();
        result.addAll(list);
        return (ArrayList)result;
    }
}
