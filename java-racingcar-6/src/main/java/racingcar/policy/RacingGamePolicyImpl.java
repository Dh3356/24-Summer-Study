package racingcar.policy;

import static racingcar.common.ErrorMessage.CANNOT_GET_MAX_POSITION_ERROR;

import java.util.List;
import racingcar.common.util.number_generator.RandomNumberGenerator;
import racingcar.domain.RacingCar;

public class RacingGamePolicyImpl implements RacingGamePolicy {

  public final int movingCondition = 4; // 이동 조건
  private final int moveMin = 0; // 이동 최소값
  private final int moveMax = 9; // 이동 최대값
  private final RandomNumberGenerator randomNumberGenerator; // 랜덤 숫자 생성기
  private int maxPosition; // 최대 위치

  public RacingGamePolicyImpl(RandomNumberGenerator randomNumberGenerator) {
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public boolean isMoveable(RacingCar racingCar) {
    return randomNumberGenerator.generateRandomNumber(moveMin, moveMax) >= movingCondition;
  }

  @Override
  public int getMovingDistance() {
    return 1;
  }

  @Override
  public boolean isWinner(RacingCar racingCar) {
    return racingCar.getPosition() == maxPosition;
  }

  @Override
  public List<RacingCar> winners(List<RacingCar> racingCars) {
    setMaxPosition(racingCars);

    return racingCars.stream()
        .filter(this::isWinner)
        .toList();
  }

  /**
   * 최대 위치를 설정한다. 최대 위치는 레이싱 자동차 목록에서 가장 큰 위치이다.
   *
   * @param racingCars 레이싱 자동차 목록
   */
  private void setMaxPosition(List<RacingCar> racingCars) {

    maxPosition = racingCars.stream()
        .mapToInt(RacingCar::getPosition)
        .max()
        .orElseThrow(() ->
            new IllegalStateException(CANNOT_GET_MAX_POSITION_ERROR)
        );

  }
}
