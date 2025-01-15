package tobyspring.hellospring.domain.exrate;

import java.math.BigDecimal;
import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.dto.ExRateData;

public class RestTemplateExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";

    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(final String currency) {
        String url = URL + currency;

        return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
    }
}
