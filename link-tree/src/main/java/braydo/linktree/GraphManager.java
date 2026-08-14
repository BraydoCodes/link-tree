package braydo.linktree;

import java.util.List;

public class GraphManager {
    int limit = 0;
    private int iteration = 0;
    LinkTree linkTree;

    public GraphManager(int limit){
        this.limit = limit;
    }

    public LinkTree createGraph(String startingUrl){
        linkTree = new LinkTree(new LinkNode(startingUrl));
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
        if(iteration < limit){
            iteration += 1;
            return true;
        }
        return false;
    }
}
