package braydo.linktree;

import java.util.ArrayList;

public class LinkNode {
    public ArrayList<LinkNode> children;
    private String data;

    public LinkNode(String link){
        this.data = link;
        this.children = new ArrayList<>();
    }

    public String toString(){
        return data;
    }

    public int numOfChildren() { return children.size();}


}
