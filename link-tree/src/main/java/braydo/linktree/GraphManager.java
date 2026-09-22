package braydo.linktree;

import java.util.HashSet;
import java.util.List;

/**
 * A GraphManagers manages a group of LinkTrees, at any given time it is subject to work on 'one' linktree at a given time
 */
public class GraphManager { ;
    int limit = 0; // the number of iterations that the tree should be limited to.
    private LinkTree currentLinkTree = null;
    private List<LinkTree> linkTreeStorage = null;
    private static LinkFinder linkFinder = new LinkFinder();
    private static ScrapingStrategy scrapingMethod;
    private static final GraphTranslator<String> graphTranslator = new GraphTranslator<String>();

    private static HashSet<String> uniqueDomains = new HashSet<String>(); // this tracks all outside domains

    private static final int hiddenLinksThreshold = 1; // number of links that tells the system that this will require manual mode

    public GraphManager(int limit){
        this.limit = limit;

        scrapingMethod = new JSoupScraper();
    }

    private boolean createGraphTree(String startingUrl){
        if(currentLinkTree == null) {
            currentLinkTree = new LinkTree(new LinkNode(startingUrl));
            graphTranslator.printMultiple("Creating LinkTree", startingUrl + " doesn't exist", "Created tree");
            return true;
        }
        else {
            if(startingUrl.contains(currentLinkTree.getDomain()) && currentLinkTree.getNode(startingUrl) != null) {
                graphTranslator.printSingle(startingUrl + " already used.");
                return false;
            }
            addLinkTreeToStorage(currentLinkTree);
            graphTranslator.printSingle("Moving previous link tree");
            currentLinkTree = new LinkTree(new LinkNode(startingUrl));
            graphTranslator.printMultiple("Creating LinkTree", startingUrl + " does exist, so it is being replaced", "Created tree");
            return true;
        }
    }

    /**
     * creates a graph (LinkTree), manages the process including domain setting and children creation
     * @param startingUrl the user entered URL that ideally has links on the page
     * @param domainName the host name to fall back on if links are found externally
     * @return
     */
    public LinkTree createGraph(String startingUrl, String domainName){
        if(createGraphTree(startingUrl)) {
            setDomainName(domainName);
            createNextChildren(currentLinkTree.getRoot());
            return currentLinkTree;
        } else {
            return null;
        }
    }

    public boolean setDomainName(String domainName){
        if(currentLinkTree != null) {
            currentLinkTree.setDomain(domainName);
            graphTranslator.printSingle(" tree setting domain to " + domainName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * creates the children (via recursion) from a node in a LinkTree
     * @param linkNode the node where the child node will be branched.
     * @return boolean of whether a child was made
     */
    public boolean createNextChildren(LinkNode linkNode) {
        graphTranslator.setCurrentState(State.WORKING);
        List<String> links = handleLinksForLinkNode(linkNode);
        graphTranslator.printList(links);
        if(limit > currentLinkTree.layerCount(linkNode)) {
            for (String link : links) {
                boolean added = currentLinkTree.addNode(link, linkNode);
                if (added && link.contains(currentLinkTree.getDomain())) {
                    return createNextChildren(currentLinkTree.getNode(link));
                }
                else {
                    uniqueDomains.add(linkNode.toString());
                }
            }
        }
        return false;
    }

    /**
     * Handles the process (both auto and manual) for grabbing all links on a given linknode
     * @param linkNode, for a given link node group the child nodes (by scraping using the web scraper strategy)
     * @return links - a list of strings with all manual links
     */
    private List<String> handleLinksForLinkNode(LinkNode linkNode){
        graphTranslator.printMultiple("Finding links on page", linkNode.toString());
        List<String> links =  linkFinder.findAllLinks(scrapingMethod, linkNode.toString());
        if(links.size() < hiddenLinksThreshold){
            //activate user manual mode
            scrapingMethod = new ManualScraper();
            links = linkFinder.findAllLinks(scrapingMethod, linkNode.toString());
        }
        return  links;
    }

    private void printAllOutsideDomains(){
        System.out.println("Printing all outside domains.. \n");
        for (String url : uniqueDomains) {
            System.out.println(url);
        }
    }

    /**
     * Moves a linktree to storage and resets the current working link tree ready to process another
     * @param cTree, the current or currently being worked on LinkTree
     */
    private void addLinkTreeToStorage(LinkTree cTree) {
        linkTreeStorage.add(cTree);

        if(cTree == currentLinkTree){
            currentLinkTree = null;
        }
    }

}
