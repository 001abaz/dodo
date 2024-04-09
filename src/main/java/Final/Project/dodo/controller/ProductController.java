package Final.Project.dodo.controller;


import Final.Project.dodo.model.request.create.ProductCreateRequest;
import Final.Project.dodo.model.request.update.ProductUpdateRequest;
import Final.Project.dodo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductCreateRequest request,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductUpdateRequest request,
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.delete(id, languageOrdinal));
    }
}
