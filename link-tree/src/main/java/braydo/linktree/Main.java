package braydo.linktree;

/**
 * the main class to start and end the program, try to reduce the amount of lines in this file
 */
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