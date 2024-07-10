package lotto.common.exception;

import lotto.common.exception.classes.BonusNumberConflictException;
import lotto.common.exception.classes.CustomException;
import lotto.common.exception.classes.InvalidBonusNumberRangeException;
import lotto.common.exception.classes.InvalidLottoRangeException;
import lotto.common.exception.classes.InvalidLottoSizeException;
import lotto.common.exception.classes.InvalidNumberInputException;
import lotto.common.exception.classes.LottoMoneyTooSmallException;
import lotto.common.exception.classes.LottoNumberDuplicateException;
import lotto.common.exception.classes.LottoPurchaseAmountNotMultipleException;

/**
 * 커스텀 예외의 타입을 정의하는 Enum
 */
public enum CustomExceptionType {
    INVALID_LOTTO_RANGE(InvalidLottoRangeException.class),// 로또 번호 범위가 유효하지 않은 경우
    BONUS_NUMBER_CONFLICT(BonusNumberConflictException.class),// 보너스 번호가 당첨 번호와 중복되는 경우
    INVALID_BONUS_NUMBER_RANGE(InvalidBonusNumberRangeException.class),// 보너스 번호 범위가 유효하지 않은 경우
    INVALID_LOTTO_SIZE(InvalidLottoSizeException.class),// 로또 번호 개수가 유효하지 않은 경우
    INVALID_NUMBER_INPUT(InvalidNumberInputException.class),// 입력한 숫자가 유효하지 않은 경우
    LOTTO_MONEY_TOO_SMALL(LottoMoneyTooSmallException.class),// 로또 구매 금액이 유효하지 않은 경우
    LOTTO_NUMBER_DUPLICATE(LottoNumberDuplicateException.class),// 로또 번호가 중복되는 경우
    LOTTO_PURCHASE_AMOUNT_NOT_MULTIPLE(LottoPurchaseAmountNotMultipleException.class);// 로또 구매 금액이 유효하지 않은 경우

    private final Class<? extends CustomException> exceptionClass;

    CustomExceptionType(Class<? extends CustomException> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public Class<? extends CustomException> getExceptionClass() {
        return exceptionClass;
    }
}
