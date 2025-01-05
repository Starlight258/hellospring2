package tobyspring.hellospring.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import tobyspring.hellospring.Payment;

abstract public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
            throws IOException {
        BigDecimal exRate = getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    abstract BigDecimal getExRate(final String currency) throws IOException;
}
