package braydo.linktree;

import java.util.List;

public class LinkFinder {

    public List<String> findAllLinks(ScrapingStrategy webStrategy, String url){
        List<String> listOfLinks = webStrategy.scrapWebPage(url);
        return cleanUrls(listOfLinks);
    }
    private List<String> cleanUrls(List<String> urls){
        String invalidString = "#";
        urls.removeIf(link -> link.contains(invalidString));
        urls = removeDuplicates(urls);
        return urls;
    }

    private List<String> removeDuplicates(List<String> urls){
        return urls.stream().distinct().toList();

    }
}
