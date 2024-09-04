package baseball.dto.request;

import java.util.ArrayList;
import java.util.List;

/**
 * 숫자 야구 게임 라운드 요청 DTO
 */
public class BaseballGameRoundRequest {

  // ArrayList 를 사용해 Strike, Ball 을 효율적으로 계산할 수 있다.
  private final ArrayList<Integer> numbers;

  public BaseballGameRoundRequest(final List<Integer> numbers) {
    this.numbers = new ArrayList<>(numbers);
  }

  public ArrayList<Integer> getArrayListNumbers() {
    return numbers;
  }
}
