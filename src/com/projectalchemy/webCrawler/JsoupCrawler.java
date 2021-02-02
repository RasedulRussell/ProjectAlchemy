package com.projectalchemy.webCrawler;

import com.projectalchemy.models.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsoupCrawler implements WebCrawler {


    @Override
    public Article getData(String url) throws IOException {

        Document document = Jsoup.connect(url).get();
        var title = document.title();
        ///System.out.println(document.title());
        Elements articleTag = document.select("article");
        String details = articleTag.text();
        var article = new Article();
        article.setTitle(title);
        article.setRawDetails(details);
        var shortDetails = details.substring(0, Math.min(999, details.length()));
        article.setDetails(shortDetails);
        article.setUrl(url);
        System.out.println("return");
        return article;
    }

    @Override
    public List<String> getSublinks(String newspaperHomeUrl) throws IOException {

        Document doc = Jsoup.connect(newspaperHomeUrl).get();
        Elements links = doc.select("a[href]");
        var urls = new ArrayList<String>();
        for (Element link : links) {
            String url = link.absUrl("href");
            urls.add(url);
        }
        return urls;
    }
}
