package in.itkaran.bookmyshow_150824.advices;

import in.itkaran.bookmyshow_150824.exceptions.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }
}
