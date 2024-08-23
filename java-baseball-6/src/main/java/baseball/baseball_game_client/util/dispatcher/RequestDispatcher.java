package baseball.baseball_game_client.util.dispatcher;

import baseball.protocol.name_server.NameServer;
import baseball.protocol.request.ClientRequest;
import baseball.protocol.response.ServerResponse;
import baseball.protocol.server.Server;

/**
 * 서버로 요청을 전달하는 클래스
 */
public class RequestDispatcher {

  private final NameServer nameServer;

  public RequestDispatcher() {
    this.nameServer = NameServer.getInstance();
  }

  /**
   * 데이터를 담아 서버로 요청을 전달한다.
   * <p>
   * 서버의 응답이 필요한 경우 사용한다.
   *
   * @param serverName 서버 이름
   * @param endPoint   엔드포인트
   * @param data       요청하는 데이터
   * @return 서버 응답
   */
  public ServerResponse<?> sendRequest(
      final String serverName,
      final String endPoint,
      final Object data) {

    Server server = getServer(serverName);
    return server.handleRequest(new ClientRequest<>(endPoint, data));
  }

  /**
   * 데이터 없이 서버로 요청을 전달한다.
   * <p>
   * 서버의 응답이 필요 없는 경우 사용한다.
   *
   * @param serverName 서버 이름
   * @param endPoint   엔드포인트
   */
  public void sendRequest(
      final String serverName,
      final String endPoint) {

    Server server = getServer(serverName);
    server.handleRequest(new ClientRequest<>(endPoint));
  }

  /**
   * 서버 이름에 해당하는 서버 객체를 반환한다
   *
   * @param serverName 서버 이름
   * @return 서버 객체
   */
  private Server getServer(String serverName) {
    return nameServer.getServer(serverName);
  }
}
