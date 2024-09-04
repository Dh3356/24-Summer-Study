package baseball.client;

import baseball.client.common.enums.RetryState;
import baseball.client.util.dispatcher.DispatcherUtil;
import baseball.client.util.dispatcher.RequestDispatcher;
import baseball.client.view.input.BaseballGameRetryInputView;
import baseball.client.view.input.BaseballGameRoundInputView;
import baseball.client.view.output.BaseballGameOverOutputView;
import baseball.client.view.output.BaseballGameRoundOutputResultView;
import baseball.client.view.output.BaseballGameStartOutputView;
import baseball.dto.request.BaseballGameRoundRequest;
import baseball.dto.response.BaseballGameRoundResponse;
import baseball.protocol.client.Client;
import java.util.List;

/**
 * 숫자 야구 게임 클라이언트.
 * <p>
 * 사용자 상호작용을 담당한다.
 */
public class BaseballGameClient implements Client {

  // 입/출력 뷰
  private final BaseballGameStartOutputView baseballGameStartOutputView = new BaseballGameStartOutputView();
  private final BaseballGameRoundInputView baseballGameRoundInputView = new BaseballGameRoundInputView();
  private final BaseballGameRoundOutputResultView baseballGameRoundOutputResultView = new BaseballGameRoundOutputResultView();
  private final BaseballGameOverOutputView baseballGameOverOutputView = new BaseballGameOverOutputView();
  private final BaseballGameRetryInputView baseballGameRetryInputView = new BaseballGameRetryInputView();

  // 서버와 통신을 위한 Protocol 에 사용할 변수들
  private final RequestDispatcher requestDispatcher = new RequestDispatcher(); // RequestDispatcher 를 사용하여 서버와 통신
  private final String baseballServerName = "BaseballServer"; // 서버 이름

  /**
   * 게임 루프를 시작한다.
   */
  private void startGameLoop() {
    while (true) {
      List<Integer> input = baseballGameRoundInputView.input();
      BaseballGameRoundRequest request = new BaseballGameRoundRequest(input);

      BaseballGameRoundResponse response = playRound(request);
      baseballGameRoundOutputResultView.print(response);

      if (response.isWin()) {
        handleGameOver();
        if (shouldEndGame()) {
          break;
        }
        requestDispatcher.sendRequest(baseballServerName, "restartGame");
      }
    }
  }

  /**
   * 라운드를 진행한다.
   *
   * @param baseballGameRoundRequest 라운드 요청
   * @return 라운드 응답
   */
  private BaseballGameRoundResponse playRound(
      final BaseballGameRoundRequest baseballGameRoundRequest) {

    return DispatcherUtil.getDataAsType(
        requestDispatcher.sendRequest(
            baseballServerName,
            "playRound",
            baseballGameRoundRequest
        ),
        BaseballGameRoundResponse.class);
  }

  /**
   * 게임 오버를 처리한다.
   */
  private void handleGameOver() {
    baseballGameOverOutputView.print();
  }

  /**
   * 게임을 종료해야 하는지 여부를 반환한다.
   *
   * @return 게임을 종료해야 하는지 여부
   */
  private boolean shouldEndGame() {
    RetryState retryState = RetryState.of(baseballGameRetryInputView.input());
    return retryState == RetryState.END;
  }

  /**
   * 클라이언트를 실행한다.
   */
  @Override
  public void run() {
    baseballGameStartOutputView.print();
    startGameLoop();
  }
}
