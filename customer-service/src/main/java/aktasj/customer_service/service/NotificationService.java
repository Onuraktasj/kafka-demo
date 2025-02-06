package aktasj.customer_service.service;

import aktasj.customer_service.dto.event.NotificationEvent;

public interface NotificationService {

    String findShipmentById(Long id);
    void createShipmentNotification(NotificationEvent notificationEvent);
}
