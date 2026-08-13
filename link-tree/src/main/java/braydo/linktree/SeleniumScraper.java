package braydo.linktree;

import org.openqa.selenium.*; // remember to change to specifics
import org.openqa.selenium.chrome.*;

import java.util.ArrayList;
import java.util.List;

enum OptionMode {
    EAGER,
    NORMAL,
    BACKGROUND
}

public class SeleniumScraper implements ScrapingStrategy {
    private final String UnavailableString = String.format("This result is unavailable on the %s strategy.", this.toString());
    private WebDriver currentWebDriver;

    @Override
    public boolean isAvailable(String siteLink) {
        createChromeDriver(OptionMode.EAGER);
        return connectToWebPage(siteLink);
    }

    @Override
    public List<String> scrapWebPage(String individualLink) {
        if (isAvailable(individualLink)){
            closeCurrentConnection(currentWebDriver);
            createChromeDriver(OptionMode.NORMAL);
        }
        else {
            return List.of(UnavailableString);
        }
        return grabWebSource(individualLink);
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

    private List<String> grabWebSource(String url){
        List<WebElement> links = currentWebDriver.findElements(By.tagName("a"));
        List<String> link_href = new ArrayList<>(links.size());
        for (WebElement link : links){
            link_href.add(link.getAttribute("href"));
        }
        return link_href; // placeholder for now
    }
}
