package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.request.create.OrderProductCreateRequest;
import Final.Project.dodo.model.request.update.OrderProductUpdateRequest;

import java.util.List;

public interface OrderProductService extends BaseService<OrderProductDto> {
    String create (OrderProductCreateRequest request,Integer languageOrdinal);
    OrderProductDto update(OrderProductUpdateRequest request,Integer languageOrdinal);
    Boolean delete(Long id, Integer languageOrdinal);
    List<OrderProductDto> findAllByOrderId(Long id, Integer languageOrdinal);
}
