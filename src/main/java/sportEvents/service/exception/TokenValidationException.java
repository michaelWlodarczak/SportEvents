package sportEvents.service.exception;

public class TokenValidationException extends BusinessServiceException {
    public TokenValidationException(String message) {
        super(message);
    }
}
