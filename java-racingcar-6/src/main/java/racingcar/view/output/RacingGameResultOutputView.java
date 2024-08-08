package racingcar.view.output;

import java.util.List;
import racingcar.service.result.RacingGameResult;

public class RacingGameResultOutputView implements OutputView<RacingGameResult> {

  @Override
  public void print(RacingGameResult racingGameResult) {

    List<String> names = racingGameResult.getWinnerNames();

    System.out.println("최종 우승자 : " + String.join(", ", names));
  }
}
