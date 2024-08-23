package baseball.baseball_game_client.view.input.message;

import static baseball.baseball_game_client.common.constant.Constants.BASEBALL_GAME_END;
import static baseball.baseball_game_client.common.constant.Constants.BASEBALL_GAME_RESTART;

/**
 * 입력을 받을 때 출력할 메시지를 관리하는 클래스
 */
public class InputMessage {

  public static final String BASEBALL_GAME_ROUND_INPUT = "숫자를 입력해주세요 : ";
  public static final String BASEBALL_GAME_RETRY_INPUT =
      "게임을 새로 시작하려면 " + BASEBALL_GAME_RESTART + ", 종료하려면 " + BASEBALL_GAME_END + "를 입력하세요.";
}
