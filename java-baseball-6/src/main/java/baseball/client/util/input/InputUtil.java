package baseball.client.util.input;

import static baseball.client.common.message.ErrorMessage.INPUT_NOT_NUMBER_ERROR;
import static baseball.client.common.message.ErrorMessage.INVALID_INPUT_ERROR;

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
   * @throws IllegalArgumentException 숫자가 아닌 값이 입력된 경우
   */
  public static int readInt() {
    try {
      return Integer.parseInt(readLine());
    } catch (Exception e) {
      throw new IllegalArgumentException(INPUT_NOT_NUMBER_ERROR);
    }
  }

  /**
   * 정수를 입력받아 한 자리씩 나눠 List 로 반환한다.
   *
   * @return 입력받은 정수 리스트
   * @throws IllegalArgumentException 숫자가 아닌 값이 입력된 경우
   */
  public static List<Integer> readNumberList() {
    try {
      return readLine().chars().mapToObj(c -> Integer.parseInt(String.valueOf((char) c))).toList();
    } catch (Exception e) {
      throw new IllegalArgumentException(INPUT_NOT_NUMBER_ERROR);
    }
  }
}
