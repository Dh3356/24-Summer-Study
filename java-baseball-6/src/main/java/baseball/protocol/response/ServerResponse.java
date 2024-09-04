package baseball.protocol.response;

/**
 * 서버 응답을 나타내는 클래스
 *
 * @param <T> 응답하는 데이터의 타입
 */
public class ServerResponse<T> {

  private final T data; // 응답하는 데이터

  /**
   * 데이터를 포함하는 응답을 생성한다.
   *
   * @param data 응답하는 데이터
   */
  public ServerResponse(final T data) {
    this.data = data;
  }

  /**
   * 데이터를 포함하지 않는 응답을 생성한다.
   */
  public ServerResponse() {
    this.data = null;
  }

  public T getData() {
    return data;
  }
}
