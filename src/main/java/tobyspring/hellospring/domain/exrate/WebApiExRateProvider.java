package tobyspring.hellospring.domain.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.dto.ExRateData;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";
    private static final String KRW = "KRW";

    @Override
    public BigDecimal getExRate(final String currency) {
        String url = URL + currency;
        return runApiForExRate(url);
    }

    private BigDecimal runApiForExRate(final String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = new SimpleApiExecutor().execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal extractExRate(final String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get(KRW);
    }
}
