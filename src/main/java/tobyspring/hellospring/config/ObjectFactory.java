package tobyspring.hellospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.Client;
import tobyspring.hellospring.domain.provider.ExRateProvider;
import tobyspring.hellospring.domain.provider.SimpleExRateProvider;
import tobyspring.hellospring.service.PaymentService;

@Configuration
@ComponentScan(basePackageClasses = Client.class)  // 메인 애플리케이션 클래스 기준
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }


    @Bean
    public ExRateProvider exRateProvider() {
        return new SimpleExRateProvider();
    }
}
