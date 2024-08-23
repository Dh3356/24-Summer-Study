package baseball.client.view.output;

import static baseball.client.view.output.message.OutputMessage.BASEBALL_GAME_OVER;

/**
 * 숫자 야구 게임 종료 출력 뷰
 */
public class BaseballGameOverOutputView implements OutputView {

  /**
   * 숫자 야구 게임 종료 메시지를 출력한다.
   */
  @Override
  public void print() {
    System.out.println(BASEBALL_GAME_OVER);
  }
}
