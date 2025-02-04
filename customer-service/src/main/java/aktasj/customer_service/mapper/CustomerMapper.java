package aktasj.customer_service.mapper;

import aktasj.customer_service.dto.request.CreateCustomerRequest;
import aktasj.customer_service.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomer(CreateCustomerRequest request) {
        return Customer.builder()
                .shipmentId(request.getShipmentId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }

    public static CreateCustomerRequest toCustomerDto(Customer customer) {
        return CreateCustomerRequest.builder()
                .shipmentId(customer.getShipmentId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
