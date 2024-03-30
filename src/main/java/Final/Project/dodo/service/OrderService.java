package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.request.create.OrderCreateRequest;
import Final.Project.dodo.model.request.update.OrderUpdateRequest;


public interface OrderService extends BaseService<OrderDto> {
    OrderDto create(OrderCreateRequest request);

    OrderDto update(OrderUpdateRequest request);

    Boolean delete(Long id);
}
