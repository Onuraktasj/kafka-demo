package aktasj.notification_service.service;

import aktasj.notification_service.dto.event.ShipmentStatusChangedEvent;
import aktasj.notification_service.entity.Notification;

import java.util.List;

public interface NotificationService {
    void handleShipmentEvent(ShipmentStatusChangedEvent shipmentEvent);

}
