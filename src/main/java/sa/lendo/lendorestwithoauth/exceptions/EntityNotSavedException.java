package sa.lendo.lendorestwithoauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityNotSavedException extends RuntimeException {

    public EntityNotSavedException(String message) {
        super(message);
    }
}
