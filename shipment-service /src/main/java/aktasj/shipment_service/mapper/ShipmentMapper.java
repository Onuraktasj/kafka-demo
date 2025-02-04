package aktasj.shipment_service.mapper;

import aktasj.shipment_service.dto.request.ShipmentDto;
import aktasj.shipment_service.entity.Shipment;

public class ShipmentMapper {

    public static Shipment toShipment(ShipmentDto shipmentDto) {
        return Shipment.builder()
                .customerEmail(shipmentDto.getCustomerEmail())
                .status(shipmentDto.getStatus())
                .courierId(shipmentDto.getCourierId())
                .build();
    }

    public static ShipmentDto toShipmentDto(Shipment shipment) {
        return ShipmentDto.builder()
                .customerEmail(shipment.getCustomerEmail())
                .status(shipment.getStatus())
                .courierId(shipment.getCourierId())
                .build();
    }
}
