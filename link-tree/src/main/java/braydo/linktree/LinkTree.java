package braydo.linktree;

import java.util.HashMap;
import java.util.List;

public class LinkTree {
    private LinkNode root;
    private static LinkFinder linkFinder = new LinkFinder();
    private static ScrapingStrategy scrapingMethod;
    private int iteration = 0;
    private String domainName;

    public LinkTree(LinkNode root, int limit){
        this.root = root;
        this.root.setLeafStatus(true);
        this.iteration = limit;

        scrapingMethod = new JSoupScraper();
    }
    public LinkNode getRoot(){
        return root;
    }
    // need to check for null on operations involving this function
    private LinkNode getChildren(LinkNode node, String search){
        if(search.equals(node.toString())){
            return node;
        }
        for (int i = 0; i < node.children.size(); i++){
            if(checkNodeToData(getChildren(node.children.get(i),search),search)){
                return node.children.get(i);
            }
        }
        return null;
    }

    public LinkNode getNode(String search) {
        return getChildren(root, search);
    }
    public void setDomain(String domainName){ this.domainName = domainName; }

    public boolean addNode(String nodeData, LinkNode previousNode) {
        previousNode.children.add(new LinkNode(nodeData));
        previousNode.setLeafStatus(false);
        return true;
    }

    public int layerCount(LinkNode node){
        if(node.toString().equals(root.toString())){
            return 0;
        }
        for (int i = 0; i < node.children.size(); i++){
            String search = node.toString();
            if(checkNodeToData(getChildren(node.children.get(i),search),search)){
                return i;
            }
        }
        return -1;
    }
    private boolean checkNodeToData(LinkNode node, String data) {
        if(node != null){
            return node.toString().equals((data));
        }
        else{
            return  false;
        }
    }


    public boolean createNextChildren(LinkNode linkNode) {
        List<String> links =  linkFinder.findAllLinks(scrapingMethod, linkNode.toString());
        if(iteration > layerCount(linkNode)) {
            for (String link : links) {
                boolean added = addNode(link, linkNode);
                if (added && link.contains(domainName)) {
                    return createNextChildren(getNode(link));
                }
            }
        }
        return false;
    }
}
