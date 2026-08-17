package braydo.linktree;

public class Main {
    private static final String urlPrompt = "Please enter a url: ";

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        GraphManager graphManager = new GraphManager(2);

        // testing
        boolean isValidURL = false;
        String hostName = null;
        String test_url = null;
        while (!isValidURL && hostName == null) {
            test_url = inputHandler.grabUserUrl(urlPrompt);
            if(test_url != null) {
                try {
                    hostName = inputHandler.getURLDomain(test_url);
                    isValidURL = true;
                } catch (InvalidUrlException e) {
                    System.out.println("Error Occurred for URL: " + e.getMessage());
                }
            }
            else {
                System.out.println("Please enter a valid url string");
            }
        }
        inputHandler.closeScannerConnection();
        graphManager.createGraph(test_url, hostName);
    }
}