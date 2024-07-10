package lotto.common.exception.classes;

public class BonusNumberConflictException extends CustomException {
    private static final String ERROR_MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public BonusNumberConflictException() {
        super(ERROR_MESSAGE);
    }
}
