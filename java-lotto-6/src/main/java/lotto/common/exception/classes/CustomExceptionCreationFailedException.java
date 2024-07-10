package lotto.common.exception.classes;

public class CustomExceptionCreationFailedException extends RuntimeException {
    private static final String ERROR_MESSAGE = "CustomException 객체 생성에 실패했습니다.";

    public CustomExceptionCreationFailedException(Exception e) {
        super(ERROR_MESSAGE + " " + e.getMessage());
    }
}
