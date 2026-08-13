package braydo.linktree;

public class LinkFinder {

    public String findAllLinks(ScrapingStrategy webStrategy, String url){
            return webStrategy.scrapWebPage(url);
    }
}
