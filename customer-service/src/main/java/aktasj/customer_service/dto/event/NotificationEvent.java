package aktasj.customer_service.dto.event;

import aktasj.customer_service.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEvent {
    private Long shipmentId;
    private String message;
    private ShipmentStatus status;
}
