package braydo.linktree;

import java.util.List;

public class GraphManager { ;
    int limit = 0;
    LinkTree linkTree = null;
    private static LinkFinder linkFinder = new LinkFinder();
    private static ScrapingStrategy scrapingMethod;
    private static final GraphTranslator<String> graphTranslator = new GraphTranslator<String>();

    public GraphManager(int limit){
        this.limit = limit;

        scrapingMethod = new JSoupScraper();
    }

    private boolean createGraphTree(String startingUrl){
        if(linkTree == null) {
            linkTree = new LinkTree(new LinkNode(startingUrl));
            graphTranslator.printMultiple("Creating LinkTree", startingUrl + " doesn't exist", "Created tree");
            return true;
        }
        else {
            graphTranslator.printSingle(startingUrl + " already used.");
            return false;
        }
    }

    public LinkTree createGraph(String startingUrl, String domainName){
        createGraphTree(startingUrl);
        setDomainName(domainName);
        createNextChildren(linkTree.getRoot());
        return linkTree;
    }

    public boolean setDomainName(String domainName){
        linkTree.setDomain(domainName);
        graphTranslator.printSingle(" tree setting domain to " + domainName);
        return true;
    }

    public boolean createNextChildren(LinkNode linkNode) {
        graphTranslator.setCurrentState(State.WORKING);
        graphTranslator.printMultiple("Finding links on page", linkNode.toString());
        List<String> links =  linkFinder.findAllLinks(scrapingMethod, linkNode.toString());
        graphTranslator.printList(links);
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
