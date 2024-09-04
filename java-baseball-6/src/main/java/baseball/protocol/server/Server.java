package baseball.protocol.server;

import static baseball.server.common.message.ErrorMessage.INVALID_ENDPOINT_ERROR;

import baseball.protocol.request.ClientRequest;
import baseball.protocol.response.ServerResponse;
import java.util.Map;
import java.util.function.Function;

/**
 * 서버를 나타내는 인터페이스
 */
public interface Server {

  /**
   * 클라이언트 요청을 처리한다.
   *
   * @param request 클라이언트 요청
   * @return 서버 응답
   */
  default ServerResponse<?> handleRequest(final ClientRequest<?> request) {
    validateRequest(request);
    return methodMap().get(request.getEndPoint()).apply(request);
  }

  /**
   * 클라이언트 요청이 유효한지 검증한다.
   *
   * @param request 클라이언트 요청
   */
  private void validateRequest(final ClientRequest<?> request) {
    validateEndpoint(request.getEndPoint());
  }

  /**
   * 엔드포인트가 유효한지 검증한다.
   *
   * @param endpoint 엔드포인트
   */
  private void validateEndpoint(final String endpoint) {
    if (!methodMap().containsKey(endpoint)) {
      throw new IllegalArgumentException(INVALID_ENDPOINT_ERROR);
    }
  }

  /**
   * 클라이언트 요청을 처리하는 메서드 맵을 반환한다.
   * <p>
   * key 는 EndPoint, value 는 클라이언트 요청을 처리하는 메서드이다.
   *
   * @return 클라이언트 요청을 처리하는 메서드 맵
   */
  Map<String, Function<ClientRequest<?>, ServerResponse<?>>> methodMap();
}
