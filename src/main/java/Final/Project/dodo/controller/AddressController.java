package Final.Project.dodo.controller;

import Final.Project.dodo.model.request.create.AddressCreateRequest;
import Final.Project.dodo.model.request.update.AddressUpdateRequest;
import Final.Project.dodo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/address")
public class AddressController {

    private final AddressService service;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody AddressCreateRequest request ,@RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request , languageOrdinal));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody AddressUpdateRequest request,@RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.update(request, languageOrdinal));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("getById")
    public ResponseEntity<?> getById(@RequestParam Long id,@RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.findById(id, languageOrdinal));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id,@RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.delete(id, languageOrdinal));
    }
}
