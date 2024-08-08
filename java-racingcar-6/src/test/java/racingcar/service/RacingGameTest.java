package racingcar.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.common.util.number_generator.RandomNumberGenerator;
import racingcar.domain.RacingCar;
import racingcar.policy.RacingGamePolicy;
import racingcar.policy.RacingGamePolicyImpl;
import racingcar.service.result.RacingGameResult;
import racingcar.service.result.RacingGameRoundResult;

@DisplayName("RacingGame 클래스 테스트")
public class RacingGameTest {

  private static RandomNumberGenerator randomNumberGenerator;
  private static RacingGamePolicy racingGamePolicy;
  private static RacingGame racingGame;

  @BeforeAll
  static void setUp() {
    randomNumberGenerator = mock(RandomNumberGenerator.class);
    racingGamePolicy = new RacingGamePolicyImpl(randomNumberGenerator);
    racingGame = new RacingGame(racingGamePolicy);
  }


  @DisplayName("play 시 RacingCar List 가 MIN_RACING_CAR_SIZE 보다 작으면 예외가 발생한다.")
  @Test
  void playWhenEmptyRacingCarList() {
    // given
    List<RacingCar> racingCars = new LinkedList<>();
    int round = 5;

    for (int i = 0; i < RacingGame.MIN_RACING_CAR_SIZE - 1; i++) {
      racingCars.add(RacingCar.of("car" + i));
    }

    // when & then
    assertThrows(IllegalArgumentException.class, () -> racingGame.play(racingCars, round));
  }

  @DisplayName("play 시 round 가 MIN_ROUND 보다 작으면 예외가 발생한다.")
  @Test
  void playWhenRoundIsLessThanMinRound() {
    // given
    List<RacingCar> racingCars = new LinkedList<>();
    int round = RacingGame.MIN_ROUND - 1;

    for (int i = 0; i < RacingGame.MIN_RACING_CAR_SIZE; i++) {
      racingCars.add(RacingCar.of("car" + i));
    }

    // when & then
    assertThrows(IllegalArgumentException.class, () -> racingGame.play(racingCars, round));
  }

  @DisplayName("play 후 올바른 RacingGameResult List 를 반환한다.")
  @Test
  void playAndReturnRacingGameResultList() {
    // given
    when(randomNumberGenerator.generateRandomNumber(anyInt(), anyInt()))
        // 1 라운드
        .thenReturn(1) // car0 -> 0
        .thenReturn(4) // car1 -> 1
        .thenReturn(4) // car2 -> 1

        // 2 라운드
        .thenReturn(4) // car0 -> 1
        .thenReturn(2) // car1 -> 1
        .thenReturn(4) // car2 -> 2

        // 3 라운드
        .thenReturn(1) // car0 -> 1
        .thenReturn(2) // car1 -> 1
        .thenReturn(4); // car2 -> 3

    List<RacingCar> racingCars = new LinkedList<>();
    int round = 3;

    for (int i = 0; i < 3; i++) {
      racingCars.add(RacingCar.of("car" + i));
    }

    // when
    List<RacingGameRoundResult> racingGameRoundResults = racingGame.play(racingCars, round);

    // then
    assertAll(
        () -> assertEquals(racingGameRoundResults.get(0).getResultMap().get("car0"), 0),
        () -> assertEquals(racingGameRoundResults.get(0).getResultMap().get("car1"), 1),
        () -> assertEquals(racingGameRoundResults.get(0).getResultMap().get("car2"), 1)
    );

    assertAll(
        () -> assertEquals(racingGameRoundResults.get(1).getResultMap().get("car0"), 1),
        () -> assertEquals(racingGameRoundResults.get(1).getResultMap().get("car1"), 1),
        () -> assertEquals(racingGameRoundResults.get(1).getResultMap().get("car2"), 2)
    );

    assertAll(
        () -> assertEquals(racingGameRoundResults.get(2).getResultMap().get("car0"), 1),
        () -> assertEquals(racingGameRoundResults.get(2).getResultMap().get("car1"), 1),
        () -> assertEquals(racingGameRoundResults.get(2).getResultMap().get("car2"), 3)
    );
  }

  @DisplayName("한 명이 이긴 게임의 올바른 게임 결과를 반환한다.")
  @Test
  void getResult() {
    // given
    RacingCar rc1 = RacingCar.of("pobi");
    RacingCar rc2 = RacingCar.of("crong");
    RacingCar rc3 = RacingCar.of("honux");

    rc1.move(1);
    rc2.move(2);
    rc3.move(3);

    // when
    List<RacingCar> racingCars = List.of(rc1, rc2, rc3);
    racingGame.play(racingCars, 1);
    RacingGameResult result = racingGame.getResult();

    // when & then
    assertEquals(result.getWinnerNames(), List.of("honux"));
  }

  @DisplayName("여러 명이 이긴 게임의 올바른 게임 결과를 반환한다.")
  @Test
  void getResultWhenMultipleWinners() {
    // given
    RacingCar rc1 = RacingCar.of("pobi");
    RacingCar rc2 = RacingCar.of("crong");
    RacingCar rc3 = RacingCar.of("honux");

    rc1.move(1);
    rc2.move(3);
    rc3.move(3);

    // when
    List<RacingCar> racingCars = List.of(rc1, rc2, rc3);
    racingGame.play(racingCars, 1);
    RacingGameResult result = racingGame.getResult();

    // when & then
    assertEquals(result.getWinnerNames(), List.of("crong", "honux"));
  }
}
