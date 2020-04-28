import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindLink {

    // Pattern for recognizing a URL, based off RFC 3986
    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


    //The link to access the PDF needs to be changed otherwise we cannot access it
    public String isolateGoodLink(String completeLink) {
        return completeLink.replace("book", "content/pdf");
    }

    public List<String> findURLsInText(String givenUrl) throws IOException {
        System.out.println("Opening connection to first URL");
        URL firstUrl = new URL(givenUrl);
        HttpURLConnection connection = (HttpURLConnection) firstUrl.openConnection();
        //If there is a redirection, we want to have the URL of the last page that appears
        connection.setReadTimeout(3000);
        String newUrlStg = connection.getHeaderField("Location");
        URL url = new URL(newUrlStg);

        InputStream in = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String inputLine;
        String allText = "";
        List <String > listOfURL = null ;
        while ((inputLine = br.readLine()) != null) {
            System.out.println("Reading the URL and converting in HTML text");
            allText = br.lines().collect(Collectors.joining());
        }
        System.out.println(allText);
        List<String> urlFound = this.findUrlInText(allText);
        return urlFound;
    }


    private List<String> findUrlInText(String text) {
        List<String> listOfURL = new ArrayList();
        Matcher matcher = urlPattern.matcher(text);
        while (matcher.find())
        {
            listOfURL.add(text.substring(matcher.start(1),
                    matcher.end(0)));
        }
        return listOfURL;
    }

}
