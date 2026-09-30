package braydo.linktree;

import java.util.ArrayList;

/**
 * A simple node like class that has URL information as its 'data'
 */
public class LinkNode {
    public ArrayList<LinkNode> children;
    private String data;

    public LinkNode(String link){
        this.data = link;
        this.children = new ArrayList<>();
    }

    @Override
    public String toString(){
        String branchString = "(".concat("*").concat(this.getData()).concat("*");
        if(!this.children.isEmpty()){
            branchString = branchString.concat("->\n");
        }
        for(LinkNode node : children){
            branchString = branchString.concat(node.toString());
            if (node.children.isEmpty()){
                branchString = branchString.concat("\n_______________\n");
            } else{
                branchString = branchString.concat("\nV\n");
            }
        }
        branchString = branchString.concat(")");
        return branchString;
    }

    public String getData(){ return data; }

    public int numOfChildren() { return children.size();}


}
