package lotto.common.exception.classes;

import static lotto.common.constant.LottoConstants.LOTTO_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_MIN;

public class InvalidLottoRangeException extends CustomException {
    private static final String ERROR_MESSAGE = "로또 번호는 " + LOTTO_MIN + "부터 " + LOTTO_MAX + " 사이의 숫자여야 합니다.";

    public InvalidLottoRangeException() {
        super(ERROR_MESSAGE);
    }
}
