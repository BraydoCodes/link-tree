import braydo.linktree.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class TestInputHandler {
    private static final String wikiURL = "https://www.wikipedia.org/";
    private static final String brokenURL = "ahtt//ww.a.acom";

    public InputHandler createInputHandler(){
        return new InputHandler();
    }
    @Test
    public void testGrabUserUrlResponse(){
        InputHandler inputHandler = createInputHandler();
        String correct = inputHandler.grabUserUrl(wikiURL);
        assertEquals(wikiURL, correct);
        String incorrect = inputHandler.grabUserUrl(brokenURL);
        assertNull(incorrect);
        String defaultR = inputHandler.grabUserUrl("default");
        assertEquals("https://en.wikipedia.org/", defaultR);
    }
    @Test
    public void testDomain(){
        InputHandler inputHandler = createInputHandler();
        String correctDomain = inputHandler.getURLDomain(wikiURL);
        assertEquals("www.wikipedia.org", correctDomain);
        assertDoesNotThrow(() -> inputHandler.getURLDomain(wikiURL));
        assertThrows(InvalidUrlException.class, () ->  { inputHandler.getURLDomain(brokenURL); });
    }

    @Test
    public void testClosingScanner(){
        InputHandler inputHandler = createInputHandler();
        assertDoesNotThrow(inputHandler::closeScannerConnection);
    }

    @Test
    public void testLoopingFunction(){
        InputHandler inputHandler = createInputHandler();
        spy(inputHandler);

        inputHandler.loopUntilValidResponse();
        //verify(inputHandler).grabUserUrl(wikiURL);

    }
}
