package racingcar.view.output;

import racingcar.service.result.RacingGameRoundResult;

public class RacingGameRoundResultOutputView implements OutputView<RacingGameRoundResult> {

  @Override
  public void print(RacingGameRoundResult roundResult) {

    roundResult.getResultMap().forEach((name, distance) -> {
      System.out.println(name + " : " + "-".repeat(distance));
    });

    System.out.println();
  }
}
