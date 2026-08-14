package braydo.linktree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static ScrapingStrategy scrapingMethod;
    private static LinkFinder linkFinder = new LinkFinder();

    private static final String urlPrompt = "Please enter a url: ";

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        GraphManager graphManager = new GraphManager(2);

        // testing
        String test_url = inputHandler.grabUserUrl(urlPrompt);
        graphManager.createGraph(test_url);
        scrapingMethod = new JSoupScraper();
        boolean canContinuePopulation = graphManager.checkSize();

        HashMap<String, List<String>> urls = new HashMap<String, List<String>>();
        while (canContinuePopulation){
            canContinuePopulation = graphManager.checkSize();

            if (urls.isEmpty()){
                urls = linkFinder.findAllLinks(scrapingMethod, test_url);
            }

            try {
                urls = linkFinder.findAllLinks(scrapingMethod, test_url);
                System.out.print(urls);
            } catch (Exception e) {
                System.out.print(e.toString());
            }

            for (HashMap.Entry<String, List<String>> urlMap :
                    urls.entrySet()) {
                graphManager.addChildren(urlMap.getKey(), urlMap.getValue());
            }
        }
    }
}