package braydo.linktree;

import java.util.List;

/**
 * Using a supplied strategy will organise and return links (url) found on a single supplied url.
 */
public class LinkFinder {

    /**
     * @param webStrategy the strategy that should be used, currently Jsoup, Selenium and manual is available
     * @param url a single url.
     * @return a clean List of links in string format.
     */
    public List<String> findAllLinks(ScrapingStrategy webStrategy, String url){
        List<String> listOfLinks = webStrategy.scrapWebPage(url);
        return cleanUrls(listOfLinks);
    }

    /**
     * will filter through and remove duplicate entries in the url while also removing urls that redirect on the same page, commonly denoted by
     * '#'
     * @param urls
     * @return clean List
     */
    private List<String> cleanUrls(List<String> urls){
        String invalidString = "#";
        if(!urls.isEmpty()) {
            urls.removeIf(link -> link.contains(invalidString));
            urls = removeDuplicates(urls);
        }
        return urls;
    }

    private List<String> removeDuplicates(List<String> urls){
        return urls.stream().distinct().toList();

    }
}
