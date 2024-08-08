package racingcar.service.result;

import java.util.HashMap;
import java.util.List;
import racingcar.domain.RacingCar;

/**
 * 레이싱 게임 라운드 결과
 */
public class RacingGameRoundResult {

  private final HashMap<String, Integer> resultMap; // 레이싱 자동차 이름과 위치 Map

  private RacingGameRoundResult(final HashMap<String, Integer> resultMap) {
    this.resultMap = resultMap;
  }

  public static RacingGameRoundResult from(List<RacingCar> racingCars) {

    HashMap<String, Integer> racingCarRoundResult = new HashMap<>();

    racingCars.forEach(
        racingCar -> racingCarRoundResult.put(racingCar.getName(), racingCar.getPosition())
    );

    return new RacingGameRoundResult(racingCarRoundResult);
  }

  public HashMap<String, Integer> getResultMap() {
    return resultMap;
  }
}
