package braydo.linktree;

public interface ScrapingStrategy {
    // checks if the method has access to a link
    boolean isAvailable(String siteLink);
    // grabs the html from a page
    String scrapWebPage(String individualLink);
}
