package tobyspring.hellospring.domain.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.extractor.ErApiExRateExtractor;
import tobyspring.hellospring.extractor.ExRateExtractor;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";

    @Override
    public BigDecimal getExRate(final String currency) {
        String url = URL + currency;
        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor()); // 콜백 (실제 구현체)
    }

    // 템플릿
    private BigDecimal runApiForExRate(final String url, final ApiExecutor apiExecutor,
                                       final ExRateExtractor exRateExtractor) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
