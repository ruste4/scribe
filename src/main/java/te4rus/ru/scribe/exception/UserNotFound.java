package te4rus.ru.scribe.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String username) {
        super(String.format("User with name:%s not found", username));
    }

    public UserNotFound(Long userId) {
        super(String.format("User with id:%s not found", userId));
    }
}
