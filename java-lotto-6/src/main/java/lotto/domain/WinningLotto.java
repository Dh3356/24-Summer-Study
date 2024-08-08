package lotto.domain;


import static lotto.common.constant.LottoConstants.LOTTO_BONUS_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_BONUS_MIN;
import static lotto.common.exception.ExceptionFactory.getCustomException;

import java.util.List;
import lotto.common.exception.CustomExceptionType;

/**
 * 당첨 로또
 * <p>
 * 보너스 번호를 가지고 있다. 사용자 로또가 보너스 번호를 가지고 있는지 확인할 수 있다.
 */
public class WinningLotto extends Lotto {
    public final int bonusNumber;

    public WinningLotto(final List<Integer> numbers, final int bonusNumber) {
        super(numbers);
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    /**
     * 사용자 로또가 보너스 번호를 가지고 있는지 확인한다.
     *
     * @param lotto 사용자 로또
     * @return 보너스 번호를 가지고 있는지 여부
     */
    public boolean containsBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    /**
     * 생성자로 받은 매개변수들을 검증한다.
     *
     * @param bonusNumber 확인할 보너스 번호
     */
    private void validate(int bonusNumber) {
        validateBonusNumber(bonusNumber);
    }

    /**
     * 보너스 번호의 유효성을 검증한다.
     *
     * @param bonusNumber 확인할 보너스 번호
     */
    private void validateBonusNumber(int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateNumbersConflict(bonusNumber);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < LOTTO_BONUS_MIN || bonusNumber > LOTTO_BONUS_MAX) {
            throw getCustomException(CustomExceptionType.INVALID_BONUS_NUMBER_RANGE);
        }
    }

    private void validateNumbersConflict(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw getCustomException(CustomExceptionType.BONUS_NUMBER_CONFLICT);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "numbers=" + numbers +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
