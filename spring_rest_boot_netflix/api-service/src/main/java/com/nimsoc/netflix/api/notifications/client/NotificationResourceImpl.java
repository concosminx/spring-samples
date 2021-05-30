package com.nimsoc.netflix.api.notifications.client;

import com.nimsoc.netflix.api.notifications.model.Notification;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixObservable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationResourceImpl implements NotificationResource {
    
    @Override
    public List<Notification> findAll() {
        return new ArrayList<>();
    }
}
