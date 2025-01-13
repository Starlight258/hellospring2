package tobyspring.hellospring.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void 금액을_환율에_맞게_변환한다() {
        // Given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        PaymentFactory paymentFactory = new PaymentFactory(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        Payment payment = paymentFactory.createPrepared(1L, "USD", BigDecimal.TEN);

        // When & Then
        Assertions.assertAll(
                () -> assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000)),
                () -> assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30))
        );
    }

    @Test
    @DisplayName("유효시간은 현재 시간의 30분까지이다.")
    void 유효시간은_현재_시간의_30분까지이다() {
        // Given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        PaymentFactory paymentFactory = new PaymentFactory(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        Payment payment = paymentFactory.createPrepared(1L, "USD", BigDecimal.TEN);

        // When & Then
        Assertions.assertAll(
                () -> assertThat(payment.isValid(clock)).isTrue(),
                () -> assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse()
        );
    }
}
