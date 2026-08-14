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

    }
}