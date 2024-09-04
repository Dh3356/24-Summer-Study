package baseball.server.util.number_generator;

import static baseball.server.common.message.ErrorMessage.INVALID_MIN_MAX_ERROR;
import static baseball.server.common.message.ErrorMessage.INVALID_SIZE_ERROR;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 일반적인 랜덤 숫자 생성기
 */
public class NormalRandomNumberGenerator implements RandomNumberGenerator {

  @Override
  public List<Integer> generateRandomNumbers(final int min, final int max, final int size) {
    validate(min, max, size);

    LinkedHashSet<Integer> randomNumbers = new LinkedHashSet<>();

    while (randomNumbers.size() < size) {
      randomNumbers.add(Randoms.pickNumberInRange(min, max));
    }

    return new ArrayList<>(randomNumbers);
  }

  /**
   * 입력값을 검증한다.
   *
   * @param min  최소값
   * @param max  최대값
   * @param size 리스트 크기
   */
  private void validate(final int min, final int max, final int size) {
    validateMinAndMax(min, max);
    validateSize(min, max, size);
  }

  /**
   * 최소값과 최대값을 검증한다.
   *
   * @param min 최소값
   * @param max 최대값
   */
  public void validateMinAndMax(int min, int max) {
    if (min > max) {
      throw new IllegalArgumentException(INVALID_MIN_MAX_ERROR);
    }
  }

  /**
   * 리스트 크기를 검증한다.
   * <p>
   * 최소값과 최대값 사이의 크기보다 크거나 같으면 예외를 발생시킨다.
   *
   * @param min  최소값
   * @param max  최대값
   * @param size 리스트 크기
   */
  public void validateSize(int min, int max, int size) {
    if (size > max - min + 1) {
      throw new IllegalArgumentException(INVALID_SIZE_ERROR);
    }
  }
}
