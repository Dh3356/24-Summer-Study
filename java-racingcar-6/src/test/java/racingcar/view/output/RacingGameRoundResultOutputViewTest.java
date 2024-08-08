package racingcar.view.output;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.ConsoleTest;
import racingcar.domain.RacingCar;
import racingcar.service.result.RacingGameRoundResult;

@DisplayName("RacingGameRoundResultOutputView 클래스 테스트")
public class RacingGameRoundResultOutputViewTest extends ConsoleTest {

  private final RacingGameRoundResultOutputView racingGameRoundResultOutputView = new RacingGameRoundResultOutputView();

  @DisplayName("print 메서드 테스트")
  @Test
  void print() {
    // given
    RacingCar pobi = RacingCar.of("pobi");
    RacingCar crong = RacingCar.of("crong");
    RacingCar honux = RacingCar.of("honux");

    pobi.move(3);
    crong.move(1);
    honux.move(1);

    List<RacingCar> racingCars = List.of(pobi, crong, honux);

    RacingGameRoundResult racingGameRoundResult = RacingGameRoundResult.from(racingCars);

    // when
    racingGameRoundResultOutputView.print(racingGameRoundResult);

    // then
    assertAll(
        () -> assertThat(output()).contains("pobi : ---"),
        () -> assertThat(output()).contains("crong : -"),
        () -> assertThat(output()).contains("honux : -")
    );
  }
}
