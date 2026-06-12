package com.storeapi.store.notifications;

import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private final NotificationService notificationService;
    public NotificationManager(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public String sendNotification(){
       return notificationService.send();
    }
}
