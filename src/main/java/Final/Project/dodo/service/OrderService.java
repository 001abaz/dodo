package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.request.RepeatOrderRequest;
import Final.Project.dodo.model.request.create.OrderCreateRequest;
import Final.Project.dodo.model.request.update.OrderUpdateRequest;
import Final.Project.dodo.model.response.OrderHistoryResponse;

import java.util.List;


public interface OrderService extends BaseService<OrderDto> {
    String create(String accessToken ,OrderCreateRequest request, Integer languageOrdinal);

    OrderDto update(OrderUpdateRequest request, Integer languageOrdinal);

    Boolean delete(Long id, Integer languageOrdinal);

    List<OrderHistoryResponse> getAllByUserId(String token, int pageNum, int limit, Integer languageOrdinal);

    List<OrderHistoryResponse> getAllByUserId(String token, Integer languageOrdinal);

    String repeatOrder(String token, RepeatOrderRequest request, Integer languageOrdinal);

    void checkNewOrders();
}
