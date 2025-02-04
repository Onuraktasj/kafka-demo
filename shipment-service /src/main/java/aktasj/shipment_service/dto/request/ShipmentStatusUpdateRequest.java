package aktasj.shipment_service.dto.request;

import aktasj.shipment_service.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentStatusUpdateRequest {
    Long shipmentId;
    Status status;
}
