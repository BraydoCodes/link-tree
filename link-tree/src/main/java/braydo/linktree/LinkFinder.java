package braydo.linktree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Using a supplied strategy will organise and return links (url) found on a single supplied url.
 */
public class LinkFinder {
    private static final int MAXIMUM_THERASHOLD = 100;
    private List<String> old_url_list; // this can be used to tracked the latest url pool that is not restricted to 100 or MAXIMUM entries
    /**
     * @param webStrategy the strategy that should be used, currently Jsoup, Selenium and manual is available
     * @param url a single url.
     * @return a clean List of links in string format.
     */
    public List<String> findAllLinks(ScrapingStrategy webStrategy, String url){
        List<String> listOfLinks = webStrategy.scrapWebPage(url);
        return reduceSize(cleanUrls(listOfLinks));
    }

    /**
     * will filter through and remove duplicate entries in the url while also removing urls that redirect on the same page, commonly denoted by
     * '#'
     * @param urls all urls in a list
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

    /**
     * this will reduce the elements in a list in the case of a particularly large list, the maximum size a list can be is determined by a class variable
     * @param urls all URLs in a list
     * @return a shrunken list to the size under MAXIMUM THRESHOLD (is randomised in this case)
     */
    private List<String> reduceSize(List<String> urls){
        old_url_list = urls; // save these urls to access later if needed
        List<String> new_urls = new ArrayList<String>();
        Random ran = new Random();
        if(urls.size() >= MAXIMUM_THERASHOLD){
            for(int i = 1; i < MAXIMUM_THERASHOLD; i++){
                int newInt = ran.nextInt(MAXIMUM_THERASHOLD); // used to shuffle the urls for a random selection pull
                new_urls.add(urls.get(newInt));}
        }
        return new_urls;
    }
    private List<String> removeDuplicates(List<String> urls){
        return urls.stream().distinct().toList();

    }
}
