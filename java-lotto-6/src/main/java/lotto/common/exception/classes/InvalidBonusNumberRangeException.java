package lotto.common.exception.classes;

import static lotto.common.constant.LottoConstants.LOTTO_BONUS_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_BONUS_MIN;

public class InvalidBonusNumberRangeException extends CustomException {
    private static final String ERROR_MESSAGE =
            "보너스 번호는 " + LOTTO_BONUS_MIN + "부터 " + LOTTO_BONUS_MAX + " 사이의 숫자여야 합니다.";

    public InvalidBonusNumberRangeException() {
        super(ERROR_MESSAGE);
    }
}
