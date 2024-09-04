package baseball.protocol.request;

/**
 * 클라이언트 요청을 나타내는 클래스
 *
 * @param <T> 요청하는 데이터의 타입
 */
public class ClientRequest<T> {

  private final String endPoint; // 요청하는 서버의 엔드포인트
  private final T data; // 요청하는 데이터

  public ClientRequest(final String endPoint, final T data) {
    this.endPoint = endPoint;
    this.data = data;
  }

  public ClientRequest(final String endPoint) {
    this.endPoint = endPoint;
    this.data = null;
  }

  public String getEndPoint() {
    return endPoint;
  }

  public T getData() {
    return data;
  }
}
