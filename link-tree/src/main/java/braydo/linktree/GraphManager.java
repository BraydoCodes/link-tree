package braydo.linktree;

import java.util.List;

public class GraphManager { ;
    int limit = 0;
    LinkTree linkTree = null;
    private static LinkFinder linkFinder = new LinkFinder();
    private static ScrapingStrategy scrapingMethod;

    public GraphManager(int limit){
        this.limit = limit;

        scrapingMethod = new JSoupScraper();
    }

    private boolean createGraphTree(String startingUrl){
        if(linkTree == null) {
            linkTree = new LinkTree(new LinkNode(startingUrl));
            return true;
        }
        else {
            return false;
        }
    }

    public LinkTree createGraph(String startingUrl){
        createGraphTree(startingUrl);
        createNextChildren(linkTree.getRoot());
        return linkTree;
    }

    public boolean setDomainName(String domainName){
        linkTree.setDomain(domainName);
        return true;
    }

    public boolean createNextChildren(LinkNode linkNode) {
        List<String> links =  linkFinder.findAllLinks(scrapingMethod, linkNode.toString());
        if(limit > linkTree.layerCount(linkNode)) {
            for (String link : links) {
                boolean added = linkTree.addNode(link, linkNode);
                if (added && link.contains(linkTree.getDomain())) {
                    return createNextChildren(linkTree.getNode(link));
                }
            }
        }
        return false;
    }

}
