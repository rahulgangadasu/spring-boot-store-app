package com.storeapi.store.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService {
    @Value("${stripe.apiUrl}")
    public String apiUrl;
    @Value("${stripe.timeout}")
    public int timeout;
    @Value("${stripe.enabled}")
    public boolean enabled;
    @Value("${stripe.currencies}")
    public List<String> currencies;

    public void processPayment(double amount){
        System.out.println("STRIPE");
        System.out.println("Stripe payment amount : " + amount);
        System.out.println(apiUrl + " : " +timeout + " : " + enabled + " : " + currencies);
    }
}
