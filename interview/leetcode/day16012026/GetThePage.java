import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.net.http.*;
import java.net.URI;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.*;

public class GetThePage {
  private static JsonObject getResponseFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connectionHttp = (HttpURLConnection) url.openConnection();
        connectionHttp.setRequestMethod("GET");
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connectionHttp.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line = in.readLine();
            while (line != null) {
                content.append(line);
                line = in.readLine();
            }
            
            String finalStr = content.toString();
            return JsonParser.parseString(finalStr).getAsJsonObject();
        } finally {
            connectionHttp.disconnect();
        }
        
        
    }

    public static List<String> maximumTransfer(String name, String city) throws IOException {
        String baseUrl = " https://jsonmock.hackerrank.com/api/transactions";
        int curr = 1;
        int total = 1;
        List<String> result = new ArrayList<>(2);
        result.se
        while (curr<=total) {
           if (curr == 1) {
            // get the total actually by calling first baseUrl
            JsonObject responseObj = getResponseFromUrl(baseUrl);
            total = responseObj.get("total_pages").getAsInt();

            System.out.println("totalPage", total);
           } else {
            JsonObject response


           }
        }        
        
        return new ArrayList<>();

    }
}
