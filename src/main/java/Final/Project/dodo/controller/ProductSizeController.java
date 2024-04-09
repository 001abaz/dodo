package Final.Project.dodo.controller;

import Final.Project.dodo.model.request.create.ProductSizeCreateRequest;
import Final.Project.dodo.model.request.update.ProductSizeUpdateRequest;
import Final.Project.dodo.service.ProductSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-sizes")
public class ProductSizeController {

    private final ProductSizeService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductSizeCreateRequest request,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductSizeUpdateRequest request,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.update(request ,languageOrdinal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.delete(id, languageOrdinal));
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
    @GetMapping("getProductByFilter")
    public ResponseEntity<?> getProductByFilter(@RequestParam(required = false) Long sizeId,
                                      @RequestParam(required = false) BigDecimal fromPrice,
                                      @RequestParam(required = false) BigDecimal toPrice,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) Long categoryId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getProductSizes(sizeId, fromPrice, toPrice, name, categoryId, pageable));
    }

}
