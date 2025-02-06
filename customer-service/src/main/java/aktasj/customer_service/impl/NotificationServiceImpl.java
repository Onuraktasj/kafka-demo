package aktasj.customer_service.impl;

import aktasj.customer_service.dto.event.NotificationEvent;
import aktasj.customer_service.entity.Notification;
import aktasj.customer_service.enums.ShipmentStatus;
import aktasj.customer_service.mapper.NotificationMapper;
import aktasj.customer_service.repository.NotificationRepository;
import aktasj.customer_service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private static final String NOTIFICATION_TOPIC = "notification-events";


    public NotificationServiceImpl(NotificationRepository notificationRepository) {
      this.notificationRepository = notificationRepository;
  }


    @Override
    public String findShipmentById(Long id) {
    Notification notification = notificationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Shipment not found"));
    ShipmentStatus status = notification.getStatus();
            return status.toString();
  }

    @Override
    @KafkaListener(topics = NOTIFICATION_TOPIC, groupId = "customer-group")
    public void createShipmentNotification(NotificationEvent notificationEvent) {
        notificationRepository.save(NotificationMapper.toNotification(notificationEvent));
        log.info("Event yakalandı ! --> " + notificationEvent);
    }
}
