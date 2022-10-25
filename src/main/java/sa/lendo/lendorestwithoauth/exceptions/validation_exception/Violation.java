package sa.lendo.lendorestwithoauth.exceptions.validation_exception;

import lombok.Data;

@Data
public class Violation {

    private String fieldName;

    private String message;
}
