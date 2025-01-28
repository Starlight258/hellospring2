package tobyspring.hellospring.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.domain.exrate.CachedExRateProvider;
import tobyspring.hellospring.domain.exrate.SimpleExRateProvider;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.domain.payment.PaymentFactory;
import tobyspring.hellospring.domain.payment.PaymentService;

@Configuration
public class TestPaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(paymentFactory());
    }

    @Bean
    public PaymentFactory paymentFactory() {
        return new PaymentFactory(cachedExRateProvider(), clock());
    }

    @Bean
    public CachedExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new SimpleExRateProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
