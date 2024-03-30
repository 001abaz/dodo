package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.OrderRep;
import Final.Project.dodo.exception.NotFoundException;
import Final.Project.dodo.model.dto.AddressDto;
import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.entities.Order;
import Final.Project.dodo.model.enums.OrderStatus;
import Final.Project.dodo.model.mapper.OrderMapper;
import Final.Project.dodo.model.request.OrderProductRequest;
import Final.Project.dodo.model.request.create.OrderCreateRequest;
import Final.Project.dodo.model.request.create.OrderProductCreateRequest;
import Final.Project.dodo.model.request.update.OrderUpdateRequest;
import Final.Project.dodo.service.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDto, OrderMapper> implements OrderService {
    public OrderServiceImpl(OrderRep rep, OrderMapper mapper, UserService userService, AddressService addressService, ProductSizeService productSizeService, OrderProductService orderProductService) {
        super(rep, mapper);
        this.userService = userService;
        this.addressService = addressService;

        this.productSizeService = productSizeService;
        this.orderProductService = orderProductService;
    }
    private final UserService userService;
    private final AddressService addressService;
    private final ProductSizeService productSizeService;
    private final OrderProductService orderProductService;

    @Override
    public OrderDto create(OrderCreateRequest request) {

        BigDecimal totalPrice = BigDecimal.valueOf(0);
        BigDecimal dodoCoins;

        AddressDto addressDto = addressService.findById(request.getAddressId());
        if (addressDto == null) {
            throw new NotFoundException("Address not found for ID: " + request.getAddressId());
        } else {
            OrderDto dto = new OrderDto();
            dto.setUser(userService.findById(request.getUserId()));
            dto.setAddress(addressService.findById(request.getAddressId()));
            dto.setOrderDate(request.getOrderDate());
            dto.setPaymentType(request.getPaymentType());
            save(dto);


            for (OrderProductRequest r: request.getOrderProductList()){
                OrderProductDto orderProductDto = orderProductService.create(
                        new OrderProductCreateRequest(r.getProductId(), dto, r.getSizeId()));
                totalPrice.add(orderProductDto.getPrice());

            }
            dodoCoins = totalPrice.multiply(BigDecimal.valueOf(0.1));

            dto.setDodoCoins(dodoCoins);
            dto.setOrderStatus(OrderStatus.PREPARING);
            update(dto);
            return dto;
        }


    }

    @Override
    public OrderDto update(OrderUpdateRequest request) {
        OrderDto dto = new OrderDto();
        dto.setId(request.getId());
        dto.setUser(userService.findById(request.getUserId()));
        dto.setAddress(addressService.findById(request.getAddressId()));
        dto.setTotalPrice(request.getTotalPrice());
        dto.setOrderStatus(request.getOrderStatus());
        dto.setOrderDate(request.getOrderDate());
        dto.setPaymentType(request.getPaymentType());
        dto.setOrderStatus(request.getOrderStatus());
        dto.setDodoCoins(request.getDodoCoins());

        return update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return delete(findById(id));
    }
}
