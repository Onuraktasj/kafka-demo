package aktasj.notification_service.dto.event;

import aktasj.notification_service.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentStatusChangedEvent {
    private Long shipmentId;
    private ShipmentStatus oldStatus;
    private ShipmentStatus newStatus;
    private String recipientEmail;
}
