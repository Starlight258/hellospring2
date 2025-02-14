package tobyspring.hellospring.domain.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {

    private final Long orderId;
    private final String currency;
    private final BigDecimal foreignCurrencyAmount;
    private final BigDecimal exRate;
    private final BigDecimal convertedAmount;
    private final LocalDateTime validUntil;

    public Payment(final Long orderId, final String currency, final BigDecimal foreignCurrencyAmount,
                   final BigDecimal exRate,
                   final BigDecimal convertedAmount, final LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public boolean isValid(final Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
