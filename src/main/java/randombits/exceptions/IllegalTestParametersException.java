package randombits.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alexey Raichev
 * @since 15.04.2020
 * Unprocessable Entity (422) - server understands content type and
 * syntax of entity is correct, hence no Unsupported Media Type (415)
 * or Bad Request (400).
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IllegalTestParametersException extends RuntimeException {

    public IllegalTestParametersException(String message, Throwable cause) {
        super(message, cause);
    }

}
