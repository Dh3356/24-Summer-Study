package racingcar.service;

import static racingcar.common.ErrorMessage.RACING_CAR_SIZE_TOO_SHORT_ERROR;
import static racingcar.common.ErrorMessage.ROUND_TOO_SHORT_ERROR;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import racingcar.domain.RacingCar;
import racingcar.policy.RacingGamePolicy;
import racingcar.service.result.RacingGameResult;
import racingcar.service.result.RacingGameRoundResult;

/**
 * 레이싱 게임
 */
public class RacingGame {

  public static final int MIN_RACING_CAR_SIZE = 1; // 최소 레이싱 자동차 수
  public static final int MIN_ROUND = 1; // 최소 라운드 수
  private final RacingGamePolicy racingGamePolicy; // 레이싱 게임 정책
  private List<RacingCar> racingCars; // 레이싱 자동차 목록

  /**
   * 생성자
   *
   * @param racingGamePolicy 레이싱 게임 정책
   */
  public RacingGame(final RacingGamePolicy racingGamePolicy) {
    this.racingGamePolicy = racingGamePolicy;
  }

  /**
   * 레이싱 자동차 목록을 설정한다.
   *
   * @param racingCars 레이싱 자동차 목록
   */
  private void setRacingCars(List<RacingCar> racingCars) {
    this.racingCars = racingCars;
  }

  /**
   * 레이싱 게임을 진행한다.
   *
   * @param racingCars 레이싱 자동차 목록
   * @param round      라운드
   * @return 레이싱 게임 라운드 결과 목록
   */
  public List<RacingGameRoundResult> play(final List<RacingCar> racingCars,
      final int round) {

    validate(racingCars, round); // 검증

    setRacingCars(racingCars); // 레이싱 자동차 목록 설정

    // 레이싱 게임 라운드 결과 목록
    List<RacingGameRoundResult> racingGameRoundResults = new LinkedList<>();

    // 라운드 수 만큼 레이싱 게임 라운드를 진행한 후 결과를 목록에 추가한다.
    IntStream.range(0, round).forEach(i -> {
      racingGameRoundResults.add(playRacingRound());
    });

    // 레이싱 게임 라운드 결과 목록 반환
    return racingGameRoundResults;
  }

  /**
   * 레이싱 게임 결과를 반환한다.
   *
   * @return 레이싱 게임 결과
   */
  public RacingGameResult getResult() {
    List<String> winnerNames = racingGamePolicy.winners(racingCars).stream()
        .map(RacingCar::getName).toList();

    return new RacingGameResult(winnerNames);
  }

  /**
   * 레이싱 라운드를 진행한다.
   *
   * @return 레이싱 게임 라운드 결과
   */
  private RacingGameRoundResult playRacingRound() {
    racingCars.forEach(racingGamePolicy::move);

    return RacingGameRoundResult.from(racingCars);
  }

  /**
   * 레이싱 자동차 목록과 라운드를 검증한다.
   *
   * @param racingCars 레이싱 자동차 목록
   * @param round      라운드
   */
  private void validate(List<RacingCar> racingCars, int round) {
    validateRacingCarSize(racingCars);
    validateRoundRange(round);
  }

  /**
   * 레이싱 자동차 목록의 크기를 검증한다.
   *
   * @param racingCars 레이싱 자동차 목록
   */
  private void validateRacingCarSize(List<RacingCar> racingCars) {
    if (racingCars.size() < MIN_RACING_CAR_SIZE) {
      throw new IllegalArgumentException(RACING_CAR_SIZE_TOO_SHORT_ERROR);
    }
  }

  /**
   * 라운드 범위를 검증한다.
   *
   * @param round 라운드
   */
  private void validateRoundRange(int round) {
    if (round < MIN_ROUND) {
      throw new IllegalArgumentException(ROUND_TOO_SHORT_ERROR);
    }
  }
}
