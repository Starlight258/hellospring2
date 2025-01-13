package tobyspring.hellospring.domain.exrate;

import java.math.BigDecimal;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.executor.SimpleApiExecutor;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.api.extractor.ErApiExRateExtractor;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static final String URL = "https://open.er-api.com/v6/latest/";

    private final ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(final String currency) {
        String url = URL + currency;
        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor()); // 콜백 (실제 구현체)
    }
}
