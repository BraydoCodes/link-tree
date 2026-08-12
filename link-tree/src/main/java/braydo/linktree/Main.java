package braydo.linktree;
public class Main {
    public static void main(String[] args) {
        LinkNode root = new LinkNode("s");
        LinkNode n1 = new LinkNode("a");
        LinkNode n2 = new LinkNode("b");
        LinkNode n3 = new LinkNode("c");
        LinkNode n4 = new LinkNode("d");
        root.children.add(n1);
        n1.children.add(n2);
        root.children.add(n3);
        n3.children.add(n4);

        LinkTree LinkTreeTest = new LinkTree(root);
        LinkNode dNode = LinkTreeTest.getNode(root, "d");
        System.out.println(dNode.toString());


    }
}