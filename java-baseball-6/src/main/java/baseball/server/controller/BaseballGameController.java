package baseball.server.controller;

import baseball.dto.request.BaseballGameRoundRequest;
import baseball.dto.response.BaseballGameRoundResponse;
import baseball.protocol.response.ServerResponse;
import baseball.server.framework.ClientEndpoint;
import baseball.server.framework.Controller;
import baseball.server.service.BaseballGameService;

/**
 * 숫자 야구 게임을 제어하는 컨트롤러
 */
public class BaseballGameController extends Controller {

  private final BaseballGameService baseballGameService;

  public BaseballGameController(BaseballGameService baseballGameService) {
    this.baseballGameService = baseballGameService;
  }

  @ClientEndpoint(endPoint = "restartGame")
  public ServerResponse<Void> restartGame() {
    baseballGameService.reStart();
    return new ServerResponse<>();
  }

  @ClientEndpoint(endPoint = "playRound")
  public ServerResponse<BaseballGameRoundResponse> playRound(
      final BaseballGameRoundRequest request) {

    return new ServerResponse<>(baseballGameService.playRound(request));
  }
}
