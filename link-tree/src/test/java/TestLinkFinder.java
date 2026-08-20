import braydo.linktree.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestLinkFinder {
    private static final String wikiURL = "https://www.wikipedia.org/";
    private static final String brokenURL = "ahtt//ww.a.acom";
    @Test
    public void invalidURL(){
        LinkFinder linkFinder = new LinkFinder();
        assertThrows(IllegalArgumentException.class, () -> linkFinder.findAllLinks(new JSoupScraper(), brokenURL));
    }
    @Test
    public void validURL(){
        LinkFinder linkFinder = new LinkFinder();
        List<String> resultsNotNull = linkFinder.findAllLinks(new JSoupScraper(), wikiURL);
        assertNotNull(resultsNotNull);
        assertNotEquals(0, resultsNotNull.size());
    }

    // TODO write more tests with mocks.
    @Test
    public void testScrapingMock(){
        ScrapingStrategy mockedStrategy = mock(ScrapingStrategy.class);

        when(mockedStrategy.isAvailable((wikiURL))).thenReturn(true);
        when(mockedStrategy.isAvailable((brokenURL))).thenReturn(false);

        verify(mockedStrategy, never()).scrapWebPage(wikiURL);
        assertTrue(mockedStrategy.isAvailable(wikiURL));
        verify(mockedStrategy, never()).scrapWebPage(brokenURL);
        assertFalse(mockedStrategy.isAvailable(brokenURL));
    }
}
