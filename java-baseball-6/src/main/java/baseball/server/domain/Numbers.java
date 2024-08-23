package baseball.server.domain;

import static baseball.server.common.message.ErrorMessage.OUT_OF_INDEX_ERROR;

import java.util.List;

/**
 * 숫자 List 를 다루는 일급 객체
 */
public class Numbers {

  private final List<Integer> numbers;

  public Numbers(final List<Integer> numbers) {
    this.numbers = numbers;
  }

  /**
   * Index 는 같지 않지만, 숫자가 존재하는지 확인한다.
   *
   * @param number 숫자
   * @param index  인덱스
   * @return Index 는 같지 않지만, 숫자가 존재하는지 여부
   */
  public boolean containsButNotSameIndex(final int number, final int index) {
    validateIndex(index);
    return numbers.contains(number) && !isExactMatch(number, index);
  }

  /**
   * Index 와 숫자가 정확히 일치하는지 확인한다.
   *
   * @param number 숫자
   * @param index  인덱스
   * @return Index 와 숫자가 정확히 일치하는지 여부
   */
  public boolean isExactMatch(final int number, final int index) {
    validateIndex(index);
    return numbers.get(index) == number;
  }

  /**
   * Index 를 검증한다.
   *
   * @param index 인덱스
   */
  private void validateIndex(final int index) {
    if (index < 0 || index >= numbers.size()) {
      throw new IllegalArgumentException(OUT_OF_INDEX_ERROR);
    }
  }
}
