package braydo.linktree;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

/**
 * use this enum for different modes of selenium for optimising
 */
enum OptionMode {
    EAGER,
    NORMAL,
    BACKGROUND
}

/**
 * A scraper implementation that use selenium to load and find links by 'a' tag
 */
public class SeleniumScraper implements ScrapingStrategy {
    private final String UnavailableString = String.format("This result is unavailable on the %s strategy.", this.toString());
    private WebDriver currentWebDriver;

    @Override
    public boolean isAvailable(String siteLink) {
        createChromeDriver(OptionMode.EAGER);
        return connectToWebPage(siteLink);
    }

    @Override
    public ArrayList<String> scrapWebPage(String individualLink) {
        if (isAvailable(individualLink)){
            closeCurrentConnection(currentWebDriver);
            createChromeDriver(OptionMode.NORMAL);
        }
        else {
            return null;
        }
        return grabWebSource();
    }

    @Override
    public String toString(){
        return "Selenium Scraper";
    }

    private void createChromeDriver(OptionMode mode){
        WebDriver driver = new ChromeDriver(this.setDriverOptions(mode));
        this.setChromeDriver(driver);
    }

    private void closeCurrentConnection(WebDriver driver){
           driver.quit();
    }

    private void setChromeDriver(WebDriver driver){
        this.currentWebDriver = driver;
    }

    /**
     * this is where the mode can be switched for the chrome driver, add options to load in background
     * @param mode the enum of how fast/present the web driver should be
     * @return the options to attach to a WebDriver
     */
    private ChromeOptions setDriverOptions(OptionMode mode){
        ChromeOptions options = new ChromeOptions();
        // good to have arguments for all cases
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        switch(mode){
            case EAGER -> {
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                break;
            }
            case NORMAL -> {
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                break;
            }
            case BACKGROUND -> {
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.addArguments("--headless");
                break;
            }
        }
        return options;
    }

    private boolean connectToWebPage(String url){
        // TODO have to find a way to handle unauthorised requests
        try {
            currentWebDriver.get(url);
            return true;
        } catch (InvalidArgumentException e){
            return false;
        }
    }

    /**
     * Finds all link elements by tag name on a loaded web driver url page
     * @return a formed list of links (transformed from WebElements to String)
     */
    private ArrayList<String> grabWebSource(){
        ArrayList<WebElement> links = (ArrayList<WebElement>) currentWebDriver.findElements(By.tagName("a"));
        ArrayList<String> link_href = new ArrayList<>(links.size());
        for (WebElement link : links){
            link_href.add(link.getAttribute("href"));
        }
        return link_href;
    }
}
