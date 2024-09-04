package baseball.server.service.game_policy;

import static baseball.server.common.message.ErrorMessage.INVALID_NUMBERS_CONFLICTS_ERROR;
import static baseball.server.common.message.ErrorMessage.INVALID_NUMBERS_COUNT_ERROR;
import static baseball.server.common.message.ErrorMessage.INVALID_NUMBER_BOUND_ERROR;

import baseball.server.domain.Numbers;
import java.util.List;

/**
 * 일반적인 숫자 야구 게임 정책을 나타내는 클래스
 */
public class NormalBaseballGamePolicy implements BaseballGamePolicy {

  @Override
  public int getMinNumber() {
    return 1;
  }

  @Override
  public int getMaxNumber() {
    return 9;
  }

  @Override
  public int getNumberSize() {
    return 3;
  }

  @Override
  public void validateInputNumbers(final List<Integer> numbers) {
    validateNumbersSize(numbers);
    validateNumbersConflicts(numbers);
    validateNumberRange(numbers);
  }

  @Override
  public boolean isStrike(final Numbers numbers, final int number, final int index) {
    return numbers.isExactMatch(number, index);
  }

  @Override
  public boolean isBall(final Numbers numbers, final int number, final int index) {
    return numbers.containsButNotSameIndex(number, index);
  }

  @Override
  public boolean isWin(final int strikeCount) {
    return strikeCount == getNumberSize();
  }

  /**
   * 입력 숫자의 개수를 검증한다.
   *
   * @param numbers 입력 숫자
   */
  private void validateNumbersSize(final List<Integer> numbers) {
    if (numbers.size() != getNumberSize()) {
      throw new IllegalArgumentException(INVALID_NUMBERS_COUNT_ERROR);
    }
  }

  /**
   * 입력 숫자의 중복을 검증한다.
   *
   * @param numbers 입력 숫자
   */
  private void validateNumbersConflicts(final List<Integer> numbers) {
    if (numbers.stream().distinct().count() != getNumberSize()) {
      throw new IllegalArgumentException(INVALID_NUMBERS_CONFLICTS_ERROR);
    }
  }

  /**
   * 입력 숫자의 범위를 검증한다.
   *
   * @param numbers 입력 숫자
   */
  private void validateNumberRange(final List<Integer> numbers) {
    numbers.forEach(number -> {
      if (number < getMinNumber() || number > getMaxNumber()) {
        throw new IllegalArgumentException(INVALID_NUMBER_BOUND_ERROR);
      }
    });
  }
}
