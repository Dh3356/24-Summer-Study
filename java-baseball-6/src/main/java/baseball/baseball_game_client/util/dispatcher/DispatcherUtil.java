package baseball.baseball_game_client.util.dispatcher;

import static baseball.baseball_game_client.util.dispatcher.Message.INVALID_DATA_TYPE_ERROR;
import static baseball.baseball_game_client.util.dispatcher.Message.NO_DATA_ERROR;

import baseball.protocol.response.ServerResponse;

/**
 * Dispatcher 와 관련된 유틸리티 클래스
 */
public class DispatcherUtil {

  /**
   * 서버 응답에서 원하는 타입의 데이터를 추출한다.
   *
   * @param response 서버 응답
   * @param clazz    데이터 타입
   * @param <T>      데이터 타입
   * @return 데이터
   */
  public static <T> T getDataAsType(final ServerResponse<?> response, final Class<T> clazz) {
    Object data = response.getData();

    validate(data, clazz);

    return clazz.cast(data);
  }

  /**
   * 데이터와 타입을 검증한다.
   *
   * @param data  데이터
   * @param clazz 타입
   */
  private static void validate(final Object data, final Class<?> clazz) {
    validateData(data);
    validateType(data, clazz);
  }

  /**
   * 데이터를 검증한다.
   *
   * @param data 데이터
   */
  private static void validateData(final Object data) {
    if (data == null) {
      throw new IllegalStateException(NO_DATA_ERROR);
    }
  }

  /**
   * 타입을 검증한다.
   *
   * @param data  데이터
   * @param clazz 타입
   */
  private static void validateType(final Object data, final Class<?> clazz) {
    if (!clazz.isInstance(data)) {
      throw new ClassCastException(INVALID_DATA_TYPE_ERROR);
    }
  }
}
