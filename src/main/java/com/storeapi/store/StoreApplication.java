package com.storeapi.store;
import com.storeapi.store.notifications.NotificationManager;
import com.storeapi.store.payments.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
//        OrderService orderService = context.getBean(OrderService.class);
//        System.out.println("Spring Boot Application Started");
//        orderService.placeOrder();

//        NotificationManager notificationManager = context.getBean(NotificationManager.class);
//        System.out.println(notificationManager.sendNotification());


//        System.out.print("Enter amount : ");
//        var orderService1 = new OrderService();
//        orderService1.setPaymentService(new PayPalPaymentService());
//        orderService1.placeOrder();
//
//        System.out.print("Enter amount : ");
//        var orderService2 = new OrderService();
//        orderService2.setPaymentService(new StripePaymentService());
//        orderService2.placeOrder();
    }
}
