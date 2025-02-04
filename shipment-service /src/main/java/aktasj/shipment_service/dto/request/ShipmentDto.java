package aktasj.shipment_service.dto.request;

import aktasj.shipment_service.enums.Status;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDto {

    private Status status;
    private String customerEmail;
    private Long courierId;
}
