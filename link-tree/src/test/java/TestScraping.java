import braydo.linktree.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestScraping {
    private static final String wikiURL = "https://www.wikipedia.org/";
    private static final String brokenURL = "ahtt//ww.a.acom";

    public ScrapingStrategy createSeleniumScraper(){
        return new SeleniumScraper();
    }
    public ScrapingStrategy createJSoupScraper(){
        return new JSoupScraper();
    }
    public ScrapingStrategy createManualScraper(){
        return new ManualScraper();
    }

    @Test
    public void testSeleniumURLs(){
        ScrapingStrategy Selenium = createSeleniumScraper();
        assertNotNull(Selenium.scrapWebPage(wikiURL));
    }

    @Test
    public void testSeleniumConnection(){
        ScrapingStrategy Selenium = createSeleniumScraper();
        assertTrue(Selenium.isAvailable(wikiURL));
        assertFalse(Selenium.isAvailable(brokenURL));
    }

    @Test
    public void testJSoupURLs(){
        ScrapingStrategy JSoup = createJSoupScraper();
        assertNotNull(JSoup.scrapWebPage((wikiURL)));
        assertThrows(InvalidUrlException.class, () ->  { JSoup.scrapWebPage(brokenURL); });
        assertDoesNotThrow(() -> JSoup.scrapWebPage(wikiURL));
    }

    @Test
    public void testManualScraper(){
        ScrapingStrategy Manual = createManualScraper();
        assertNotNull(Manual.scrapWebPage((wikiURL)));
        assertEquals(0,Manual.scrapWebPage(brokenURL).size());
    }
}
