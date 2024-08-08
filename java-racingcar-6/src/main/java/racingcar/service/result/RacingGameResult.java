package racingcar.service.result;

import java.util.Collection;
import java.util.List;

/**
 * 레이싱 게임 결과
 */
public class RacingGameResult {

  private final List<String> winnerNames; // 우승자 이름 목록

  public RacingGameResult(Collection<String> winnerNames) {
    this.winnerNames = winnerNames.stream().toList();
  }

  public List<String> getWinnerNames() {
    return winnerNames;
  }
}
