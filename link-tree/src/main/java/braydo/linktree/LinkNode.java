package braydo.linktree;

import java.util.ArrayList;
public class LinkNode {
    public ArrayList<LinkNode> children;
    private String data;
    private boolean endPointStatus;

    public LinkNode(String link){
        this.data = link;
        this.children = new ArrayList<>();
    }

    public String toString(){
        return data;
    }

    public boolean getEndPointStatus(){
        return endPointStatus;
    }

    public void setEndPointStatus(boolean changedStatus){
        this.endPointStatus = changedStatus;
    }

    public int numOfChildren() { return children.size();}

}
