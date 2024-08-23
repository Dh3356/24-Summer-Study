package baseball.baseball_game_client.view.input;

import static baseball.baseball_game_client.view.input.message.InputMessage.BASEBALL_GAME_RETRY_INPUT;

import baseball.baseball_game_client.util.input.InputUtil;

/**
 * 숫자 야구 게임 재시도 입력 뷰
 */
public class BaseballGameRetryInputView implements InputView<Integer> {

  /**
   * 재시도 여부를 입력받아 반환한다.
   *
   * @return 재시도 여부
   */
  @Override
  public Integer input() {
    printBeforeInput();
    return InputUtil.readInt();
  }

  @Override
  public void printBeforeInput() {
    System.out.print(BASEBALL_GAME_RETRY_INPUT);
  }
}
