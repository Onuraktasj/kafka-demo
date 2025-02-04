package aktasj.notification_service.ımpl;

import aktasj.notification_service.dto.event.NotificationEvent;
import aktasj.notification_service.dto.event.ShipmentStatusChangedEvent;
import aktasj.notification_service.service.NotificationService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String NOTIFICATION_TOPIC = "notification-events";

    @Override
    @KafkaListener(topics = "shipment-status-changes", groupId = "notification-group")
    public void handleShipmentEvent(ShipmentStatusChangedEvent shipmentEvent) {
        try {
            validateShipmentEvent(shipmentEvent);
            NotificationEvent notificationEvent = createNotificationEvent(shipmentEvent);
            log.info("Event yakalandı ! --> " + notificationEvent);
        } catch (Exception e) {
            log.error("Shipment event işlenirken hata oluştu. Event: {}", shipmentEvent, e);
        }
    }

    private void validateShipmentEvent(ShipmentStatusChangedEvent shipmentEvent) {
        if (shipmentEvent == null) {
            throw new IllegalArgumentException("Shipment event null olamaz");
        }
        if (shipmentEvent.getNewStatus() == null) {
            throw new IllegalArgumentException("Shipment status null olamaz");
        }
        if (StringUtils.isEmpty(shipmentEvent.getShipmentId().toString())) {
            throw new IllegalArgumentException("Shipment ID null veya boş olamaz");
        }
    }

    private NotificationEvent createNotificationEvent(ShipmentStatusChangedEvent shipmentEvent) {
        NotificationEvent notificationEvent = switch (shipmentEvent.getNewStatus()) {
            case IN_TRANSIT -> NotificationEvent.builder()
                    .shipmentId(shipmentEvent.getShipmentId())
                    .message("YOLDA")
                    .status(shipmentEvent.getNewStatus())
                    .build();
            case DELIVERED -> NotificationEvent.builder()
                    .shipmentId(shipmentEvent.getShipmentId())
                    .message("TESLİM EDİLDİ")
                    .status(shipmentEvent.getNewStatus())
                    .build();
            case CANCELLED -> NotificationEvent.builder()
                    .shipmentId(shipmentEvent.getShipmentId())
                    .message("BEKLEMEDE")
                    .status(shipmentEvent.getNewStatus())
                    .build();
            default -> throw new IllegalStateException("Beklenmeyen kargo durumu: " + shipmentEvent.getNewStatus());
        };
        try {
            kafkaTemplate.send(NOTIFICATION_TOPIC,
                            shipmentEvent.getShipmentId().toString(),
                            notificationEvent)
                    .whenComplete((result, ex) -> {
                        if (ex == null) {
                            log.info("Notification event başarıyla gönderildi. Event: {}", notificationEvent);
                        } else {
                            log.error("Notification event gönderilirken hata oluştu. Event: {}", notificationEvent, ex);
                        }
                    });
        } catch (Exception e) {
            log.error("Kafka'ya mesaj gönderilirken hata oluştu. Event: {}", notificationEvent, e);
            throw new RuntimeException("Event gönderme hatası", e);
        }

        return notificationEvent;
    }

}
