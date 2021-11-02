package sportEvents.service.exception;

public class EmailAlreadyExistException extends BusinessServiceException{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
