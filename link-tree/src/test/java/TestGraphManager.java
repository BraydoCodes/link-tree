import braydo.linktree.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestGraphManager {
    private static final String wikiURL = "https://www.wikipedia.org/";
    private static final String brokenURL = "ahtt//ww.a.acom";

    public GraphManager createGraphManager(int limit){
        return new GraphManager(limit);
    }

    @Test
    public void testGraphCreation(){
        GraphManager graphManager = createGraphManager(1);
        LinkTree result = graphManager.createGraph(wikiURL, wikiURL);
        assertInstanceOf(LinkTree.class, result);

        assertEquals(wikiURL, result.getRoot().toString());
        assertNotNull(result.getDomain());
        assertEquals(wikiURL, result.getDomain());
        assertNotNull(result.getNode(wikiURL));
    }
    @Test
    public void testAttemptsToCreateGraphWhenURLAlreadyExists(){
        GraphManager graphManager = createGraphManager(2);
        graphManager.createGraph(wikiURL, wikiURL);
        //try to create it again
        assertNull(graphManager.createGraph(wikiURL, wikiURL));
    }


    @Test
    public void testSetDomainName(){
        GraphManager graphManager = createGraphManager(1);
        graphManager.createGraph(wikiURL, wikiURL); // creating a graph is necessary
        assertTrue(graphManager.setDomainName(wikiURL));
    }

    @Test
    public void testAddChildren(){
        GraphManager graphManager = createGraphManager(1);
        LinkTree result = graphManager.createGraph(wikiURL, wikiURL);
        assertFalse(graphManager.createNextChildren(result.getRoot()));

        GraphManager graphManagerZero = createGraphManager(0);
        LinkTree resultZero = graphManagerZero.createGraph(wikiURL, wikiURL);
        assertFalse(graphManager.createNextChildren(resultZero.getRoot()));
    }
}
