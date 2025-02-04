package aktasj.customer_service.mapper;

import aktasj.customer_service.dto.event.NotificationEvent;
import aktasj.customer_service.entity.Notification;

public class NotificationMapper {

    public static Notification toNotification(NotificationEvent notificationEvent){
        return Notification.builder()
                .shipmentId(notificationEvent.getShipmentId())
                .status(notificationEvent.getStatus())
                .message(notificationEvent.getMessage())
                .build();
    }

    public static NotificationEvent toNotificationEvent(Notification notification){
        return NotificationEvent.builder()
                .shipmentId(notification.getShipmentId())
                .status(notification.getStatus())
                .message(notification.getMessage())
                .build();
    }
}
