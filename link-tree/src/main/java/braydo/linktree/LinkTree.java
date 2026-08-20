package braydo.linktree;

public class LinkTree {
    private LinkNode root;
    private String domainName = null;

    public LinkTree(LinkNode root){
        this.root = root;
    }
    public LinkNode getRoot(){
        return root;
    }
    // need to check for null on operations involving this function
    private LinkNode getChildren(LinkNode node, String search){
        System.out.println(search + " searching...");
        if(search.equals(node.toString())){
            System.out.println(search + " found node...");
            return node;
        }
        for (int i = 0; i < node.children.size(); i++){
            System.out.println(search + " I am a children in the loop..." + i);
            if(checkNodeToData(getChildren(node.children.get(i),search),search)){
                System.out.println(search + " I am the search...");
                return node.children.get(i);
            }
        }
        return null;
    }

    public LinkNode getNode(String search) {
        return getChildren(root, search);
    }
    public String getDomain(){ return domainName; }
    public void setDomain(String domainName){ this.domainName = domainName; }

    public boolean addNode(String nodeData, LinkNode previousNode) {
        previousNode.children.add(new LinkNode(nodeData));
        return true;
    }

    //TODO FIX
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
}
