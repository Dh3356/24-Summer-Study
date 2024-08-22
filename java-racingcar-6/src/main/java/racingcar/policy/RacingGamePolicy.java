package racingcar.policy;

import java.util.List;
import racingcar.domain.RacingCar;


/**
 * 레이싱 게임 정책
 */
public interface RacingGamePolicy {

  /**
   * 이동 가능한지 확인
   *
   * @param racingCar 레이싱 자동차
   * @return 이동 가능 여부
   */
  boolean isMoveable(RacingCar racingCar);

  /**
   * 이동 거리
   *
   * @return 이동 거리
   */
  int getMovingDistance();

  /**
   * 우승자인지 확인
   *
   * @param racingCar 레이싱 자동차
   * @return 우승자 여부
   */
  boolean isWinner(RacingCar racingCar);

  /**
   * 우승자 목록
   *
   * @param racingCars 레이싱 자동차 목록
   * @return 우승자 목록
   */
  List<RacingCar> winners(List<RacingCar> racingCars);

  /**
   * 이동
   *
   * @param racingCar 레이싱 자동차
   */
  default void move(RacingCar racingCar) {
    if (isMoveable(racingCar)) {
      racingCar.move(getMovingDistance());
    }
  }
}
