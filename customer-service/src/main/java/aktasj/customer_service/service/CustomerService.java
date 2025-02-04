package aktasj.customer_service.service;

import aktasj.customer_service.dto.event.NotificationEvent;
import aktasj.customer_service.dto.request.CreateCustomerRequest;
import aktasj.customer_service.entity.Customer;

public interface CustomerService {

    void createCustomer(CreateCustomerRequest createCustomerRequest);

    String findShipmentById(Long shipmentId);
    void createNotification(NotificationEvent notification);
    NotificationEvent findNotificationById(Long notificationId);
}
