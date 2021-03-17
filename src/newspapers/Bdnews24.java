package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.util.CategoryCheck;
import com.projectalchemy.webCrawler.WebCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bdnews24 implements WebCrawler {

    private String titleFilter(String title) {
        int i = 0;
        while(i < title.length() && title.charAt(i) != '|') {
            i++;
        }
        return title.substring(0, i);
    }

    @Override
    public Article getData(String url) throws IOException {
        Document document = null;
        try {
            document =  Jsoup.connect(url).get();
        }catch (Exception ex) {
            return null;
        }

        String title = document.select("title").text();
        title = titleFilter(title);
        String cateory = CategoryCheck.categoryCheck(url);

        Elements elements = document.select("p");

        for(int i = 0; i < 4; i++) {
            elements.remove(0);
        }

        String details = elements.text();
        String shortDetails = details.substring(0, Math.min(100, details.length()));

        Article article = new Article();

        article.setTitle(title);
        article.setDetails(shortDetails);
        article.setRawDetails(details);
        article.setCategory(cateory);
        article.setUrl(url);

        return article;
    }

    @Override
    public List<String> getSublinks(String newspaperHomeUrl) throws IOException {
        Document document = Jsoup.connect(newspaperHomeUrl).get();
        Elements links = document.select("a[href]");
        ArrayList<String> urls = new ArrayList<String>();

        for(var link : links) {
            String url = link.absUrl("href");
            if(url.contains("article")) {
                urls.add(url);
            }
        }
        return urls;
    }
}
