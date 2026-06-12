package com.storeapi.store.notifications;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService{
    @Override
    public String send() {
        return "Inside Email Notification Service";
    }
}
