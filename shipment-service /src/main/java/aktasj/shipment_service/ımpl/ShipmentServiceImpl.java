package aktasj.shipment_service.ımpl;


import aktasj.shipment_service.dto.event.ShipmentStatusChangedEvent;
import aktasj.shipment_service.dto.request.ShipmentDto;
import aktasj.shipment_service.dto.request.ShipmentStatusUpdateRequest;
import aktasj.shipment_service.entity.Shipment;
import aktasj.shipment_service.enums.Status;
import aktasj.shipment_service.mapper.ShipmentMapper;
import aktasj.shipment_service.repository.ShipmentRepository;
import aktasj.shipment_service.service.ShipmentService;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC_SHIPMENT_STATUS = "shipment-status-changes";

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.shipmentRepository = shipmentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createShipment(ShipmentDto shipment) {
        shipmentRepository.save(ShipmentMapper.toShipment(shipment));
    }

    @Override
    @Transactional
    public void updateShipmentStatus(ShipmentStatusUpdateRequest request) {
        Shipment shipment = shipmentRepository.findById(request.getShipmentId()).orElseThrow(() -> new IllegalArgumentException("Shipment not found"));

        Status oldStatus = shipment.getStatus();
        shipment.setStatus(request.getStatus());
        shipmentRepository.save(shipment);

        ShipmentStatusChangedEvent event = new ShipmentStatusChangedEvent(
                shipment.getId(),
                oldStatus,
                request.getStatus(),
                System.currentTimeMillis(),
                "abc"
        );

        kafkaTemplate.send(TOPIC_SHIPMENT_STATUS,shipment.getId().toString(),event);
    }
}
