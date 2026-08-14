package braydo.linktree;

import java.util.HashMap;
import java.util.List;

public class LinkTree {
    private LinkNode root;

    public LinkTree(LinkNode root){
        this.root = root;
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

    public boolean addNode(String nodeData, LinkNode previousNode) {
        previousNode.children.add(new LinkNode(nodeData));
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

    public HashMap<LinkNode, List<LinkNode>> getAllLeafParentPairings(){
        //create a list of all 'parents' at iteration - 1 (add this variable)
        //check the children do not have state 'cannot continue'
        //add to the hashmap
        return null;
    }
}
