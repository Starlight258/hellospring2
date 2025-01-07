package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.provider.ExRateProvider;
import tobyspring.hellospring.provider.SimpleExRateProvider;
import tobyspring.hellospring.service.PaymentService;

@Configuration
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
