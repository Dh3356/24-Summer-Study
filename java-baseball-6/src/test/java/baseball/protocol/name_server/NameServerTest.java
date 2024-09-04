package baseball.protocol.name_server;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import baseball.protocol.server.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("NameServer 클래스 테스트")
public class NameServerTest {

  private static final NameServer nameServer = NameServer.getInstance();

  @DisplayName("존재하지 않는 서버 이름을 입력하면 IllegalArgumentException 을 발생한다.")
  @Test
  void getServerExceptionTest() {
    // given
    String nonExistentServerName = "non-existent-server";

    // when & then
    assertThrows(IllegalArgumentException.class, () -> nameServer.getServer(nonExistentServerName));
  }

  @DisplayName("서버 이름을 입력하면 해당 서버 객체를 반환한다.")
  @Test
  void getServerTest() {
    // given
    String serverName = "BaseballServer";

    // when
    Server server = nameServer.getServer(serverName);

    // then
    assertNotNull(server);
  }
}
