package com.projectalchemy.webCrawler;

import com.projectalchemy.models.Article;

import java.io.IOException;
import java.util.List;

public interface WebCrawler {

    public Article getData(String url) throws IOException;
    public List<String> getSublinks(String url) throws IOException;
}
