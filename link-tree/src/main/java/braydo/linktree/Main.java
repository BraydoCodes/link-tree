package braydo.linktree;


import java.net.URI;

public class Main {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        GraphManager graphManager = new GraphManager(2);
        // testing
        String userInput = inputHandler.loopUntilValidResponse();
        if (!userInput.equals(inputHandler.getQuitPrompt())) {
            graphManager.createGraph(userInput, inputHandler.getCurrentHostName());
        }

        System.out.print(inputHandler.getQuitPrompt());

        inputHandler.closeScannerConnection(); //I can't find a good design for closing the stream here as it is needed for the manual one
    }
}