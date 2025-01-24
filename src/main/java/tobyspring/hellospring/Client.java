package tobyspring.hellospring;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.config.PaymentConfig;
import tobyspring.hellospring.domain.payment.Payment;
import tobyspring.hellospring.domain.payment.PaymentService;

@SpringBootApplication
public class Client {

    public static void main(String[] args) throws InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment1 : " + payment);
        System.out.println("---------");

        TimeUnit.SECONDS.sleep(1);
        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment2 : " + payment2);
        System.out.println("---------");

        TimeUnit.SECONDS.sleep(2);
        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment3 : " + payment3);
        System.out.println("---------");
    }
}
