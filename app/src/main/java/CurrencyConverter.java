import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyConverter {

    Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);
    /**
     *  Converts {fromAmount} of {fromCurrency} into {toCurrency}
     *
     * @param fromAmount Value of currency to convert from
     * @param fromCurrency Currency to convert from
     * @param toCurrency Currency to convert to
     * @return The Value of {fromAmount} in {fromCurrency} converted to {toCurrency}
     */
    public BigDecimal convert(BigDecimal fromAmount, String fromCurrency, String toCurrency) {
        assert fromAmount.compareTo(BigDecimal.ZERO) >= 0 : "Original amount cannot be negative.";

        logger.info("Converting {} {} to {}", fromAmount, fromCurrency, toCurrency);

        RateFetcher rateFetcher = new RateFetcher();
        BigDecimal rate = rateFetcher.getRate(fromCurrency, toCurrency);
        logger.info("Rate: {}", rate);

        return fromAmount.multiply(rate);
    }
}