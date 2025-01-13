package tobyspring.hellospring.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import tobyspring.hellospring.dto.ExRateData;

public class ErApiExRateExtractor implements ExRateExtractor {

    private static final String KRW = "KRW";

    @Override
    public BigDecimal extractExRate(final String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get(KRW);
    }
}
