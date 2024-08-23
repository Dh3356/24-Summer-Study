package baseball.client.common.message;

import static baseball.client.common.constant.Constants.BASEBALL_GAME_END;
import static baseball.client.common.constant.Constants.BASEBALL_GAME_RESTART;

/**
 * 에러 메시지를 관리하는 클래스
 */
public class ErrorMessage {

  public static final String INVALID_INPUT_ERROR = "잘못된 입력입니다.";
  public static final String INPUT_NOT_NUMBER_ERROR = "숫자가 아닌 값이 입력되었습니다.";
  public static final String INVALID_RETRY_STATE_ERROR =
      BASEBALL_GAME_RESTART + " 또는 " + BASEBALL_GAME_END + "만 입력 가능합니다.";
}
