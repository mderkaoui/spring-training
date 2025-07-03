package fr.dawan.project1.interceptors;

import fr.dawan.project1.dto.ErrorLogDto;
import fr.dawan.project1.exceptions.IdNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IdNotFoundException.class})
    protected ResponseEntity<?> handleIdNotFoundException(Exception ex, WebRequest request){
        ErrorLogDto error = new ErrorLogDto();
        error.setErrorCode(404);
        error.setLevel(ErrorLogDto.LogLevel.ERROR);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<?> handleGenericException(Exception ex, WebRequest request){
        ErrorLogDto error = new ErrorLogDto();
        error.setErrorCode(500);
        error.setLevel(ErrorLogDto.LogLevel.ERROR);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
