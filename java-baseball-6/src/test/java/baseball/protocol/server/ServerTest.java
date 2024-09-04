package baseball.protocol.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import baseball.protocol.request.ClientRequest;
import baseball.protocol.response.ServerResponse;
import java.util.Map;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Server 인터페이스 테스트")
public class ServerTest {

  private static final Server server = new Server() {
    @Override
    public Map<String, Function<ClientRequest<?>, ServerResponse<?>>> methodMap() {
      return Map.of("helloEndpoint", request -> new ServerResponse<>("hello"));
    }
  };

  @DisplayName("EndPoint 가 유효하지 않으면 IllegalArgumentException 을 발생한다.")
  @Test
  void handleRequestInvalidEndPointTest() {
    // given
    ClientRequest<String> request = new ClientRequest<>("invalidEndPoint", "data");

    // when & then
    assertThrows(IllegalArgumentException.class, () -> server.handleRequest(request));
  }

  @DisplayName("EndPoint 가 유효하면 매핑되어있는 메서드를 수행해 ServerResponse 를 반환한다.")
  @Test
  void handleRequestTest() {
    // given
    ClientRequest<String> request = new ClientRequest<>("helloEndpoint");

    // when
    ServerResponse<?> response = server.handleRequest(request);

    // then
    assertEquals("hello", response.getData());
  }
}
