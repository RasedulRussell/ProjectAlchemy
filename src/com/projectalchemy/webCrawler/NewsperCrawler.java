package com.projectalchemy.webCrawler;

import com.projectalchemy.DatabaseStorage.Database;
import java.io.IOException;

public class NewsperCrawler {

    private final String homePageUrl;
    private final Database database;
    private final WebCrawler crawler;

    public NewsperCrawler(String homePageUrl, Database database, WebCrawler crawler) {
        this.homePageUrl = homePageUrl;
        this.database = database;
        this.crawler = crawler;
    }

    //Crawling the whole website
    public void Crawl() throws IOException {
        var urls = crawler.getSublinks(homePageUrl);
        for (var url : urls) {
            var article = crawler.getData(url);
            if (article != null && article.getDetails().length() > 0) {
                database.StoreData(article);
            }
        }
    }
}
