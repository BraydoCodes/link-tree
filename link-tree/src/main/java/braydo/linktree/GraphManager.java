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

        List<String> currentChildren = new ArrayList<String>();
        HashMap<String, List<String>> urls = new HashMap<String, List<String>>();
        HashMap<String, List<String>> nextUrls = new HashMap<String, List<String>>();
        while (canContinuePopulation){
            canContinuePopulation = checkSize();
            nextUrls = urls;
            clearHashMap(urls);
            if (iteration == 0){
                currentChildren = linkFinder.findAllLinks(scrapingMethod, startingUrl);
                if(!checkIfEmpty(currentChildren)){
                    urls.put(startingUrl, currentChildren);
                }
            }
            else {
                for (HashMap.Entry<String, List<String>> urlMap : nextUrls.entrySet()) {
                    addChildren(urlMap.getKey(), urlMap.getValue());

                    try {
                        for (String link : urlMap.getValue()) {
                            currentChildren = linkFinder.findAllLinks(scrapingMethod, link);
                            if (!checkIfEmpty(currentChildren)) {
                                urls.put(link, currentChildren);
                            } else {
                                linkTree.getNode(link).setEndPointStatus(true);
                            }
                            System.out.print(urls);
                        }
                    } catch (Exception e) {
                        System.out.print(e.toString());
                    }
                }
            }
            addIteration();
        }
        return linkTree;
    }
    private <E> void clearHashMap(HashMap<String, List<String>> hashMap){
        hashMap.clear();
    }
    private <E> boolean checkIfEmpty(List<E> list){
        return list.isEmpty();
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
