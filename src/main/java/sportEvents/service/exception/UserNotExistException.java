package sportEvents.service.exception;

public final class UserNotExistException extends BusinessServiceException {

    public UserNotExistException(String message) {
        super(message);
    }
}
