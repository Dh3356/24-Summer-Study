package racingcar.common.util.input;

import static racingcar.common.ErrorMessage.INVALID_INPUT_ERROR;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

/**
 * 입력 유틸
 */
public class InputUtil {

  /**
   * 한 줄의 문자열을 입력받는다.
   *
   * @return 입력받은 문자열
   * @throws IllegalArgumentException 입력이 잘못된 경우
   */
  public static String readLine() {
    try {
      return Console.readLine();
    } catch (Exception e) {
      throw new IllegalArgumentException(INVALID_INPUT_ERROR);
    }
  }

  /**
   * 정수 하나를 입력받는다.
   *
   * @return 입력받은 정수
   * @throws IllegalArgumentException 입력이 잘못된 경우
   */
  public static int readInt() {
    try {
      return Integer.parseInt(readLine());
    } catch (Exception e) {
      throw new IllegalArgumentException(INVALID_INPUT_ERROR);
    }
  }

  /**
   * 문자열을 입력받아 구분자로 나눈다.
   *
   * @param regex 구분자
   * @return 구분된 문자열 리스트
   */
  public static List<String> readAndSplit(String regex) {
    return List.of(readLine().split(regex));
  }
}
