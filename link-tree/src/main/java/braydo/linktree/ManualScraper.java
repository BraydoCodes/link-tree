package braydo.linktree;

import java.util.ArrayList;

/**
 * A version of that requires the user to manually insert links for pages that other scrapers cannot find
 * requires a dependency of input handler to handle this input
 * @see InputHandler
 */
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

    /**
     * will continually prompt the user for either links or quit to end
     * @param individualLink the original link used as confirmation
     * @return a created list with all user values to send to a link tree
     */
    private ArrayList<String> promptUserForLinks(String individualLink){
        ArrayList<String> finalListOfLinks = new ArrayList<String>();
        String userValue = "";
        System.out.print("Manual Scraping Required for '" + individualLink + "': Use " + inputHandler.getQuitPrompt() + " to finish this layer of links.\n");
        while (!userValue.equals(inputHandler.getQuitPrompt())){
            userValue = inputHandler.loopUntilValidResponse();
            finalListOfLinks.add(userValue);
        }
        return finalListOfLinks;
    }
}
