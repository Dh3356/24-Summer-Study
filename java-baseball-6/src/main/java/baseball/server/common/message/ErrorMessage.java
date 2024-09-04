package baseball.server.common.message;

/**
 * 서버에서 사용하는 에러 메시지를 나타내는 클래스
 */
public class ErrorMessage {

  public static final String OUT_OF_INDEX_ERROR = "인덱스가 범위를 벗어났습니다.";
  public static final String INVALID_NUMBERS_COUNT_ERROR = "숫자의 개수가 올바르지 않습니다.";
  public static final String INVALID_NUMBERS_CONFLICTS_ERROR = "숫자가 중복되어 있습니다.";
  public static final String INVALID_NUMBER_BOUND_ERROR = "숫자의 범위가 올바르지 않습니다.";
  public static final String INVALID_MIN_MAX_ERROR = "최소값은 최대값보다 작거나 같아야 합니다.";
  public static final String INVALID_SIZE_ERROR = "사이즈는 최대값과 최소값의 차이보다 작거나 같아야 합니다.";
  public static final String INVALID_ENDPOINT_ERROR = "EndPoint 가 올바르지 않습니다.";
}
