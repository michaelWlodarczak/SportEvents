package service.exception;

import lombok.NonNull;

public class SubscriptionException extends BusinessServiceException {
    public SubscriptionException(@NonNull String text) {
        super(text);
    }
}
