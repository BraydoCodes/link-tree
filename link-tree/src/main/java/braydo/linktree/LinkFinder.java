package braydo.linktree;

import java.util.HashMap;
import java.util.List;

public class LinkFinder {

    public HashMap<String, List<String>> findAllLinks(ScrapingStrategy webStrategy, String url){
        HashMap<String, List<String>> urlMapped = new HashMap<String, List<String>>();
        urlMapped.put(url, webStrategy.scrapWebPage(url));
        return urlMapped;
    }
    private List<String> cleanUrls(List<String> urls){
        String invalidString = "#";
        urls.removeIf(link -> link.contains(invalidString));
        return urls;
    }
}
