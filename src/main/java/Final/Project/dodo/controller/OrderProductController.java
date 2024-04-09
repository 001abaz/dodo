package Final.Project.dodo.controller;

import Final.Project.dodo.model.request.create.OrderProductCreateRequest;
import Final.Project.dodo.model.request.update.OrderProductUpdateRequest;
import Final.Project.dodo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/order-products")
public class OrderProductController {

    private final OrderProductService service;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody OrderProductCreateRequest request,
                                  @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody OrderProductUpdateRequest request,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.update(request, languageOrdinal));
    }
    @GetMapping("getById")
    public ResponseEntity<?> getById(Long id,
                                     @RequestParam(required = false) Integer languageOrdinal){
        return ResponseEntity.ok(service.findById(id, languageOrdinal));
    }
    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.delete(id, languageOrdinal));
    }

    @GetMapping("findAllByOrderId")
    public ResponseEntity<?> findAllByOrderId(@RequestParam Long id,
                                              @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.findAllByOrderId(id, languageOrdinal));
    }
}
