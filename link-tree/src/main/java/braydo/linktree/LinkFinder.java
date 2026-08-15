package braydo.linktree;


import java.util.ArrayList;
import java.util.List;

public class LinkFinder {

    public List<String> findAllLinks(ScrapingStrategy webStrategy, String url){
        List<String> listOfLinks = webStrategy.scrapWebPage(url);
        return cleanUrls(listOfLinks);
    }
    private List<String> cleanUrls(List<String> urls){
        String invalidString = "#";
        urls.removeIf(link -> link.contains(invalidString));
        return urls;
    }
}
