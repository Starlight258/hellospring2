package tobyspring.hellospring.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PaymentServiceTest {

    @ParameterizedTest
    @CsvSource({
            "500, 5_000",
            "1000, 10_000"
    })
    @DisplayName("변환된 금액을 테스트한다.")
    void prepare(final BigDecimal exRate, final BigDecimal convertedAmount) throws IOException {
        testAmount(exRate, convertedAmount);
    }

    private void testAmount(final BigDecimal exRate, final BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertAll(
                () -> assertThat(payment.getExRate()).isEqualByComparingTo(exRate),
                () -> assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount),
                () -> assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now()),
                () -> assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30))
        );
    }
}
