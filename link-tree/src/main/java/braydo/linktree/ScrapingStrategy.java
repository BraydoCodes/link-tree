package braydo.linktree;

import java.util.ArrayList;

/**
 * this defines the methods for a scraping class, where the goal is to return a list of elements that are 'links'
 */
public interface ScrapingStrategy {
    /**
     * this can be used for tools that require verification of a links availability when a user enters a url
     * @param siteLink an HTTPS site link or domain
     * @return a boolean based on the sites availability
     */
    boolean isAvailable(String siteLink);

    /**
     * Scraps a web pages in a way where it finds/gets a list of links that it should return
     * @param individualLink that should be handled prior
     * @return a url (not href) string list in ArrayList implementation
     */
    ArrayList<String> scrapWebPage(String individualLink);
}
