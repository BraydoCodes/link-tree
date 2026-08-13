package braydo.linktree;

import java.util.List;

public class LinkFinder {

    public List<String> findAllLinks(ScrapingStrategy webStrategy, String url){
            return cleanUrls(webStrategy.scrapWebPage(url));
    }
    private List<String> cleanUrls(List<String> urls){
        String invalidString = "#";
        urls.removeIf(link -> link.contains(invalidString));
        return urls;
    }
}
