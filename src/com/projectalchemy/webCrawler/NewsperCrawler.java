package com.projectalchemy.webCrawler;

import com.projectalchemy.DatabaseStorage.Database;
import com.projectalchemy.util.UrlValidator;

import java.io.IOException;

public class NewsperCrawler {

    private String homePageUrl;
    private Database database;
    private WebCrawler crawler;

    public NewsperCrawler(String homePageUrl, Database database, WebCrawler crawler) {
        this.homePageUrl = homePageUrl;
        this.database = database;
        this.crawler = crawler;
    }

    //Crawling the whole website
    public void Crawl() throws IOException {
        var urls = crawler.getSublinks(homePageUrl);
        for (var url : urls) {
            System.out.println( "this is url " + url);
            if (UrlValidator.isValid(url)) {
                var article = crawler.getData(url);
                System.out.println("details " + article.getDetails());
                if (article != null && article.getDetails().length() > 0) {
                    System.out.println(article.getTitle());
                    database.StoreData(article);
                }
            }
        }
    }
}
