package com.projectalchemy.webCrawler;

import com.projectalchemy.DatabaseStorage.Database;
import com.projectalchemy.util.UrlValidator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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

        System.out.println("Total link count " + urls.size());
        int count = 0;
        System.out.println(urls.get(35));
        for (var url : urls) {
            ///System.out.println(++count + ":Url to crawl " + url);
            if (UrlValidator.isValid(url)) {
                var article = crawler.getData(url);
                if (article.getDetails().length() > 0)
                    database.StoreData(article);
            }
            ///System.out.println( "is valid "+ UrlValidator.isValid(url)+ " " + ++count +" execute");
            if( count == 34 ){
                System.out.println(url);
                ///break;
            }
        }
    }
}
