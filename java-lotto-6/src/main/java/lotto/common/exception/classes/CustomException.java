package lotto.common.exception.classes;

public abstract class CustomException extends IllegalArgumentException {
    protected CustomException(String message) {
        super(message);
    }
}
