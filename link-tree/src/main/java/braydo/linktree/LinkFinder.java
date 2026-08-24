package braydo.linktree;

import java.util.List;

/**
 * Using a supplied strategy will organise and return links (url) found on a single supplied url.
 */
public class LinkFinder {
    private static final int MAXIMUM_THERASHOLD = 100;

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
     * this will reduce the elements in a list in the case of a particularly large list, the maxium size a list can be is determined by a class variable
     * @param urls all urls in a list
     * @return a shrunken list to the size under MAXIMUM THRESHOLD
     */
    private List<String> reduceSize(List<String> urls){
        if(urls.size() > MAXIMUM_THERASHOLD){
            int num_of_iterations = urls.size() - MAXIMUM_THERASHOLD;
            for(int i = 1; i < num_of_iterations; i++){
                urls.removeLast();
            }
        }
        return urls;
    }
    private List<String> removeDuplicates(List<String> urls){
        return urls.stream().distinct().toList();

    }
}
