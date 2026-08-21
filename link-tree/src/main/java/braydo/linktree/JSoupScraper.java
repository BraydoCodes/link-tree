package braydo.linktree;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Uses JSoup to extract the html elements on a given page, this only works for pages that are static or don't modify the hrefs
 * (typical causes of this are as such using front-end buttons)
 */
public class JSoupScraper implements ScrapingStrategy{
    private static final String targetCSSQuery = "a[href]";
    private static final String targetAttributeKey = "abs:href";

    @Override
    public boolean isAvailable(String siteLink) {
        return false;
    }

    @Override
    public ArrayList<String> scrapWebPage(String individualLink) {
        return handleJSoupConnection(individualLink);
    }

    /**
     * creates a jsoup connection to the url and finds all links in the source
     * @param url the url to scrap
     * @return all the links in a list of strings
     */
    private ArrayList<String> handleJSoupConnection(String url){
        ArrayList<String> linkList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select(targetCSSQuery);
            for(Element link: links){
                String absHref = link.attr(targetAttributeKey);
                linkList.add(absHref);
            }
        } catch (IOException e) {
            throw new InvalidUrlException("The URL inserted did not receive a valid response", e);
        }
        return linkList;
    }
}
