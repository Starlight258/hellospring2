package tobyspring.hellospring.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void 금액을_환율에_맞게_변환한다() {
        // Given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // When
        Payment payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000),
                LocalDateTime.now(clock));

        // Then
        Assertions.assertAll(
                () -> assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000)),
                () -> assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30))
        );
    }
}
