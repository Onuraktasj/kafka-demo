package aktasj.customer_service.ımpl;

import aktasj.customer_service.dto.event.NotificationEvent;
import aktasj.customer_service.dto.request.CreateCustomerRequest;
import aktasj.customer_service.entity.Customer;
import aktasj.customer_service.entity.Notification;
import aktasj.customer_service.mapper.CustomerMapper;
import aktasj.customer_service.mapper.NotificationMapper;
import aktasj.customer_service.repository.CustomerRepository;
import aktasj.customer_service.repository.NotificationRepository;
import aktasj.customer_service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final NotificationRepository notificationRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CustomerServiceImpl(CustomerRepository customerRepository, NotificationRepository notificationRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.notificationRepository = notificationRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createCustomer(CreateCustomerRequest createCustomerRequest) {
        customerRepository.save(CustomerMapper.toCustomer(createCustomerRequest));
    }

    @Override
    public String findShipmentById(Long shipmentId) {
        return "";
    }

    @Override
    @KafkaListener(topics = "notification-events",groupId = "notification-group")
    public void createNotification(NotificationEvent notificationEvent) {
        try {
            notificationRepository.save(NotificationMapper.toNotification(notificationEvent));
            log.info("Event yakalandı ! --> " + notificationEvent);

        }catch (Exception e){
            log.error("Shipment event işlenirken hata oluştu. Event: {}", notificationEvent, e);
        }
    }


    @Override
    public NotificationEvent findNotificationById(Long notificationId) {
        return null;
    }


}
