package newspapers;

import com.projectalchemy.models.Article;
import com.projectalchemy.webCrawler.WebCrawler;

import java.io.IOException;
import java.util.List;

public class ProthomAlo implements WebCrawler {

    @Override
    public Article getData(String url) throws IOException {
        return null;
    }

    @Override
    public List<String> getSublinks(String newsPaperHomeUrl) throws IOException {

        return null;
    }
}
