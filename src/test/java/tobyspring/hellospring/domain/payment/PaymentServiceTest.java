package tobyspring.hellospring.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PaymentServiceTest {

    private Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @ParameterizedTest
    @CsvSource({
            "500, 5_000",
            "1000, 10_000"
    })
    @DisplayName("변환된 금액을 테스트한다.")
    void prepare(final BigDecimal exRate, final BigDecimal convertedAmount) throws IOException {
        testAmount(exRate, convertedAmount, this.clock);
    }

    private void testAmount(final BigDecimal exRate, final BigDecimal convertedAmount, final Clock clock) throws IOException {
        PaymentFactory paymentFactory = new PaymentFactory(new ExRateProviderStub(exRate), clock);
        PaymentService paymentService = new PaymentService(paymentFactory);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertAll(
                () -> assertThat(payment.getExRate()).isEqualByComparingTo(exRate),
                () -> assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount)
        );
    }

    @Test
    @DisplayName("유효시간은 현재 시간의 30분 뒤이다.")
    void 유효시간은_현재_시간의_30분_뒤이다() throws IOException {
        // Given
        PaymentFactory paymentFactory = new PaymentFactory(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        PaymentService paymentService = new PaymentService(paymentFactory);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // When
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        // Then
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}
