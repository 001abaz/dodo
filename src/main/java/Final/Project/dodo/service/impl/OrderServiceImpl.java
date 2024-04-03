package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.OrderRep;
import Final.Project.dodo.dao.projection.OrderListResponse;
import Final.Project.dodo.exception.NotFoundException;
import Final.Project.dodo.model.dto.*;
import Final.Project.dodo.model.entities.Order;
import Final.Project.dodo.model.enums.OrderStatus;
import Final.Project.dodo.model.mapper.OrderMapper;
import Final.Project.dodo.model.request.OrderProductRequest;
import Final.Project.dodo.model.request.RepeatOrderRequest;
import Final.Project.dodo.model.request.create.OrderCreateRequest;
import Final.Project.dodo.model.request.update.OrderUpdateRequest;
import Final.Project.dodo.model.response.OrderHistoryResponse;
import Final.Project.dodo.model.response.ProductListResponse;
import Final.Project.dodo.service.*;
import Final.Project.dodo.utils.JwtProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDto, OrderMapper> implements OrderService {
    public OrderServiceImpl(OrderRep rep, OrderMapper mapper, UserService userService, AddressService addressService, ProductSizeService productSizeService, OrderProductService orderProductService, JwtProvider jwtProvider) {
        super(rep, mapper);
        this.userService = userService;
        this.addressService = addressService;

        this.productSizeService = productSizeService;
        this.orderProductService = orderProductService;
        this.jwtProvider = jwtProvider;
    }
    private final UserService userService;
    private final AddressService addressService;
    private final ProductSizeService productSizeService;
    private final OrderProductService orderProductService;
    private final JwtProvider jwtProvider;
    @Override
    public OrderDto create(String accessToken, OrderCreateRequest request) {
        Long userId = jwtProvider.validateToken(accessToken);
        BigDecimal totalPrice = BigDecimal.ZERO;
        Integer dodoCoins;

        AddressDto addressDto = addressService.findById(request.getAddressId());
        if (addressDto == null) {
            throw new NotFoundException("Address not found for ID: " + request.getAddressId());
        }
        UserDto userDto = userService.findById(userId);

        OrderDto dto = new OrderDto();
        dto.setAddress(addressDto);
        dto.setUser(userDto);
        dto.setOrderDate(request.getOrderDate());
        dto.setPaymentType(request.getPaymentType());
        if (request.getOrderDate().plusMinutes(30).isAfter(LocalDateTime.now())) {
            dto.setOrderStatus(OrderStatus.NEW);
        } else {
            dto.setOrderStatus(OrderStatus.PREPARING);
        }

        for (OrderProductRequest r : request.getOrderProductList()) {
            ProductSizeDto productSizeDto = productSizeService.findById(r.getProductSizeId());
            if (productSizeDto == null) {
                throw new NotFoundException("Product size not found for ID: " + r.getProductSizeId());
            }
            totalPrice = totalPrice.add(productSizeDto.getPrice());
        }
        dodoCoins =  totalPrice.multiply(BigDecimal.valueOf(0.20)).intValue();

        dto.setTotalPrice(totalPrice);
        dto.setDodoCoins(dodoCoins);

        if (userDto.getDodoCoins() == null){
            userDto.setDodoCoins(dodoCoins);
        }else {
            userDto.setDodoCoins(userDto.getDodoCoins() + dodoCoins);
        }

        OrderDto savedOrder = save(dto);

        userService.update(userDto);

        for (OrderProductRequest r : request.getOrderProductList()) {
            ProductSizeDto productSizeDto = productSizeService.findById(r.getProductSizeId());
            if (productSizeDto == null) {
                throw new NotFoundException("Product size not found for ID: " + r.getProductSizeId());
            }
            OrderProductDto orderProductDto = new OrderProductDto();
            orderProductDto.setProduct(productSizeDto.getProduct());
            orderProductDto.setPrice(productSizeDto.getPrice());
            orderProductDto.setOrder(savedOrder);
            orderProductService.save(orderProductDto);
        }

        return savedOrder;
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

    public List<ProductListResponse> findByOrderId(Long id){
        List<ProductListResponse> productListResponses = new ArrayList<>();
       List<OrderProductDto> orderProductDtoList = orderProductService.findAllByOrderId(id);
       for (OrderProductDto orderProductDto: orderProductDtoList){
           ProductListResponse response = new ProductListResponse();
           response.setName(orderProductDto.getProduct().getName());
           response.setLogo(orderProductDto.getProduct().getLogo());
           response.setId(orderProductDto.getProduct().getId());
           response.setDescription(orderProductDto.getProduct().getDescription());
           response.setCategoryName(orderProductDto.getProduct().getCategories().getName());


           productListResponses.add(response);
       }
       return productListResponses;
    }

    @Override
    public List<OrderHistoryResponse> getByUserId(String token) {
        Long userId = jwtProvider.validateToken(token);

        List<OrderHistoryResponse> responseList = new ArrayList<>();

        List<OrderListResponse> orderListResponseList =  rep.findByUserId(userId);

        for (OrderListResponse r: orderListResponseList){
            OrderHistoryResponse response = new OrderHistoryResponse();
            response.setOrderDate(r.getOrderDate());
            response.setNumOfOrder(r.getId());
            response.setTotalPrice(r.getTotalPrice());
            response.setProductListResponse(findByOrderId(r.getId()));
            response.setAddressResponse(addressService.findByAddressId(r.getAddressId()));
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public OrderDto repeatOrder(String token, RepeatOrderRequest request) {
        jwtProvider.validateToken(token);

        OrderDto dto = findById(request.getId());
        OrderDto newDto = new OrderDto();
        newDto.setUser(dto.getUser());
        newDto.setTotalPrice(dto.getTotalPrice());
        newDto.setDodoCoins(dto.getDodoCoins());
        newDto.setOrderDate(request.getOrderDate());
        newDto.setPaymentType(request.getPaymentType());
        newDto.setAddress(addressService.findById(request.getAddressId()));

        if (newDto.getOrderDate().plusMinutes(30).isAfter(LocalDateTime.now())) {
            newDto.setOrderStatus(OrderStatus.NEW);
        } else {
            newDto.setOrderStatus(OrderStatus.PREPARING);
        }

        return save(newDto);
    }
}
