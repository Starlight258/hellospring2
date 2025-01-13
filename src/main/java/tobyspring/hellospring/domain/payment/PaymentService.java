package tobyspring.hellospring.domain.payment;

import java.math.BigDecimal;

public class PaymentService {

    private final PaymentFactory paymentFactory;

    public PaymentService(final PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        return paymentFactory.createPrepared(orderId, currency, foreignCurrencyAmount);
    }
}
