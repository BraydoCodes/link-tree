import braydo.linktree.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// provides test coverage of LinkTree and LinkNode classes
public class TestLink {
    private static LinkNode l1;
    private static LinkNode l2;
    private static LinkNode l3;
    private static LinkNode l4;
    private static LinkNode l5;
    private static LinkNode l6;
    private static LinkTree lt1;
    private static final String link1 = "javaLink";
    private static final String link2 = "gradleLink";
    private static final String link3 = "wikiLink";

    @BeforeAll
    public static void setUpLinks(){
        LinkNode l1 = new LinkNode(link1);
        LinkNode l2 = new LinkNode(link2);
        LinkNode l3 = new LinkNode(link3);
        LinkNode l4 = new LinkNode(link1);
        LinkNode l5 = new LinkNode(link2);
        LinkNode l6 = new LinkNode(link3);

        // create a tree 1 > [2 > [3, 4], 5]
        LinkTree lt1 = new LinkTree(l1, 10);
        l1.children.add(l2);
        l2.children.add(l3);
        l2.children.add(l4);
        l1.children.add(l5);
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
        assertEquals(l1, lt1.getRoot());

        assertEquals(l2, lt1.getNode(link2));
        assertEquals(l3, lt1.getNode(link3));
        assertEquals(l4, lt1.getNode(link1));
    }
    @Test
    public void testLayerCount(){
        assertEquals(0,lt1.layerCount(l1));
        assertEquals(1,lt1.layerCount(l2));
        assertEquals(2,lt1.layerCount(l3));
        assertEquals(2,lt1.layerCount(l4));
        assertEquals(1,lt1.layerCount(l5));
    }

}
