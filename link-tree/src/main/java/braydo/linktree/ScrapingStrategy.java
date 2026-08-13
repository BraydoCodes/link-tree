package braydo.linktree;

import java.util.List;

public interface ScrapingStrategy {
    // checks if the method has access to a link
    boolean isAvailable(String siteLink);
    // grabs the html from a page
    List<String> scrapWebPage(String individualLink);
}
