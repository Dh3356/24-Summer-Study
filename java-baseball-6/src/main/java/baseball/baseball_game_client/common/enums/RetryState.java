package baseball.baseball_game_client.common.enums;

import static baseball.baseball_game_client.common.constant.Constants.BASEBALL_GAME_END;
import static baseball.baseball_game_client.common.constant.Constants.BASEBALL_GAME_RESTART;
import static baseball.baseball_game_client.common.message.ErrorMessage.INVALID_RETRY_STATE_ERROR;

/**
 * 재시도 상태를 나타내는 enum
 */
public enum RetryState {
  RETRY, END;

  /**
   * int 값을 받아서 RetryState 로 변환한다.
   *
   * @param value int 값
   * @return RetryState
   */
  public static RetryState of(int value) {
    if (value == BASEBALL_GAME_RESTART) {
      return RETRY;
    }
    if (value == BASEBALL_GAME_END) {
      return END;
    }
    throw new IllegalArgumentException(INVALID_RETRY_STATE_ERROR);
  }
}
