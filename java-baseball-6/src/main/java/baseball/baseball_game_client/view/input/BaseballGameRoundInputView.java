package baseball.baseball_game_client.view.input;

import static baseball.baseball_game_client.view.input.message.InputMessage.BASEBALL_GAME_ROUND_INPUT;

import baseball.baseball_game_client.util.input.InputUtil;
import java.util.List;

/**
 * 숫자 야구 게임 라운드 입력 뷰
 */
public class BaseballGameRoundInputView implements InputView<List<Integer>> {

  /**
   * 사용자의 숫자를 입력받아 List<Integer> 로 반환한다.
   *
   * @return List<Integer>
   */
  @Override
  public List<Integer> input() {
    printBeforeInput();
    return InputUtil.readNumberList();
  }

  @Override
  public void printBeforeInput() {
    System.out.println(BASEBALL_GAME_ROUND_INPUT);
  }
}
