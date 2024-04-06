package Final.Project.dodo.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class JobService {
    private final OrderService orderService;

    public JobService(OrderService orderService) {
        this.orderService = orderService;
    }


    @Scheduled(fixedRate = 30000) // 30000 миллисекунд = 30 секунд
    private void testSchedule(){
        orderService.checkNewOrders();
    }
}
