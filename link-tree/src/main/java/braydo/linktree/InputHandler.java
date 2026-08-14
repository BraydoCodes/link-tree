package braydo.linktree;
import java.util.Scanner;
import java.net.URI;


public class InputHandler {
    private static final String defaultURL = "https://en.wikipedia.org/";
    public String grabUserUrl(String userPrompt){
        System.out.print(userPrompt);
        Scanner scanner = new Scanner(System.in);
        return grabUserInput(scanner);
    }
    // currently defaults on typo, ideally I add something like a loop to validate
    private String grabUserInput(Scanner urlScanner){
        String url = urlScanner.next();
        boolean validatedUrl = checkURL(url);
        closeScannerConnection(urlScanner);
        if(validatedUrl){
            return url;
        }
        else {
            return defaultURL;
        }
    }
    private boolean checkURL(String stringUrl){
        try{
            URI uri = new URI(stringUrl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void closeScannerConnection(Scanner scanner){
        scanner.close();
    }

}
