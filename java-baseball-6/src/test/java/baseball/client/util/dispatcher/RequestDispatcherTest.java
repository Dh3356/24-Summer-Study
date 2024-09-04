package baseball.client.util.dispatcher;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import baseball.protocol.response.ServerResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RequestDispatcher 클래스 테스트")
public class RequestDispatcherTest {

  private final RequestDispatcher requestDispatcher = new RequestDispatcher();

  @DisplayName("sendRequest 메서드는 서버로 요청을 전달한다.")
  @Test
  void sendRequestTest() {
    // given
    String serverName = "BaseballServer";
    String endPoint = "restartGame";
    Object data = new Object();

    // when
    ServerResponse<?> response = requestDispatcher.sendRequest(serverName, endPoint, data);

    // then
    assertNotNull(response);
  }
}
