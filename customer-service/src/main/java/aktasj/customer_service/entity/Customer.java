package aktasj.customer_service.entity;

import aktasj.customer_service.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long shipmentId;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
}
