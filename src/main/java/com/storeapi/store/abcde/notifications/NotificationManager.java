package com.storeapi.store.abcde.notifications;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class NotificationManager {
    private final NotificationService notificationService;
    public NotificationManager(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public String sendNotification(){
       return notificationService.send();
    }
}
