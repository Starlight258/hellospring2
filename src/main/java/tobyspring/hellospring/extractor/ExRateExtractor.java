package tobyspring.hellospring.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extractExRate(final String response) throws JsonProcessingException;
}
