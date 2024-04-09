package Final.Project.dodo.exception;


import Final.Project.dodo.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> errorsList = new ArrayList<>();

        e.getAllErrors().forEach(err -> errorsList.add(err.getDefaultMessage()));

        return new ResponseEntity<>(new Response(errorsList, e.getMessage()), HttpStatus.MULTI_STATUS);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> exceptionHandle(NotFoundException e) {
        return new ResponseEntity<>(new Response(null, e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> AuthException(AuthException a) {
        return new ResponseEntity<>(new Response(null, a.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<?> DeleteException(RuntimeException ex) {
        return new ResponseEntity<>(new Response(null, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}