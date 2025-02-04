package aktasj.shipment_service.service;

import aktasj.shipment_service.dto.request.ShipmentDto;
import aktasj.shipment_service.dto.request.ShipmentStatusUpdateRequest;
import aktasj.shipment_service.entity.Shipment;
import aktasj.shipment_service.enums.Status;


public interface ShipmentService{

     void createShipment(ShipmentDto shipmentDto);
     void updateShipmentStatus(ShipmentStatusUpdateRequest request);


}
