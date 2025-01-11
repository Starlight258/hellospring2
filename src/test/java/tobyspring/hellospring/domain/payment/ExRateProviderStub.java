package tobyspring.hellospring.domain.payment;

import java.math.BigDecimal;

public class ExRateProviderStub implements ExRateProvider {

    private BigDecimal exRate;

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(final BigDecimal exRate) {
        this.exRate = exRate;
    }

    public ExRateProviderStub(final BigDecimal exRate) {
        this.exRate = exRate;
    }

    @Override
    public BigDecimal getExRate(final String currency) {
        return exRate;
    }
}
