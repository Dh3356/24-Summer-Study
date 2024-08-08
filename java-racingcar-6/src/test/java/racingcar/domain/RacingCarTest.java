package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("RacingCar 클래스 테스트")
public class RacingCarTest {

  private static Stream<String> provideInvalidName() {
    return Stream.of(
        "a".repeat(RacingCar.NAME_MAX_LENGTH + 1),
        "a".repeat(RacingCar.NAME_MIN_LENGTH - 1),
        " ",
        "",
        null
    );
  }

  private static Stream<String> provideName() {
    return Stream.of(
        "abc",
        "pobid",
        "pobi"
    );
  }

  private static Stream<Integer> provideDistance() {
    return Stream.of(1, 2, 3, 4, 5);
  }

  @DisplayName("name 이 올바르지 않은 경우 예외가 발생한다.")
  @ParameterizedTest(name = "name: {0}")
  @MethodSource("provideInvalidName")
  void createRacingCarByInvalidName(String name) {

    // when & then
    assertThatThrownBy(() -> RacingCar.of(name))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("name 이 올바르다면 RacingCar 객체를 생성한다.")
  @ParameterizedTest(name = "name: {0}")
  @MethodSource("provideName")
  void createRacingCarByValidName(String name) {

    // when & then
    assertDoesNotThrow(() -> RacingCar.of(name));
  }

  @DisplayName("move 를 통해 자동차가 이동한다.")
  @ParameterizedTest(name = "distance: {0}")
  @MethodSource("provideDistance")
  void move(int distance) {
    // given
    RacingCar racingCar = RacingCar.of("pobi");

    // when
    racingCar.move(distance);

    // then
    assertThat(racingCar.getPosition()).isEqualTo(distance);
  }
}
