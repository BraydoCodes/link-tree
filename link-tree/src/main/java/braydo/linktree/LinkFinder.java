package braydo.linktree;

import java.util.List;

public class LinkFinder {

    public List<String> findAllLinks(ScrapingStrategy webStrategy, String url){
            return webStrategy.scrapWebPage(url);
    }
}
