package racingcar.view.input;

import java.util.List;
import racingcar.common.util.input.InputUtil;

public class RacingCarNamesInputView implements InputView<List<String>> {

  @Override
  public List<String> input() {
    printBeforeInput();
    return InputUtil.readAndSplit(",").stream().toList(); // 쉼표(,)로 split 후 List 로 변환
  }

  @Override
  public void printBeforeInput() {
    System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,) 기준으로 구분)");
  }
}
