package com.storeapi.store.abcde.notifications;

import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements NotificationService{
    @Override
    public String send() {
        return "Inside SMS Notification Service.";
    }
}
