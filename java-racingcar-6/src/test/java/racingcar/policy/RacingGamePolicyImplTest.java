package racingcar.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.common.util.number_generator.RandomNumberGenerator;
import racingcar.domain.RacingCar;

@DisplayName("RacingGamePolicyImpl 클래스 테스트")
public class RacingGamePolicyImplTest {

  private static RandomNumberGenerator randomNumberGenerator;
  private static RacingGamePolicyImpl racingGamePolicyImpl;

  private static Stream<Integer> provideMoveableRandomNumbers() {
    return Stream.of(
        racingGamePolicyImpl.movingCondition,
        racingGamePolicyImpl.movingCondition + 1,
        racingGamePolicyImpl.movingCondition + 2
    );
  }

  private static Stream<Integer> provideUnMoveableRandomNumbers() {
    return Stream.of(
        racingGamePolicyImpl.movingCondition - 1,
        racingGamePolicyImpl.movingCondition - 2,
        racingGamePolicyImpl.movingCondition - 3
    );
  }

  @BeforeAll
  static void setUp() {
    randomNumberGenerator = mock(RandomNumberGenerator.class);
    racingGamePolicyImpl = new RacingGamePolicyImpl(randomNumberGenerator);
  }

  @DisplayName("movingCondition 을 충족하면 통해 자동차가 이동한다.")
  @ParameterizedTest(name = "random number: {0}")
  @MethodSource("provideMoveableRandomNumbers")
  void moveTest(int randomNumber) {
    // given
    when(randomNumberGenerator.generateRandomNumber(anyInt(), anyInt())).thenReturn(randomNumber);
    RacingCar racingCar = RacingCar.of("pobi");

    // when
    racingGamePolicyImpl.move(racingCar);

    // then
    assertEquals(racingCar.getPosition(), racingGamePolicyImpl.getMovingDistance());
  }

  @DisplayName("movingCondition 을 충족하지 않으면 자동차가 이동하지 않는다.")
  @ParameterizedTest(name = "random number: {0}")
  @MethodSource("provideUnMoveableRandomNumbers")
  void unMoveTest(int randomNumber) {
    // given
    when(randomNumberGenerator.generateRandomNumber(anyInt(), anyInt())).thenReturn(randomNumber);
    RacingCar racingCar = RacingCar.of("pobi");

    // when
    racingGamePolicyImpl.move(racingCar);

    // then
    assertEquals(racingCar.getPosition(), 0);
  }

  @DisplayName("가장 멀리 간 자동차가 우승한다.")
  @Test
  void winnersTest() {
    // given
    RacingCar rc1 = RacingCar.of("pobi");
    RacingCar rc2 = RacingCar.of("crong");
    RacingCar rc3 = RacingCar.of("honux");

    rc1.move(1);
    rc2.move(2);
    rc3.move(3);

    // when
    List<RacingCar> winners = racingGamePolicyImpl.winners(List.of(rc1, rc2, rc3));

    // then
    assertThat(winners).containsExactly(rc3);
  }

  @DisplayName("가장 멀리 간 자동차가 여러 대일 경우 모두 우승한다.")
  @Test
  void winnersTest2() {
    // given
    RacingCar rc1 = RacingCar.of("pobi");
    RacingCar rc2 = RacingCar.of("crong");
    RacingCar rc3 = RacingCar.of("honux");

    rc1.move(1);
    rc2.move(3);
    rc3.move(3);

    // when
    List<RacingCar> winners = racingGamePolicyImpl.winners(List.of(rc1, rc2, rc3));

    // then
    assertThat(winners).containsExactly(rc2, rc3);
  }
}
