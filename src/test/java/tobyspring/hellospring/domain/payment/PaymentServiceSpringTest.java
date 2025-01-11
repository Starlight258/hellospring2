package tobyspring.hellospring.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.config.TestPaymentConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    Clock clock;

    @Test
    @DisplayName("변환된 금액을 테스트한다.")
    void prepare() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertAll(
                () -> assertThat(payment.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000)),
                () -> assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000)),
                () -> assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now()),
                () -> assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30))
        );
    }

    @Test
    @DisplayName("유효시간은 현재 시간의 30분 뒤이다.")
    void 유효시간은_현재_시간의_30분_뒤이다() throws IOException {
        // Given
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // When
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        // Then
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}
