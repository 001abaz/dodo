package Final.Project.dodo.controller;

import Final.Project.dodo.model.request.create.UserCreateRequest;
import Final.Project.dodo.model.request.update.UserUpdateRequest;
import Final.Project.dodo.service.AccountService;
import Final.Project.dodo.service.UserService;
import Final.Project.dodo.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody UserCreateRequest request, int languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }

    @PutMapping("Update")
    public ResponseEntity<?> update(@RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }
    @GetMapping("getById")
    public ResponseEntity<?> getById(String token,
                                     @RequestParam(required = false) Integer languageOrdinal){
        return ResponseEntity.ok(service.findById(jwtProvider.validateToken(token ,languageOrdinal), languageOrdinal));
    }
    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(required = false) Integer languageOrdinal) {
        return ResponseEntity.ok(service.delete(id, languageOrdinal));
    }

    @GetMapping("UserInfo")
    public ResponseEntity<?> getUserInfo(@RequestHeader String token, int pageNum, int limit,
                                         @RequestParam(required = false) Integer languageOrdinal){
        return ResponseEntity.ok(accountService.getUserInfo(token, pageNum, limit ,languageOrdinal));
//        return null;
    }
}

