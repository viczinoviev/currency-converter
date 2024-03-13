import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.logging.Logger;

public class RateFetcherTest {

    Logger logger = Logger.getLogger(RateFetcher.class.getName());
    @Test
    public void FetchRateContentIsNotEmpty() {
        RateFetcher rateFetcher = new RateFetcher();
        JSONObject result = rateFetcher.getJson("EUR", "USD");
        Assertions.assertNotNull(result);
        logger.info("got: " + result.toString());
    }
}
