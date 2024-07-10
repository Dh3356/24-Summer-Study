package lotto.common.exception.classes;

public class LottoNumberDuplicateException extends CustomException {
    private static final String ERROR_MESSAGE = "로또 번호는 중복되지 않아야 합니다.";

    public LottoNumberDuplicateException() {
        super(ERROR_MESSAGE);
    }
}
