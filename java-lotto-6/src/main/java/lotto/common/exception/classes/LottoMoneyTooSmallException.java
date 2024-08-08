package lotto.common.exception.classes;

import static lotto.common.constant.LottoConstants.LOTTO_PRICE;

public class LottoMoneyTooSmallException extends CustomException {
    private static final String ERROR_MESSAGE = "로또 구매 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.";

    public LottoMoneyTooSmallException() {
        super(ERROR_MESSAGE);
    }
}
