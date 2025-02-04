package aktasj.customer_service.dto.request;

import aktasj.customer_service.enums.ShipmentStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    private Long shipmentId;
    private ShipmentStatus shipmentStatus;
}
