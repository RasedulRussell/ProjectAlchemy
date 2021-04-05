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

public class DailyStar implements WebCrawler {

    private final ArrayList<String> mediaUrlClass = new ArrayList<>() {
        {
            add("image-style-big-1");
            add("image-style-big-2");
            add("image-style-big-3");
            add("image-style-big-4");
            add("image-style-big-5");
            add("image-style-big-6");
            add("image-style-big-7");
            add("image-style-big-8");
            add("image-style-big-9");
            add("image-style-very-big-1");
            add("image-style-none");
            add("ytp-cued-thumbnail-overlay-image");
            add("html5-video-container");
        }
    };

    private String titelFilter(String title) {
        int i = 0;
        while(i < title.length()-1 && title.charAt(i+1) != '|') {
            i++;
        }
        return title.substring(0, i);
    }

    @Override
    public Article getData(String url) throws IOException {
        Document document = null;
        try{
            document = Jsoup.connect(url).get();
        }catch (Exception ignored){
            return null;
        }

        ///title and category finder
        String title = null;
        try{
            title = document.select("title").text();
        }catch (Exception ignored){}
        title = titelFilter(title);
        String category = CategoryCheck.categoryCheck(url);

        ///media url
        Elements mediaElements = null;
        for(String isImage : mediaUrlClass) {
            mediaElements = document.getElementsByClass(isImage);
            if(mediaElements.size() > 0) break;
        }
        String mediaUrl = null;
        if(mediaElements.size() > 0) {
            mediaUrl = ParseToMediaUrl.parseToMediaURL(mediaElements.get(0).toString());
        }

        /// details find
        Elements elements = document.select("p");
        String rawDetails = elements.text();
        String details = rawDetails.substring(0, Math.min(300, rawDetails.length()));

        Timestamp date = new Timestamp(System.currentTimeMillis());

        ///Article generate
        Article article = new Article();
        article.setTitle(title);
        article.setCategory(category);
        article.setDetails(details);
        article.setRawDetails(rawDetails);
        article.setMediaUrl(mediaUrl);
        article.setTimePublished(date);
        article.setUrl(url);
        return article;
    }

    @Override
    public List<String> getSublinks(String url) throws IOException {
        Document document = null;
        try{
            document = Jsoup.connect(url).get();
        }catch (Exception ignored) {
        }
        assert document != null;
        Elements elements = document.select("a[href]");
        ArrayList<String> urls = new ArrayList<>();
        for(var element : elements) {
            url = element.absUrl("href");
            if(url.contains("/news/")) {
                urls.add(element.absUrl("href"));
            }
        }
        return urls;
    }
}
