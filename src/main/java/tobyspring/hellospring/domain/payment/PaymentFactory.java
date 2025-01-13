package tobyspring.hellospring.domain.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentFactory(final ExRateProvider exRateProvider, final Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment createPrepared(final Long orderId, final String currency, final BigDecimal foreignCurrencyAmount) {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        LocalDateTime now = LocalDateTime.now(clock);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = now.plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}
