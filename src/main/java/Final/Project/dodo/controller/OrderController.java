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
                                  @RequestBody OrderCreateRequest request,
                                  @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(accessToken, request, languageOrdinal));
    }
    @PostMapping("repeatOrder")
    public ResponseEntity<?> repeatOrder(@RequestHeader String accessToken,
                                         @RequestBody RepeatOrderRequest request,
                                         @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.repeatOrder(accessToken, request, languageOrdinal));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody OrderUpdateRequest request,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.update(request, languageOrdinal));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.delete(id, languageOrdinal));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("getById")
    public ResponseEntity<?> getById(@RequestParam Long id,
                                     @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.findById(id, languageOrdinal));
    }

    @GetMapping("HistoryOfOrder")
    public ResponseEntity<?> getHistoryByUserId(@RequestHeader String token, int pageNum, int limit,
                                                @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.getAllByUserId(token, pageNum, limit, languageOrdinal));
    }
}


