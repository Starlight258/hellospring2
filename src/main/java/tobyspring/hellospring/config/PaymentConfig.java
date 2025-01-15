package tobyspring.hellospring.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring.Client;
import tobyspring.hellospring.domain.exrate.CachedExRateProvider;
import tobyspring.hellospring.domain.exrate.RestTemplateExRateProvider;
import tobyspring.hellospring.domain.payment.ExRateProvider;
import tobyspring.hellospring.domain.payment.PaymentFactory;
import tobyspring.hellospring.domain.payment.PaymentService;

@Configuration
@ComponentScan(basePackageClasses = Client.class)  // 메인 애플리케이션 클래스 기준
public class PaymentConfig {

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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
