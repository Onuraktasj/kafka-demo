package aktasj.shipment_service.dto.event;

import aktasj.shipment_service.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentStatusChangedEvent {
    private Long shipmentId;
    private Status oldStatus;
    private Status newStatus;
    private Long timestamp;
    private String recipientEmail;

}
