package com.storeapi.store.payments;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Lazy //for bean lazy initialization
//@Scope("prototype") //to change scope of bean, default is singleton
public class OrderService {

    private final PaymentService paymentService;
    public OrderService( PaymentService paymentService){
        this.paymentService = paymentService;
        System.out.println("Order Service created."); //Early/Eager Initialization.
    }
    public void placeOrder(){
        paymentService.processPayment(100);
    }

    @PostConstruct
    public void init(){
        System.out.println("Post Bean Construction");
    }
//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
}
