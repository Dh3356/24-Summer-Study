package baseball.baseball_game_client.view.output.message;

import static baseball.baseball_game_client.common.constant.Constants.BASEBALL_GAME_NUMBER_SIZE;

/**
 * 출력 메시지를 관리하는 클래스
 */
public class OutputMessage {

  public static final String BASEBALL_GAME_OVER =
      BASEBALL_GAME_NUMBER_SIZE + "개의 숫자를 모두 맞히셨습니다! 게임 종료";
  public static final String BASEBALL_GAME_START = "숫자 야구 게임을 시작합니다.";
  public static final String STRIKE = "스트라이크";
  public static final String BALL = "볼";
  public static final String NOTHING = "낫싱";
}
