package aktasj.customer_service.controller;

import aktasj.customer_service.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final NotificationService notificationService;

    public CustomerController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @GetMapping("/getNotification/{id}")
    public String getNotification(@PathVariable("id") Long id) {
      return   notificationService.findShipmentById(id);
    }

}
