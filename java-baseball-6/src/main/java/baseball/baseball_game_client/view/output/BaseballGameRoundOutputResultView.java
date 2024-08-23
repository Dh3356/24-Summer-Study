package baseball.baseball_game_client.view.output;

import static baseball.baseball_game_client.view.output.message.OutputMessage.BALL;
import static baseball.baseball_game_client.view.output.message.OutputMessage.NOTHING;
import static baseball.baseball_game_client.view.output.message.OutputMessage.STRIKE;

import baseball.dto.response.BaseballGameRoundResponse;
import baseball.server.domain.Counts;

/**
 * 숫자 야구 게임 라운드 결과 출력 뷰
 */
public class BaseballGameRoundOutputResultView implements
    OutputResultView<BaseballGameRoundResponse> {

  /**
   * 숫자 야구 게임 라운드 결과를 출력한다.
   *
   * @param baseballGameRoundResponse 출력할 라운드 결과
   */
  @Override
  public void print(BaseballGameRoundResponse baseballGameRoundResponse) {
    StringBuilder output = new StringBuilder();

    Counts counts = baseballGameRoundResponse.getCounts();

    appendBallCount(output, counts.ballCount());
    appendStrikeCount(output, counts.strikeCount());
    appendNothing(output);

    System.out.println(output.toString().trim());
  }

  private void appendBallCount(StringBuilder output, Integer ballCount) {
    if (ballCount > 0) {
      output.append(ballCount).append(BALL).append(" ");
    }
  }

  private void appendStrikeCount(StringBuilder output, int strikeCount) {
    if (strikeCount > 0) {
      output.append(strikeCount).append(STRIKE).append(" ");
    }
  }

  private void appendNothing(StringBuilder output) {
    if (output.isEmpty()) {
      output.append(NOTHING);
    }
  }
}
