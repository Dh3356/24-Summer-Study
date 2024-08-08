package lotto.common.exception.classes;

import static lotto.common.constant.LottoConstants.LOTTO_SIZE;

public class InvalidLottoSizeException extends CustomException {
    private static final String ERROR_MESSAGE = "로또 번호는 " + LOTTO_SIZE + "개여야 합니다.";

    public InvalidLottoSizeException() {
        super(ERROR_MESSAGE);
    }
}
