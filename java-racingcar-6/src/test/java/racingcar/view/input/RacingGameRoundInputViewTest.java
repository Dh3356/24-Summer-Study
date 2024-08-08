package racingcar.view.input;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.ConsoleTest;

@DisplayName("RaceCarNamesInputView 클래스 테스트")
public class RacingGameRoundInputViewTest extends ConsoleTest {

  private static final RacingGameRoundInputView racingGameRoundInputView = new RacingGameRoundInputView();

  @DisplayName("문구를 출력한다.")
  @Test
  void printBeforeInput() {

    // when & then
    assertSimpleTest(
        () -> {
          racingGameRoundInputView.printBeforeInput();
          assertThat(output()).isEqualTo("시도할 회수는 몇 회인가요?");
        }
    );
  }

  @DisplayName("입력받은 시도 횟수를 반환한다.")
  @Test
  void input() {
    // given
    command("5");

    // when & then
    assertThat(racingGameRoundInputView.input()).isEqualTo(5);
  }
}
