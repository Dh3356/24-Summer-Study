package racingcar.view.input;

import racingcar.common.util.input.InputUtil;

public class RacingGameRoundInputView implements InputView<Integer> {

  @Override
  public Integer input() {
    printBeforeInput();
    return InputUtil.readInt();
  }

  @Override
  public void printBeforeInput() {
    System.out.println("시도할 회수는 몇 회인가요?");
  }
}
