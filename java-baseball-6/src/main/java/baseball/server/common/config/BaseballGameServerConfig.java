package baseball.server.common.config;

import baseball.protocol.server.Server;
import baseball.protocol.server.ServerConfig;
import baseball.server.BaseballServer;
import baseball.server.controller.BaseballGameController;
import baseball.server.framework.Controller;
import baseball.server.service.BaseballGameService;
import baseball.server.service.game_policy.BaseballGamePolicy;
import baseball.server.service.game_policy.NormalBaseballGamePolicy;
import baseball.server.util.number_generator.NormalRandomNumberGenerator;
import baseball.server.util.number_generator.RandomNumberGenerator;
import java.util.Set;

/**
 * BaseballGameServer 의 설정을 관리하는 클래스
 */
public class BaseballGameServerConfig implements ServerConfig {

  // Singleton
  private static BaseballGameServerConfig instance;

  // 내부 DI
  private final BaseballGamePolicy baseballGamePolicy = new NormalBaseballGamePolicy();
  private final RandomNumberGenerator randomNumberGenerator = new NormalRandomNumberGenerator();
  private final BaseballGameService baseballGameService = new BaseballGameService(
      baseballGamePolicy,
      randomNumberGenerator
  );

  // Controller 들
  private final BaseballGameController baseBallGameController = new BaseballGameController(
      baseballGameService
  );

  // Server 정의
  private BaseballServer baseballServer;

  // Singleton
  private BaseballGameServerConfig() {
    initializeServer();
  }

  public static BaseballGameServerConfig getInstance() {
    if (instance == null) {
      instance = new BaseballGameServerConfig();
    }
    return instance;
  }

  /**
   * Server 를 초기화한다.
   */
  private void initializeServer() {
    Set<Controller> controllers = Set.of(baseBallGameController);
    baseballServer = new BaseballServer(controllers);
  }

  @Override
  public String getServerName() {
    return "BaseballServer";
  }

  @Override
  public Server getServer() {
    return baseballServer;
  }
}