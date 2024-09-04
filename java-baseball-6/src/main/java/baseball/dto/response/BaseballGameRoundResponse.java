package baseball.dto.response;

import baseball.server.domain.Counts;

/**
 * 숫자 야구 게임 라운드 응답 DTO
 */
public class BaseballGameRoundResponse {

  private final Counts counts;
  private final boolean isWin;

  public BaseballGameRoundResponse(
      final Counts counts,
      final boolean isWin) {

    this.counts = counts;
    this.isWin = isWin;
  }

  public Counts getCounts() {
    return counts;
  }

  public boolean isWin() {
    return isWin;
  }
}
