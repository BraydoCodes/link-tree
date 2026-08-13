package braydo.linktree;
public class Main {
    private static ScrapingStrategy scrapingMethod;
    private static LinkFinder linkFinder = new LinkFinder();

    public static void main(String[] args) {
        LinkNode root = new LinkNode("s");
        LinkNode n1 = new LinkNode("a");
        System.out.print(n1.numOfChildren());
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

        // testing a hardcoded
        String test_url = "https://en.wikipedia.org/";
        scrapingMethod = new JSoupScraper();
        System.out.print(linkFinder.findAllLinks(scrapingMethod, test_url));
    }
}