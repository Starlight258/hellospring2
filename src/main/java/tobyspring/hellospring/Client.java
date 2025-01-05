package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import tobyspring.hellospring.service.PaymentService;
import tobyspring.hellospring.service.SimpleExRatePaymentService;

public class Client {

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
