package lotto.common.exception.classes;

import static lotto.common.constant.LottoConstants.LOTTO_PRICE;

public class LottoPurchaseAmountNotMultipleException extends CustomException {
    private static final String ERROR_MESSAGE = "로또 구매 금액은 " + LOTTO_PRICE + "원 단위로만 가능합니다.";

    public LottoPurchaseAmountNotMultipleException() {
        super(ERROR_MESSAGE);
    }
}
