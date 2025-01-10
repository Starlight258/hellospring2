package tobyspring.hellospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.Client;
import tobyspring.hellospring.domain.exrate.CachedExRateProvider;
import tobyspring.hellospring.domain.exrate.ExRateProvider;
import tobyspring.hellospring.domain.exrate.WebApiExRateProvider;
import tobyspring.hellospring.domain.payment.PaymentService;

@Configuration
@ComponentScan(basePackageClasses = Client.class)  // 메인 애플리케이션 클래스 기준
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public CachedExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
