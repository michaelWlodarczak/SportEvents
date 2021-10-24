package service.exception;

public class UserNotExistException extends BusinessServiceException{
    public UserNotExistException(String message) {
        super(message);
    }
}
