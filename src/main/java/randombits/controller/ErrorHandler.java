package randombits.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import randombits.exceptions.IllegalTestParametersException;
import randombits.util.ErrorEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Alexey Raichev
 * @see randombits.util.NistFactory
 * 3. Umprocessable Entity (422) - when user haven't passed the right parameters in the request body or
 * parameters values were rejected during validation. Provides an explanation of correct parameters in the
 * message
 * 4. Not Found (404) - when user tries to perform request on the URL that have not been mapped by existing
 * controllers.
 * @since 16.04.2020
 * Handle possible exceptions user may face while using the API.
 * URL's: '/nist_test/{method}' (1), '/md_test/{method}' (2).
 * These exceptions include:
 * 1. Method Not Allowed (403) - when user tries to use get method on the URLs (1) and
 * (2). Both URL's support a set of methods for performing statistical test and a corresponding
 * POST method to provide test parameters.
 * 2. Bad Request (400) - when user tries to make a POST request on URL's (1) or (2) and method name
 * have not been found in the factory.
 */

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status,
                                                                         WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        errorEntity.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        errorEntity.setError(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        errorEntity.setMessage(((ServletWebRequest) request).getRequest().getMethod() +
                " method is not supported by this URL.");
        errorEntity.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        String methods = ex.getSupportedHttpMethods().toString();
        headers.add("Allow", methods.substring(1, methods.length() - 1));
        return new ResponseEntity<>(errorEntity, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                               WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        errorEntity.setStatus(HttpStatus.BAD_REQUEST.value());
        errorEntity.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        String s = "";
        if (Objects.requireNonNull(ex.getRequiredType()).isEnum()) {
            s = Arrays.toString(ex.getRequiredType().getEnumConstants());
            s = s.substring(1, s.length() - 1).toLowerCase();
        }
        errorEntity.setMessage("Url parameter should be one of: " + s + ".");
        errorEntity.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalTestParametersException.class)
    private ResponseEntity<Object> handleIllegalParametersException(IllegalTestParametersException ex,
                                                                    WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        errorEntity.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorEntity.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        errorEntity.setMessage(ex.getMessage());
        errorEntity.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(errorEntity, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        errorEntity.setStatus(HttpStatus.NOT_FOUND.value());
        errorEntity.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorEntity.setMessage("No handler found on server for this URL.");
        errorEntity.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        errorEntity.setStatus(HttpStatus.BAD_REQUEST.value());
        errorEntity.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorEntity.setMessage("Missing request parameters.");
        errorEntity.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        errorEntity.setStatus(HttpStatus.BAD_REQUEST.value());
        errorEntity.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorEntity.setMessage("Request body is not readable.");
        errorEntity.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }
}
