package Final.Project.dodo.controller;

import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.request.create.OrderProductCreateRequest;
import Final.Project.dodo.model.request.update.OrderProductUpdateRequest;
import Final.Project.dodo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/order-products")
public class OrderProductController {

    private final OrderProductService service;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody OrderProductCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody OrderProductUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }
    @GetMapping("getById")
    public ResponseEntity<?> getById(Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("findAllByOrderId")
    public ResponseEntity<?> findAllByOrderId(@RequestParam Long id) {
        return ResponseEntity.ok(service.findAllByOrderId(id));
    }
}
