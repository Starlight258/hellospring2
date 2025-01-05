package tobyspring.hellospring.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import tobyspring.hellospring.Payment;
import tobyspring.hellospring.provider.ExRateProvider;

public class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService(final ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
            throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

}
