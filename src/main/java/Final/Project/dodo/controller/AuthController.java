package Final.Project.dodo.controller;


import Final.Project.dodo.model.request.authRequest.AuthRequest;
import Final.Project.dodo.model.request.authRequest.ValidateEmailReq;
import Final.Project.dodo.service.AuthService;
import Final.Project.dodo.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtProvider provider;


    @PostMapping(name = "/auth")
    ResponseEntity<?> auth(@RequestBody AuthRequest request, @RequestParam(required = false) Integer languageOrdinal){
        return ResponseEntity.ok(authService.auth(request, languageOrdinal));
    }

    @GetMapping("/check")
    ResponseEntity<?> validate(@RequestParam() String email, String password,
                               @RequestParam(required = false) Integer languageOrdinal){
        ValidateEmailReq emailReq = new ValidateEmailReq();
        emailReq.setEmail(email);
        emailReq.setPassword(password);
        return ResponseEntity.ok(authService.validate(emailReq, languageOrdinal));
    }

    @GetMapping("/checkToken")
    ResponseEntity<?> validateToken(@RequestParam() String token, Integer languageOrdinal){
        return ResponseEntity.ok(provider.validateToken(token, languageOrdinal));
    }

    @GetMapping("/getClaim")
    ResponseEntity<?> getClaim(@RequestParam() String token){
        return ResponseEntity.ok(provider.getClaim(token));
    }




}
