package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.request.RepeatOrderRequest;
import Final.Project.dodo.model.request.create.OrderCreateRequest;
import Final.Project.dodo.model.request.update.OrderUpdateRequest;
import Final.Project.dodo.model.response.OrderHistoryResponse;

import java.util.List;


public interface OrderService extends BaseService<OrderDto> {
    OrderDto create(String accessToken ,OrderCreateRequest request);

    OrderDto update(OrderUpdateRequest request);

    Boolean delete(Long id);

    List<OrderHistoryResponse> getAllByUserId(String token, int pageNum, int limit);

    OrderDto repeatOrder(String token, RepeatOrderRequest request);

    void checkNewOrders();
}
