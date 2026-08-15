package braydo.linktree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupScraper implements ScrapingStrategy{
    @Override
    public boolean isAvailable(String siteLink) {
        return false;
    }

    @Override
    public ArrayList<String> scrapWebPage(String individualLink) {
        return handleJSoupConnection(individualLink);
    }

    private ArrayList<String> handleJSoupConnection(String url){
        ArrayList<String> linkList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for(Element link: links){
                String absHref = link.attr("abs:href");
                linkList.add(absHref);
            }
        } catch (Exception e) {
            throw new InvalidUrlException("The URL inserted did not receive a valid response");
        }
        return linkList;
    }
}
