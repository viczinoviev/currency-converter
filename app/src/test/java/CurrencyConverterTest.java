import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class CurrencyConverterTest {
    Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);

    @Test
    public void TestCurrencyConverter() {
        BigDecimal fromAmount = BigDecimal.TEN;
        String fromCurrency = "EUR";
        String toCurrency = "USD";

        CurrencyConverter converter = new CurrencyConverter();

        BigDecimal result = converter.convert(fromAmount, fromCurrency, toCurrency);
        assertTrue(result.compareTo(BigDecimal.ZERO) >= 0);
        logger.info("{} {} equals {} {}", fromAmount, fromCurrency, result, toCurrency);
    }

}
