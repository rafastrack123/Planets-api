package b2w.test.star.wars.planets.api.controllers.errorhandling;


import static org.springframework.http.HttpStatus.NOT_FOUND;

import b2w.test.star.wars.planets.exceptions.NotFoundException;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionMapperAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Object> handle(NotFoundException cause) {
        var body = new HashMap<>();

        body.put("message", cause.getCause());

        return new ResponseEntity<>(body, NOT_FOUND);
    }
}
