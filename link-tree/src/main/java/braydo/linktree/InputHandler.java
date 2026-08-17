package braydo.linktree;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Scanner;
import java.net.URI;
import org.apache.commons.validator.*;
import org.apache.commons.validator.routines.UrlValidator;



public class InputHandler {
    private static final String defaultResponse = "default";
    private static final String defaultURL = "https://en.wikipedia.org/";
    private Scanner scanner = new Scanner(System.in);

    // can throw return statement of null, must handle outside
    public String grabUserUrl(String userPrompt){
        System.out.print(userPrompt);
        return grabUserInput(scanner);
    }
    // you must handle INVALIDURLEXCEPTION on call.
    public String getURLDomain(String url) throws InvalidUrlException {
        try{
            return new URI(url).getHost();
        }
        catch (URISyntaxException | NullPointerException e ){
            throw new InvalidUrlException("URL typed didn't have a domain name", e);
        }
    }
    // currently defaults on typo, ideally I add something like a loop to validate
    private String grabUserInput(Scanner urlScanner){
        String url = urlScanner.next();
        boolean validatedUrl = checkURL(url);
        if(validatedUrl){
            return url;
        }
        else if(url.toLowerCase(Locale.ROOT).equals(defaultResponse)) {
            return defaultURL;
        }
        else {
            return null;
        }
    }
    private boolean checkURL(String stringUrl){
        UrlValidator validator = new UrlValidator();
        return validator.isValid(stringUrl);

    }
    public void closeScannerConnection(){
        scanner.close();
    }

}
