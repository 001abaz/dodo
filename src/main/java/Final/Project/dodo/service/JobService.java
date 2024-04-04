package Final.Project.dodo.service;

import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.enums.OrderStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class JobService {
    private final OrderService orderService;

    public JobService(OrderService orderService) {
        this.orderService = orderService;
    }


    @Scheduled(fixedRate = 30000) // 30000 миллисекунд = 30 секунд
    private void testSchedule(){
        List<OrderDto> orderList = orderService.findAll();
        for (OrderDto dto: orderList){
            if ((dto.getOrderStatus().equals(OrderStatus.NEW)
                   && dto.getUpdateDate().plusMinutes(30).isEqual(dto.getOrderDate())
                    || dto.getUpdateDate().plusMinutes(30).isBefore(dto.getOrderDate()))) {
                dto.setOrderStatus(OrderStatus.PREPARING);
                orderService.update(dto);
            }
        }
    }
}
