package tobyspring.hellospring.domain.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;

public class PaymentService {

    private final PaymentFactory paymentFactory;

    public PaymentService(final PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
            throws IOException {
        return paymentFactory.createPrepared(orderId, currency, foreignCurrencyAmount);
    }
}
