package tobyspring.hellospring.config;

import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.domain.payment.ExRateProviderStub;
import tobyspring.hellospring.domain.payment.PaymentService;

@Configuration
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
