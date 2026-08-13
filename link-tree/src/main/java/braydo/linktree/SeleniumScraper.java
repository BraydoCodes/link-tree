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
    private final String UnavailableString = String.format("This result is unavailable on the %s method.", this.toString());
    private WebDriver currentWebDriver;

    @Override
    public boolean isAvailable(String siteLink) {
        createChromeDriver(OptionMode.EAGER);
        return connectToWebPage(siteLink);
    }

    @Override
    public String scrapWebPage(String individualLink) {
        if (isAvailable(individualLink)){
            closeCurrentConnection(currentWebDriver);
            createChromeDriver(OptionMode.NORMAL);
        }
        else {
            return UnavailableString;
        }
        return grabWebSource(individualLink);
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
        currentWebDriver.get(url);
        // TODO have to find a way to handle unauthorised requests
        return true;
    }

    private String grabWebSource(String url){
        return currentWebDriver.getTitle(); // placeholder for now
    }
}
