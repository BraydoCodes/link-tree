import braydo.linktree.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// provides test coverage of LinkTree and LinkNode classes
public class TestLink {
    LinkNode l1 = new LinkNode(link1);
    LinkNode l2 = new LinkNode(link2);
    LinkNode l3 = new LinkNode(link3);
    LinkNode l4 = new LinkNode(link4);
    LinkNode l5 = new LinkNode(link5);
    LinkNode l6 = new LinkNode(link3);
    private static LinkTree lt1;
    private static final String link1 = "javaLink";
    private static final String link2 = "gradleLink";
    private static final String link3 = "wikiLink";
    private static final String link4 = "lLink";
    private static final String link5 = "wLink";

    private static final String linkDomain = "domain.com";

    public LinkTree setUpLinks(){
        // create a tree 1 > [2 > [3, 4], 5]
        LinkTree lt1 = new LinkTree(l1);
        l1.children.add(l2);
        l2.children.add(l3);
        l2.children.add(l4);
        l1.children.add(l5);

        return lt1;
    }
    @Test
    public void testToString() {
        assertEquals(link1, l1.toString());
        assertEquals(link2, l2.toString());
        assertEquals(link3, l3.toString());
    }
    @Test
    public void testNodeChildrenCount(){
        assertEquals(0, l1.numOfChildren());
    }
    @Test
    public void testGetters(){
        lt1 = setUpLinks();
        assertEquals(l1, lt1.getRoot());

        assertEquals(l2, lt1.getNode(link2));
        assertEquals(l3, lt1.getNode(link3));
        assertEquals(l4, lt1.getNode(link4));
    }
    @Test
    public void testLayerCount(){
        lt1 = setUpLinks();
        assertEquals(0,lt1.layerCount(l1));
        assertEquals(1,lt1.layerCount(l2));
        assertEquals(2,lt1.layerCount(l3));
        assertEquals(2,lt1.layerCount(l4));
        assertEquals(1,lt1.layerCount(l5));
        // test null case
        assertEquals(-1, lt1.layerCount(null));
    }
    @Test
    public void testDomain(){
        lt1 = setUpLinks();
        assertNull(lt1.getDomain());
        lt1.setDomain(linkDomain);
        assertEquals(linkDomain, lt1.getDomain());
    }
}
