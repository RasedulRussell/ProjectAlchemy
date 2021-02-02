package com.projectalchemy.webCrawler;

import com.projectalchemy.models.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestCrawler implements WebCrawler {

    @Override
    public Article getData(String url) throws IOException {
        var article =  new Article();
        article.setUrl(url);
        article.setTitle("Test title");
        article.setDetails("details");
        return article;
    }

    @Override
    public List<String> getSublinks(String url) throws IOException {
        var list = new ArrayList<String>();
        for (int i=0; i<1000000; i++)
        {
            list.add(UUID.randomUUID().toString());
        }

        return list;
    }
}
