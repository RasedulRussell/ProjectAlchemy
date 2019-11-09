package com.projectalchemy.webCrawler;

import com.projectalchemy.DatabaseStorage.Database;

import java.io.IOException;

public class NewsperCrawler {

    private String homePageUrl ;
    private Database database;
    private WebCrawler crawler;

    public NewsperCrawler(String homePageUrl, Database database, WebCrawler crawler)
    {
        this.homePageUrl = homePageUrl;
        this.database = database;
        this.crawler = crawler;
    }

    public void Crawl() throws IOException {

        var urls = crawler.getSublinks(homePageUrl);
        for (var url : urls) {
            var article = crawler.getData(url);
            database.StoreData(article);
        }
    }
}
