package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.ConsoleTest;
import racingcar.service.result.RacingGameResult;

@DisplayName("RacingGameResultOutputView 클래스 테스트")
public class RacingGameResultOutputViewTest extends ConsoleTest {

  private final RacingGameResultOutputView racingGameResultOutputView = new RacingGameResultOutputView();

  @DisplayName("print 메서드 테스트")
  @Test
  void print() {
    // given
    List<String> winnerNames = List.of("pobi", "crong");
    RacingGameResult racingGameResult = new RacingGameResult(winnerNames);

    // when
    racingGameResultOutputView.print(racingGameResult);

    // then
    assertThat(output()).isEqualTo("최종 우승자 : pobi, crong");
  }
}
