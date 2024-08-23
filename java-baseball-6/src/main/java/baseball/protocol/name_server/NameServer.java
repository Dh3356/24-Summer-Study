package baseball.protocol.name_server;

import static baseball.protocol.common.message.ErrorMessage.SERVER_NOT_EXIST_ERROR;

import baseball.protocol.server.Server;
import baseball.protocol.server.ServerConfig;
import baseball.server.common.config.BaseballGameServerConfig;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

/**
 * 서버 이름을 관리하는 클래스
 */
public class NameServer {

  // Singleton
  private static NameServer instance;

  private final HashMap<String, Server> servers = new HashMap<>(); // 서버 이름과 서버 객체를 매핑한다.
  private Set<ServerConfig> serverConfigs; // 서버 설정들을 관리한다.

  public NameServer() {
    initializeServerConfigs();
    initializeServers();
  }

  public static NameServer getInstance() {
    if (instance == null) {
      instance = new NameServer();
    }
    return instance;
  }

  /**
   * 서버 설정들을 초기화한다.
   * <p>
   * 추후 서버 설정이 추가되면 이 메서드에 추가하면 된다.
   */
  private void initializeServerConfigs() {
    serverConfigs = Set.of(BaseballGameServerConfig.getInstance());
  }

  /**
   * 서버 Map 을 초기화한다.
   */
  private void initializeServers() {
    serverConfigs.forEach(
        serverConfig -> servers.put(serverConfig.getServerName(), serverConfig.getServer()));
  }

  /**
   * 서버 이름에 해당하는 서버 객체를 반환한다.
   *
   * @param serverName 서버 이름
   * @return 서버 객체
   */
  public Server getServer(String serverName) {
    return Optional.ofNullable(servers.get(serverName))
        .orElseThrow(() -> new IllegalArgumentException(SERVER_NOT_EXIST_ERROR));
  }
}
