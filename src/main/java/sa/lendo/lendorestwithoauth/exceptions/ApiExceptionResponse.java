package sa.lendo.lendorestwithoauth.exceptions;

import java.time.ZonedDateTime;

public class ApiExceptionResponse {
    private final String message;
    private final int status;
    private final ZonedDateTime timestamp;
    private final String cause;

    public ApiExceptionResponse(String message, int status, Throwable throwable) {
        this.message = message;
        this.status = status;
        this.timestamp = ZonedDateTime.now();
        this.cause = throwable.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getCause() {
        return cause;
    }


}
