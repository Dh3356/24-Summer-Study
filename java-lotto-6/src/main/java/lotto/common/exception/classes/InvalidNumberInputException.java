package lotto.common.exception.classes;

public class InvalidNumberInputException extends CustomException {
    private static final String ERROR_MESSAGE = "입력한 숫자가 올바르지 않습니다.";

    public InvalidNumberInputException() {
        super(ERROR_MESSAGE);
    }
}
