package com.storeapi.store.abcde.payments;

import org.springframework.stereotype.Service;

@Service("paypal")
public class PayPalPaymentService implements PaymentService {
    public void processPayment(double amount) {
        System.out.println("PAYPAL");
        System.out.println("PayPal payment amount : " + amount);
    }
}
