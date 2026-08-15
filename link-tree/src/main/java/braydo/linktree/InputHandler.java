package braydo.linktree;
import java.util.Scanner;
import java.net.URI;
import org.apache.commons.validator.*;
import org.apache.commons.validator.routines.UrlValidator;



public class InputHandler {
    private static final String defaultURL = "https://en.wikipedia.org/";
    public String grabUserUrl(String userPrompt){
        System.out.print(userPrompt);
        Scanner scanner = new Scanner(System.in);
        return grabUserInput(scanner);
    }
    public String getURLDomain(String url){
        try{
            return new URI(url).getHost();
        }
        catch (Exception e){
            return "";
        }
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
        UrlValidator validator = new UrlValidator();
        return validator.isValid(stringUrl);

    }
    private void closeScannerConnection(Scanner scanner){
        scanner.close();
    }

}
