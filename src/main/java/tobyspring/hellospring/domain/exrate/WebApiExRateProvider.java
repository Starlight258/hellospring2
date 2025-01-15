package tobyspring.hellospring.domain.exrate;

import java.math.BigDecimal;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.domain.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";

    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(final ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(final String currency) {
        String url = URL + currency;
        return apiTemplate.getExRate(url); // 콜백 (실제 구현체)
    }
}
