package braydo.linktree;

import java.util.*;

public class GraphManager { ;
    int limit = 0;
    LinkTree linkTree = null;

    public GraphManager(int limit){
        this.limit = limit;
    }

    private boolean createGraphTree(String startingUrl){
        if(linkTree == null) {
            linkTree = new LinkTree(new LinkNode(startingUrl), limit);
            return true;
        }
        else {
            return false;
        }
    }
    public LinkTree createGraph(String startingUrl){
        createGraphTree(startingUrl);
        linkTree.createNextChildren(linkTree.getRoot());
        return linkTree;
    }
    private boolean updateLinkTree(List<String> urls, String originalLink){
        for(String link : urls) {
            linkTree.addNode(link, linkTree.getNode(originalLink));
        }
        return true;
    }

    private <E> void clearHashMap(HashMap<String, List<String>> hashMap){
        hashMap.clear();
    }
    private <E> boolean checkIfEmpty(List<E> list){
        return list.isEmpty();
    }

}
