package baseball.server.service.game_policy;

import baseball.server.domain.Numbers;
import java.util.List;

/**
 * 숫자 야구 게임 정책을 나타내는 인터페이스
 */
public interface BaseballGamePolicy {

  /**
   * 최소 숫자를 반환한다.
   *
   * @return 최소 숫자
   */
  int getMinNumber();

  /**
   * 최대 숫자를 반환한다.
   *
   * @return 최대 숫자
   */
  int getMaxNumber();

  /**
   * 숫자의 개수를 반환한다.
   *
   * @return 숫자의 개수
   */
  int getNumberSize();

  /**
   * 입력 숫자를 검증한다.
   *
   * @param number 입력 숫자
   */
  void validateInputNumbers(final List<Integer> number);

  /**
   * 스트라이크인지 확인한다.
   *
   * @param numbers 정답 숫자 입급 객체
   * @param number  숫자
   * @param index   인덱스
   * @return 스트라이크 여부
   */
  boolean isStrike(final Numbers numbers, final int number, final int index);

  /**
   * 볼인지 확인한다.
   *
   * @param numbers 정답 숫자 입급 객체
   * @param number  숫자
   * @param index   인덱스
   * @return 볼 여부
   */
  boolean isBall(final Numbers numbers, final int number, final int index);

  /**
   * 승리 여부를 확인한다.
   *
   * @param strikeCount 스트라이크 개수
   * @return 승리 여부
   */
  boolean isWin(final int strikeCount);
}
