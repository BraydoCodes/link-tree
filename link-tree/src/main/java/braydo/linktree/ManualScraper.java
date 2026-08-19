package braydo.linktree;

import java.util.ArrayList;

public class ManualScraper implements ScrapingStrategy{
    InputHandler inputHandler = new InputHandler();

    @Override
    public boolean isAvailable(String siteLink) {
        return false;
    }

    @Override
    public ArrayList<String> scrapWebPage(String individualLink) {
        return promptUserForLinks(individualLink);
    }

    private ArrayList<String> promptUserForLinks(String individualLink){
        ArrayList<String> finalListOfLinks = new ArrayList<String>();
        String userValue = "";
        System.out.print("Manual Scraping Required: Use " + inputHandler.getQuitPrompt() + " to finish this layer of links.\n");
        while (!userValue.equals(inputHandler.getQuitPrompt())){
            userValue = inputHandler.loopUntilValidResponse();
            finalListOfLinks.add(userValue);
        }
        return finalListOfLinks;
    }
}
