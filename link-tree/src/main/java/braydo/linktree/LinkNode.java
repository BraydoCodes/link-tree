package braydo.linktree;

import java.util.ArrayList;
import java.util.List;

public class LinkNode {
    public ArrayList<LinkNode> children;
    private String data;
    private boolean isLeaf = true;

    public LinkNode(String link){
        this.data = link;
        this.children = new ArrayList<>();
    }

    public String toString(){
        return data;
    }

    public boolean getLeafStatus(){
        return isLeaf;
    }

    public void setLeafStatus(boolean changedStatus){
        this.isLeaf = changedStatus;
    }

    public int numOfChildren() { return children.size();}


}
