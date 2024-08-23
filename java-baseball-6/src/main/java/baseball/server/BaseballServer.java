package baseball.server;

import baseball.protocol.request.ClientRequest;
import baseball.protocol.response.ServerResponse;
import baseball.protocol.server.Server;
import baseball.server.framework.Controller;
import baseball.server.framework.ControllerMethodMapper;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * 야구 게임 서버
 */
public class BaseballServer implements Server {

  private final Map<String, Function<ClientRequest<?>, ServerResponse<?>>> methodMap;

  /**
   * 생성자
   * <p>
   * 컨트롤러 목록을 주입받아 메서드 맵을 생성한다.
   *
   * @param controllers 컨트롤러 목록
   */
  public BaseballServer(Set<Controller> controllers) {
    this.methodMap = ControllerMethodMapper.mapMethods(controllers);
  }

  @Override
  public Map<String, Function<ClientRequest<?>, ServerResponse<?>>> methodMap() {
    return methodMap;
  }
}
