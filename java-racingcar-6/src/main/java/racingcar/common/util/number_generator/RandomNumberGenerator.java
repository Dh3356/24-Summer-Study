package racingcar.common.util.number_generator;

import camp.nextstep.edu.missionutils.Randoms;

/**
 * 랜덤 숫자 생성기
 */
public class RandomNumberGenerator {

  /**
   * min 과 max 사이의 랜덤 숫자를 생성한다.
   *
   * @param min 최소값
   * @param max 최대값
   * @return 생성된 랜덤 숫자
   */
  public int generateRandomNumber(int min, int max) {
    return Randoms.pickNumberInRange(min, max);
  }
}
