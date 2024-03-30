package Final.Project.dodo.controller;


import Final.Project.dodo.model.request.create.SizeCreateRequest;
import Final.Project.dodo.model.request.update.SizeUpdateRequest;
import Final.Project.dodo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/size")
public class SizeController {

    private final SizeService service;

    @PostMapping
    public ResponseEntity<?> create(SizeCreateRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@ModelAttribute SizeUpdateRequest request){
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
