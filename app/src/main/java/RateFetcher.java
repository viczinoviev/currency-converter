import io.github.cdimascio.dotenv.Dotenv;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
public class RateFetcher {
    Dotenv dotenv = Dotenv.load();
    private final String ACCESS_KEY = dotenv.get("API_KEY");
    private final String BASE_URI = "http://api.exchangeratesapi.io/v1/latest?access_key=";

    private String getURI(String from, String to) {
        return BASE_URI + ACCESS_KEY + "&base=" + from + "&symbols=" + to;
    }


    public JSONObject getJson(String from, String to) {
        JSONObject json = null;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(
                    HttpRequest
                            .newBuilder(new URI(getURI(from, to)))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            json = new JSONObject(response.body());

        } catch (URISyntaxException e) {
            System.out.println("Something went wrong");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    public BigDecimal getRate(String from, String to) {
        JSONObject json = getJson(from, to);
        return json.getJSONObject("rates").getBigDecimal(to);
    }

}
