package racingcar.common.context;

import java.util.List;
import racingcar.common.util.number_generator.RandomNumberGenerator;
import racingcar.policy.RacingGamePolicyImpl;
import racingcar.service.RacingGame;
import racingcar.service.result.RacingGameResult;
import racingcar.service.result.RacingGameRoundResult;
import racingcar.view.input.InputView;
import racingcar.view.input.RacingCarNamesInputView;
import racingcar.view.input.RacingGameRoundInputView;
import racingcar.view.output.OutputView;
import racingcar.view.output.RacingGameResultOutputView;
import racingcar.view.output.RacingGameRoundResultOutputView;

/**
 * 애플리케이션 설정
 */
public class ApplicationContext {

  private static final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
  private static final RacingGamePolicyImpl racingGamePolicyImpl = new RacingGamePolicyImpl(
      randomNumberGenerator);
  private static final RacingGame racingGame = new RacingGame(racingGamePolicyImpl);
  private static final RacingCarNamesInputView racingCarNamesInputView = new RacingCarNamesInputView();
  private static final RacingGameRoundInputView racingGameRoundInputView = new RacingGameRoundInputView();
  private static final RacingGameResultOutputView racingGameResultOutputView = new RacingGameResultOutputView();
  private static final RacingGameRoundResultOutputView racingGameRoundResultOutputView = new RacingGameRoundResultOutputView();

  public static RacingGame getRacingGame() {
    return racingGame;
  }

  public static InputView<List<String>> getRacingCarNamesInputView() {
    return racingCarNamesInputView;
  }

  public static InputView<Integer> getRacingGameRoundInputView() {
    return racingGameRoundInputView;
  }

  public static OutputView<RacingGameResult> getRacingGameResultOutputView() {
    return racingGameResultOutputView;
  }

  public static OutputView<RacingGameRoundResult> getRacingGameRoundResultOutputView() {
    return racingGameRoundResultOutputView;
  }
}
