package baseball.baseball_game_client.view.output;

import static baseball.baseball_game_client.view.output.message.OutputMessage.BASEBALL_GAME_START;

/**
 * 숫자 야구 게임 시작 출력 뷰
 */
public class BaseballGameStartOutputView implements OutputView {

  /**
   * 숫자 야구 게임 게임 시작 메시지를 출력한다.
   */
  @Override
  public void print() {
    System.out.println(BASEBALL_GAME_START);
  }
}
