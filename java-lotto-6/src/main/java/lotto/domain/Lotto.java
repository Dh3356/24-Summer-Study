package lotto.domain;

import static lotto.common.constant.ErrorMessageConstants.INVALID_LOTTO_NUMBER_DUPLICATE;
import static lotto.common.constant.ErrorMessageConstants.INVALID_LOTTO_NUMBER_RANGE;
import static lotto.common.constant.ErrorMessageConstants.INVALID_LOTTO_NUMBER_SIZE;
import static lotto.common.constant.LottoConstants.LOTTO_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_MIN;
import static lotto.common.constant.LottoConstants.LOTTO_SIZE;

import java.util.List;

/**
 * 로또 번호를 관리하는 객체 로또 번호는 1부터 45까지의 숫자로 이루어져 있으며, 중복되는 숫자가 없어야 한다. 사용자 로또, 당첨 로또를 관리하는 객체가 상속받아 사용한다. 로또 번호는 숫자를 포함하고
 * 있는지, 일치하는 숫자의 개수를 구하는 기능을 제공한다.
 */
public class Lotto {
    protected final List<Integer> numbers;// 로또 번호 List

    public Lotto(final List<Integer> numbers) {
        validate(numbers);// validate 메서드를 통해 로또 번호의 유효성을 검증한다.
        this.numbers = numbers;
    }

    /**
     * 로또 번호 List 를 반환한다.
     *
     * @return 로또 번호 List
     */
    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * 다른 로또와 일치하는 숫자의 개수를 반환한다.
     *
     * @param lotto 비교할 로또
     * @return 일치하는 숫자의 개수
     */
    public int countMatch(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    /**
     * 로또 번호에 특정 숫자가 포함되어 있는지 확인한다.
     *
     * @param number 확인할 숫자
     * @return 포함 여부
     */
    public boolean contains(int number) {
        return numbers.contains(number);
    }

    /**
     * 생성자로 받은 매개변수들을 검증한다.
     *
     * @param numbers 확인할 로또 번호 List
     */
    protected void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateNumbersRange(numbers);
        validateNumbersConflict(numbers);
    }

    /**
     * 로또 번호 List 의 크기가 유효한지 검증한다.
     *
     * @param numbers 로또 번호 List
     */
    private void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    /**
     * 로또 번호 List 의 중복 여부를 검증한다.
     *
     * @param numbers 로또 번호 List
     */
    private void validateNumbersConflict(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_DUPLICATE);
        }
    }

    /**
     * 로또 번호 List 에 있는 모든 번호의 범위가 유효한지 검증한다.
     *
     * @param numbers 로또 번호 List
     */
    private void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(this::validateNumberRange);
    }

    /**
     * 로또 번호가 유효한 범위인지 검증한다.
     *
     * @param number 로또 번호
     */
    private void validateNumberRange(int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE);
        }
    }
}
