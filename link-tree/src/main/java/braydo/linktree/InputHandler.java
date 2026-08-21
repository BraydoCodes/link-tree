package braydo.linktree;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Scanner;
import java.net.URI;
import org.apache.commons.validator.*;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * A InputHandler is created to handle user i/o for urls
 */
public class InputHandler {
    private static final String defaultResponse = "default";
    private static final String defaultURL = "https://en.wikipedia.org/";
    private static final String urlPrompt = "Please enter a url: ";
    private final String quitPrompt = "quit";
    private String currentHostName = "";
    private Scanner scanner;

    /**
     * will continue to prompt for a valid answer (in this case a url) or until a quit request has been given
     * @return a string that is either the valid response or 'quit'
     */
    public String loopUntilValidResponse(){
        scanner = new Scanner(System.in);

        boolean isValidURL = false;
        String hostName = null;
        String test_url = "";

        while (!isValidURL && hostName == null) {
            test_url = grabUserUrl(urlPrompt);
            if (test_url != null) {
                if (test_url.equals(quitPrompt)) {
                    System.out.println("Exiting...");
                    break;
                }

                try {
                    hostName = getURLDomain(test_url);
                    isValidURL = true;
                } catch (InvalidUrlException e) {
                    System.out.println("Error Occurred for URL: " + e.getMessage());
                }
            } else {
                System.out.println("Please enter a valid url string");
            }
        }
        currentHostName = hostName;
        return test_url; // must handle the case of a quit use getQuitPrompt()
    }

    public String getQuitPrompt() {
        return quitPrompt;
    }
    public String getCurrentHostName(){
        return  currentHostName;
    }

    /** can throw return statement of null, must handle outside
     * @param userPrompt
     * @return the user given url after validation
     */
    public String grabUserUrl(String userPrompt){
        System.out.print(userPrompt);
        return grabUserInput(scanner);
    }

    /** converts a url into host format
     * @param url a url that requires host information
     * @return the host of a valid url
     * @throws InvalidUrlException you must handle INVALIDURLEXCEPTION on call.
     */
    public String getURLDomain(String url) throws InvalidUrlException {
        try{
            return new URI(url).getHost();
        }
        catch (URISyntaxException | NullPointerException e ){
            throw new InvalidUrlException("URL typed didn't have a domain name", e);
        }
    }

    /**
     * validates user url input and handles quits, has a special case for default too.
     * @param urlScanner, the scanner object (must be not closed)
     * @return the url string, quit or null |HANDLE|
     */
    private String grabUserInput(Scanner urlScanner){
        String url = grabScannerNextLine(urlScanner);
        boolean validatedUrl = checkURL(url);
        if(validatedUrl){
            return url;
        }
        else if(url.toLowerCase(Locale.ROOT).equals(defaultResponse)) {
            return defaultURL;
        }
        else if(url.equals(quitPrompt)){
            return quitPrompt;
        }
        else {
            return null;
        }
    }
    private String grabScannerNextLine(Scanner scanner){
        return scanner.next();
    }

    private boolean checkURL(String stringUrl){
        UrlValidator validator = new UrlValidator();
        return validator.isValid(stringUrl);
    }
    public void closeScannerConnection(){
        scanner.close();
    }

}
