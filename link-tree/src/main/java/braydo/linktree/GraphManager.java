package braydo.linktree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphManager {
    private static LinkFinder linkFinder = new LinkFinder();
    private static ScrapingStrategy scrapingMethod;
    int limit = 0;
    private int iteration = 0;
    LinkTree linkTree;

    public GraphManager(int limit){
        this.limit = limit;
        scrapingMethod = new JSoupScraper();
    }

    private LinkTree createGraphTree(String startingUrl){
        linkTree = new LinkTree(new LinkNode(startingUrl));
        return linkTree;
    }
    public LinkTree createGraph(String startingUrl){
        createGraphTree(startingUrl);
        boolean canContinuePopulation = checkSize();

        HashMap<String, List<String>> urls = new HashMap<String, List<String>>();
        while (canContinuePopulation){
            canContinuePopulation = checkSize();

            if (urls.isEmpty()){
                urls = linkFinder.findAllLinks(scrapingMethod, startingUrl);
            }

            for (HashMap.Entry<String, List<String>> urlMap : urls.entrySet()) {
                List<String> links = new ArrayList<>();
                addChildren(urlMap.getKey(), urlMap.getValue());
                try {
                    for (String link : urlMap.getValue()) {
                        urls = linkFinder.findAllLinks(scrapingMethod, link);
                        System.out.print(urls);
                    }
                } catch (Exception e) {
                    System.out.print(e.toString());
                }
            }
        }
        return linkTree;
    }
    public boolean addChildren(String previousURL, List<String> urlLinks){
        boolean ableToInsert = addIteration();
        if(ableToInsert){
            LinkNode previousNode = linkTree.getNode(previousURL);
            for(String link : urlLinks){
                linkTree.addNode(link, previousNode);
            }
        }
        return ableToInsert;
    }

    private boolean addIteration(){
        if(checkSize()){
            iteration += 1;
            return true;
        }
        return false;
    }

    public boolean checkSize(){
        return iteration < limit;
    }
}
