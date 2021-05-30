package com.nimsoc.netflix.api.notifications.service;

import com.nimsoc.netflix.api.notifications.client.NotificationResource;
import com.nimsoc.netflix.api.notifications.client.NotificationVersionResource;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.nimsoc.netflix.api.infrastructure.Collaborators.NOTIFICATIONS;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

  public static final String VERSION = "1.0.0";
  @InjectMocks
  private NotificationService notificationService;

  @Mock
  private EurekaClient discoveryClient;
  @Mock
  private NotificationResource notificationResource;
  @Mock
  private NotificationVersionResource notificationVersionResource;

  @Test
  public void notificationStatusCallsEurekaClient() {
    InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder()
            .setAppName(NOTIFICATIONS)
            .setStatus(InstanceInfo.InstanceStatus.STARTING)
            .build();
    when(discoveryClient.getNextServerFromEureka(NOTIFICATIONS, false))
            .thenReturn(instanceInfo);

    assertThat(notificationService.notificationsStatus())
            .isEqualTo(instanceInfo.getStatus());
  }

  @Test
  public void versionCallsRestTemplate() {
    when(notificationVersionResource.version())
            .thenReturn(VERSION);

    assertThat(notificationService.version())
            .isEqualTo(VERSION);
  }
}
