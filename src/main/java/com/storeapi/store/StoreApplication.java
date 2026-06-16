package com.storeapi.store;

import com.storeapi.store.repositories.ProfileRepository;
import com.storeapi.store.repositories.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var repository = context.getBean(ProfileRepository.class);
        var found = repository.findById(1L).orElseThrow();
        System.out.println(found.getBio());
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.findByEmail("rahulgangadasu@icloud.com");
    }
}

//        User user = User.builder()
//                        .name("Rahul")
//                        .email("rahulgangadasu@icloud.com")
//                        .password("pandu")
//                        .build();
//        var address = Address.builder()
//                             .street("2803 W Royal Ln")
//                             .city("Irving")
//                             .state("TX")
//                             .zip("75063")
//                             .build();
//        user.addAddress(address);
//        user.addTag("tag1");
//        var profile = Profile.builder()
//                .bio("Software Developer")
//                .build();
//        user.setProfile(profile);
//        profile.setUser(user);
//        System.out.println(user.getAddresses());
//        System.out.println(user.getProfile());
//        System.out.println(user.getTags());


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