package braydo.linktree;

public class LinkTree {
    private LinkNode root;

    public LinkTree(LinkNode root){
        this.root = root;
    }
    public LinkNode getRoot(){
        return root;
    }
    // need to check for null on operations involving this function
    public LinkNode getNode(LinkNode node, String search){
        if(search.equals(node.toString())){
            return node;
        }
        for (int i = 0; i < node.children.size(); i++){
            if(checkNodeToData(getNode(node.children.get(i),search),search)){
                return node.children.get(i);
            }
        }
        return null;
    }

    public boolean addNode(LinkNode node, LinkNode previousNode) {
        previousNode.children.add(node);
        return true;
    }

    public int layerCount(LinkNode node){
        if(node.toString().equals(root.toString())){
            return 0;
        }
        for (int i = 0; i < node.children.size(); i++){
            String search = node.toString();
            if(checkNodeToData(getNode(node.children.get(i),search),search)){
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
