package racingcar.common;

import racingcar.domain.RacingCar;
import racingcar.service.RacingGame;

public class ErrorMessage {

  public static final String CAR_NAME_EMPTY_ERROR =
      "이름이 비어있습니다.";

  public static final String CAR_NAME_TOO_SHORT_ERROR =
      "이름은 " + RacingCar.NAME_MIN_LENGTH + "자 이상이어야 합니다.";

  public static final String CAR_NAME_TOO_LONG_ERROR =
      "이름은 " + RacingCar.NAME_MAX_LENGTH + "자 이하이어야 합니다.";

  public static final String RACING_CAR_SIZE_TOO_SHORT_ERROR =
      "자동차는 최소 " + RacingGame.MIN_RACING_CAR_SIZE + "대 이상이어야 합니다.";

  public static final String ROUND_TOO_SHORT_ERROR =
      "라운드는 최소 " + RacingGame.MIN_ROUND + "이상이어야 합니다.";

  public static final String CANNOT_GET_MAX_POSITION_ERROR = "최대 위치를 구할 수 없습니다.";

  public static final String INVALID_INPUT_ERROR = "잘못된 입력입니다.";
}
