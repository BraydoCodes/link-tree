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

    public String toString(){
        return data;
    }

    public int numOfChildren() { return children.size();}


}
