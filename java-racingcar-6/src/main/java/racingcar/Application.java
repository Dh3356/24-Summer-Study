package racingcar;

import java.util.List;
import racingcar.common.context.ApplicationContext;
import racingcar.domain.RacingCar;
import racingcar.service.RacingGame;
import racingcar.service.result.RacingGameResult;
import racingcar.service.result.RacingGameRoundResult;
import racingcar.view.input.InputView;
import racingcar.view.output.OutputView;

public class Application {

  // ApplicationContext 에서 필요한 객체들을 가져온다.
  private static final RacingGame racingGame = ApplicationContext.getRacingGame();

  private static final InputView<List<String>> racingCarNamesInputView = ApplicationContext.getRacingCarNamesInputView();
  private static final InputView<Integer> racingGameRoundInputView = ApplicationContext.getRacingGameRoundInputView();

  private static final OutputView<RacingGameResult> racingGameResultOutputView = ApplicationContext.getRacingGameResultOutputView();
  private static final OutputView<RacingGameRoundResult> racingGameRoundResultOutputView = ApplicationContext.getRacingGameRoundResultOutputView();

  public static void main(String[] args) {

    // 필요한 정보들을 입력받는다.
    List<String> carNames = racingCarNamesInputView.input(); // 자동차 이름들을 입력받는다.
    int round = racingGameRoundInputView.input(); // 시도 횟수를 입력받는다.

    // RacingCar 이름들을 RacingCar 객체로 변환한다.
    List<RacingCar> racingCars = carNames.stream().map(RacingCar::of).toList();

    // 자동차 경주 게임을 수행한 후, 결과를 얻는다
    List<RacingGameRoundResult> racingGameRoundResults = racingGame.play(racingCars, round);

    // 경주 결과를 출력한다.
    racingGameRoundResults.forEach(racingGameRoundResultOutputView::print);

    // 최종 우승 결과를 얻는다.
    RacingGameResult racingGameResult = racingGame.getResult();

    // 최종 우승 RacingCar 들의 이름을 출력한다.
    racingGameResultOutputView.print(racingGameResult);
  }
}
