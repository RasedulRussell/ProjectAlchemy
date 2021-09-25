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

public class Ittefaq implements WebCrawler {

    @Override
    public Article getData(String url) throws IOException {
        org.jsoup.nodes.Document document = null;

        try{
            document = Jsoup.connect(url).get();
        }catch (Exception ex) {
            return null;
        }

        String title = document.select("title").text();

        String category = CategoryCheck.categoryCheck(url);

        String medialUrl = document.getElementsByClass("img").attr("src");

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

    private boolean check(String url) {
        int cnt = 0;
        for(int i = 0; i < url.length(); i++) {
            if(url.charAt(i) == '/') cnt++;
        }
        return cnt == 5;
    }

    private String mediaUrlParser(String url) {
        return "";
    }

    @Override
    public List<String> getSublinks(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("a[href]");
        var newsArticleUrl = new HashSet<>();
        for(var element : elements) {
            String absUrl = element.absUrl("href");
            if(check(absUrl)) {
                newsArticleUrl.add(absUrl);
            }
        }
        var result = new ArrayList<>();
        result.addAll(newsArticleUrl);
        return (ArrayList)result;
    }
}
