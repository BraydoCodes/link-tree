package braydo.linktree;

import java.util.ArrayList;

public interface ScrapingStrategy {
    // checks if the method has access to a link
    boolean isAvailable(String siteLink);
    // grabs the html from a page
    ArrayList<String> scrapWebPage(String individualLink);
}
