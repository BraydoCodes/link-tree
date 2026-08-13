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
    public List<String> scrapWebPage(String individualLink) {
        return handleJSoupConnection(individualLink);
    }

    private List<String> handleJSoupConnection(String url){
        List<String> linkList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for(Element link: links){
                String absHref = link.attr("abs:href");
                linkList.add(absHref);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return linkList;
    }
}
