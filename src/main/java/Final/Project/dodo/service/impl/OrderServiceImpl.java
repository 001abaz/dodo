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
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDto, OrderMapper> implements OrderService {

    private final UserService userService;
    private final AddressService addressService;
    private final ProductSizeService productSizeService;
    private final OrderProductService orderProductService;
    private final JwtProvider jwtProvider;

    public OrderServiceImpl(OrderRep rep, OrderMapper mapper, UserService userService,
                            AddressService addressService, ProductSizeService productSizeService,
                            OrderProductService orderProductService, JwtProvider jwtProvider) {
        super(rep, mapper);
        this.userService = userService;
        this.addressService = addressService;
        this.productSizeService = productSizeService;
        this.orderProductService = orderProductService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String create(String accessToken, OrderCreateRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        Long userId = jwtProvider.validateToken(accessToken, languageOrdinal);
        BigDecimal totalPrice = BigDecimal.ZERO;
        Integer dodoCoins;

        AddressDto addressDto = addressService.findById(request.getAddressId(), languageOrdinal);
        if (addressDto == null) {
            throw new NotFoundException("Address not found for ID: " + request.getAddressId());
        }
        UserDto userDto = userService.findById(userId, languageOrdinal);

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
            totalPrice = totalPrice.add(r.getPrice());
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
            ProductSizeDto productSizeDto = productSizeService.findById(r.getProductSizeId(), languageOrdinal);
            if (productSizeDto == null) {
                throw new NotFoundException("Product size not found for ID: " + r.getProductSizeId());
            }
            OrderProductDto orderProductDto = new OrderProductDto();
            orderProductDto.setProduct(productSizeDto.getProduct());
            orderProductDto.setPrice(productSizeDto.getPrice());
            orderProductDto.setOrder(savedOrder);
            orderProductService.save(orderProductDto);
        }
        return ResourceBundleLanguage.periodMessage(language,"createSuccessful");
    }


    @Override
    public OrderDto update(OrderUpdateRequest request, Integer languageOrdinal) {
        OrderDto dto = new OrderDto();
        dto.setId(request.getId());
        dto.setUser(userService.findById(request.getUserId(), languageOrdinal));
        dto.setAddress(addressService.findById(request.getAddressId(), languageOrdinal));
        dto.setTotalPrice(request.getTotalPrice());
        dto.setOrderStatus(request.getOrderStatus());
        dto.setOrderDate(request.getOrderDate());
        dto.setPaymentType(request.getPaymentType());
        dto.setOrderStatus(request.getOrderStatus());
        dto.setDodoCoins(request.getDodoCoins());

        return update(dto);
    }

    public List<ProductListResponse> findByOrderId(Long id, Integer languageOrdinal){

        List<ProductListResponse> productListResponses = new ArrayList<>();
        List<OrderProductDto> orderProductDtoList = orderProductService.findAllByOrderId(id, languageOrdinal);

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
    public List<OrderHistoryResponse> getAllByUserId(String token, int pageNum, int limit, Integer languageOrdinal) {
        Long userId = jwtProvider.validateToken(token, languageOrdinal);

        List<OrderHistoryResponse> responseList = new ArrayList<>();

        Page<OrderListResponse> page = rep.findByUserId(userId, PageRequest.of(pageNum, limit));
        List<OrderListResponse> orderListResponseList = page.getContent();

        for (OrderListResponse r: orderListResponseList){
            OrderHistoryResponse response = new OrderHistoryResponse();
            response.setOrderDate(r.getOrderDate());
            response.setNumOfOrder(r.getId());
            response.setTotalPrice(r.getTotalPrice());
            response.setProductListResponse(findByOrderId(r.getId(), languageOrdinal));
            response.setAddressResponse(addressService.findByAddressId(r.getAddressId()));
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public List<OrderHistoryResponse> getAllByUserId(String token, Integer languageOrdinal) {
        Long userId = jwtProvider.validateToken(token, languageOrdinal);
        List<OrderHistoryResponse> responseList = new ArrayList<>();
        List<OrderListResponse> list = rep.findByUserId(userId);
        for (OrderListResponse r : list) {
            OrderHistoryResponse response = new OrderHistoryResponse();
            response.setOrderDate(r.getOrderDate());
            response.setNumOfOrder(r.getId());
            response.setTotalPrice(r.getTotalPrice());
            response.setProductListResponse(findByOrderId(r.getId(), languageOrdinal));
            response.setAddressResponse(addressService.findByAddressId(r.getAddressId()));
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public String repeatOrder(String token, RepeatOrderRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        jwtProvider.validateToken(token, languageOrdinal);

        OrderDto oldDto = findById(request.getId(), languageOrdinal);
        OrderCreateRequest createRequest = new OrderCreateRequest();

        createRequest.setOrderDate(oldDto.getOrderDate());
        createRequest.setPaymentType(oldDto.getPaymentType());
        createRequest.setAddressId(oldDto.getAddress().getId());
        List<OrderProductDto> list = orderProductService.findAllByOrderId(oldDto.getId(), languageOrdinal);
        List<OrderProductRequest> orderProductList = new ArrayList<>();
        for (OrderProductDto dto: list){
            OrderProductRequest newRequest = new OrderProductRequest();
            newRequest.setPrice(dto.getPrice());
            orderProductList.add(newRequest);
        }
        createRequest.setOrderProductList(orderProductList);
        create(token, createRequest, languageOrdinal);

        return ResourceBundleLanguage.periodMessage(language,"createSuccessful");
    }

    @Override
    public void checkNewOrders() {
        List<OrderDto> orderList = mapper.toDtos(rep.findByOrderStatus(OrderStatus.NEW), context);
        for (OrderDto dto: orderList) {
            if ((dto.getUpdateDate().plusMinutes(30).isEqual(dto.getOrderDate())
                    || dto.getUpdateDate().plusMinutes(30).isBefore(dto.getOrderDate()))) {
                dto.setOrderStatus(OrderStatus.PREPARING);
                update(dto);
            }
        }
    }
    // eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MTI1MDcwNzYsImV4cCI6NzExMjUwNzA3NiwidXNlcklkIjoxfQ.UfEUCt7TT-vOs77FVR1u-cFzzv2aiNFmA_HPqSdUm16pXdJHj2Wdf0kRSlyucQubA6W93duMSKY64JFO3VjU3Q


    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }
}


