package Final.Project.dodo.controller;

import Final.Project.dodo.model.request.create.CategoriesCreateRequest;
import Final.Project.dodo.model.request.update.CategoriesUpdateRequest;
import Final.Project.dodo.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/categories")
public class CategoriesController {

    private final CategoriesService service;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody CategoriesCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody CategoriesUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("getById")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

