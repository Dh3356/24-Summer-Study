package baseball.server.util.number_generator;

import java.util.List;

/**
 * 랜덤 숫자 생성기
 * <p>
 * 랜점 숫자 생성 정책에 따라 구현체가 달라진다
 */
public interface RandomNumberGenerator {

  /**
   * 랜덤 숫자 리스트를 생성한다.
   *
   * @param min  최소값
   * @param max  최대값
   * @param size 리스트 크기
   * @return 랜덤 숫자 리스트
   */
  List<Integer> generateRandomNumbers(final int min, final int max, final int size);
}
