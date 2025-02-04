package aktasj.shipment_service.controller;

import aktasj.shipment_service.dto.request.ShipmentDto;
import aktasj.shipment_service.dto.request.ShipmentStatusUpdateRequest;
import aktasj.shipment_service.entity.Shipment;
import aktasj.shipment_service.enums.Status;
import aktasj.shipment_service.service.ShipmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/create-shipment")
    public void createShipment(@RequestBody ShipmentDto shipment) {
        shipmentService.createShipment(shipment);
    }

    @PutMapping("/update-status")
    public void updateStatus(@RequestBody ShipmentStatusUpdateRequest request) {
        shipmentService.updateShipmentStatus(request);
    }


}
