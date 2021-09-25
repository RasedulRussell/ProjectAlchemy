package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Jugantor implements WebCrawler {
    @Override
    public Article getData(String url) throws IOException {


        StringBuilder stringBuilder = new StringBuilder(url);

        while(stringBuilder.charAt(stringBuilder.length()-1) != '/') {
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        url = stringBuilder.toString();

        Connection.Response res = Jsoup.connect(url).execute();
        Map<String, String> coockis = res.cookies();
        Document document = Jsoup.connect(url).cookies(coockis).get();

        String title = document.select("title").text();

        if(title.charAt(0) == 'J' ) return null;

        String category = CategoryCheck.categoryCheck(url);

        Elements media = document.getElementsByTag("img");
        String mediaUrl = "";

        for(Element en : media) {
            String temp = en.attr("src");
            if(temp.contains(".jpg")) {
                mediaUrl = temp;
                break;
            }
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
        article.setMediaUrl(mediaUrl);
        article.setUrl(url);

        return article;
    }

    protected boolean articleUrlCheck(String url) {
        int cnt = 0;
        for(int i = 0; i < url.length(); i++) {
            if(url.charAt(i) == '/') cnt++;
        }
        return cnt == 5;
    }

    @Override
    public List<String> getSublinks(String url) throws IOException {
        Connection.Response res = Jsoup.connect(url).execute();
        Map<String, String> cookies = res.cookies();
        Document document = Jsoup.connect(url).cookies(cookies).get();
        Elements elements = document.select("a[href]");
        var urls = new HashSet<>();
        for(int i = 0; i < elements.size(); i++) {
            String temp = elements.get(i).absUrl("href");
            if(articleUrlCheck(temp) && !temp.contains("video") && !temp.contains("pdf") && !temp.contains("viewers-opinion")) {
                urls.add(temp);
            }
        }
        var result = new ArrayList(urls);
        System.out.println(result.size());
        return result;
    }
}
