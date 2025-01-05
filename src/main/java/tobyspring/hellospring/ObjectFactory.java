package tobyspring.hellospring;

import tobyspring.hellospring.provider.ExRateProvider;
import tobyspring.hellospring.provider.SimpleExRateProvider;
import tobyspring.hellospring.service.PaymentService;

public class ObjectFactory {

    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    public ExRateProvider exRateProvider() {
        return new SimpleExRateProvider();
    }
}
