package com.storeapi.store;

import com.storeapi.store.entities.Address;
import com.storeapi.store.entities.Profile;
import com.storeapi.store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        //SpringApplication.run(StoreApplication.class, args);
        User user = User.builder()
                        .name("Rahul")
                        .email("rahulgangadasu@icloud.com")
                        .password("pandu")
                        .build();
        var address = Address.builder()
                             .street("2803 W Royal Ln")
                             .city("Irving")
                             .state("TX")
                             .zip("75063")
                             .build();
        user.addAddress(address);
        user.addTag("tag1");
        var profile = Profile.builder()
                .bio("Software Developer")
                .build();
        user.setProfile(profile);
        profile.setUser(user);
        System.out.println(user.getAddresses());
        System.out.println(user.getProfile());
        System.out.println(user.getTags());
    }
}


// ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
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