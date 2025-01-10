package tobyspring.hellospring.domain.exrate;

import java.math.BigDecimal;
import tobyspring.hellospring.domain.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {

    private static final String USD = "USD";
    private static final int CURRENCY = 1000;

    @Override
    public BigDecimal getExRate(final String currency) {
        if (currency.equals(USD)) {
            return BigDecimal.valueOf(CURRENCY);
        }
        throw new IllegalArgumentException("지원되지 않는 통화입니다.");
    }
}
