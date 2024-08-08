package racingcar.view.input;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.ConsoleTest;

@DisplayName("RaceCarNamesInputView 클래스 테스트")
public class RacingCarNamesInputViewTest extends ConsoleTest {

  private static final RacingCarNamesInputView racingCarNamesInputView = new RacingCarNamesInputView();

  @DisplayName("문구를 출력한다.")
  @Test
  void printBeforeInput() {

    // when & then
    assertSimpleTest(
        () -> {
          racingCarNamesInputView.printBeforeInput();
          assertThat(output()).isEqualTo("경주할 자동차 이름을 입력하세요(이름은 쉼표(,) 기준으로 구분)");
        }
    );
  }

  @DisplayName("입력받은 자동차 이름을 ',' 를 기준으로 split 하여 List 로 반환한다.")
  @Test
  void input() {
    // given
    command("pobi,crong,honux", "5");

    // when & then
    assertThat(racingCarNamesInputView.input()).containsExactly("pobi", "crong", "honux");
  }
}
