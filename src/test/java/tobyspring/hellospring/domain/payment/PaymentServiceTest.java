package tobyspring.hellospring.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import tobyspring.hellospring.domain.exrate.WebApiExRateProvider;

class PaymentServiceTest {

    @Test
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertAll(
                () -> assertThat(payment.getExRate()).isNotNull(),
                () -> assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now()),
                () -> assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30))
        );
    }
}
