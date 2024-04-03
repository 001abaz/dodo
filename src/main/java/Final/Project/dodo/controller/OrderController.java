package Final.Project.dodo.controller;


import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.request.RepeatOrderRequest;
import Final.Project.dodo.model.request.create.OrderCreateRequest;
import Final.Project.dodo.model.request.update.OrderUpdateRequest;
import Final.Project.dodo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestHeader String accessToken,
            @RequestBody OrderCreateRequest request) {
        return ResponseEntity.ok(service.create(accessToken, request));
    }
    @PostMapping("repeatOrder")
    public ResponseEntity<?> repeatOrder(@RequestHeader String accessToken,
                                  @RequestBody RepeatOrderRequest request) {
        return ResponseEntity.ok(service.repeatOrder(accessToken, request));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody OrderUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("getById")
    public ResponseEntity<OrderDto> getById(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("HistoryOfOrder")
    public ResponseEntity<?> getHistoryByUserId(@RequestHeader String token) {
        return ResponseEntity.ok(service.getByUserId(token));
    }
}


