package baseball.server.service;

import baseball.dto.request.BaseballGameRoundRequest;
import baseball.dto.response.BaseballGameRoundResponse;
import baseball.server.domain.Counts;
import baseball.server.domain.Numbers;
import baseball.server.service.game_policy.BaseballGamePolicy;
import baseball.server.util.number_generator.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 * 숫자 야구 게임 서비스
 */
public class BaseballGameService {

  private final RandomNumberGenerator randomNumberGenerator; // 랜덤 숫자 생성기
  private final BaseballGamePolicy baseballGamePolicy; // 야구 게임 정책
  private Numbers numbers; // 정답 숫자

  /**
   * 생성자
   * <p>
   * 랜덤 숫자 생성기와 야구 게임 정책을 주입받는다.
   *
   * @param baseballGamePolicy    야구 게임 정책
   * @param randomNumberGenerator 랜덤 숫자 생성기
   */
  public BaseballGameService(
      final BaseballGamePolicy baseballGamePolicy,
      final RandomNumberGenerator randomNumberGenerator) {

    this.randomNumberGenerator = randomNumberGenerator;
    this.baseballGamePolicy = baseballGamePolicy;
    initializeNumbers();
  }

  /**
   * 정답 숫자를 초기화한다.
   */
  private void initializeNumbers() {
    List<Integer> randomNumbers = this.randomNumberGenerator.generateRandomNumbers(
        baseballGamePolicy.getMinNumber(),
        baseballGamePolicy.getMaxNumber(),
        baseballGamePolicy.getNumberSize()
    );

    numbers = new Numbers(randomNumbers);
  }

  /**
   * 게임을 재시작한다.
   */
  public void reStart() {
    initializeNumbers();
  }

  /**
   * 한 라운드를 진행한다.
   *
   * @param baseballGameRoundRequest 야구 게임 라운드 요청
   * @return 야구 게임 라운드 응답
   */
  public BaseballGameRoundResponse playRound(
      final BaseballGameRoundRequest baseballGameRoundRequest) {

    // 야구 게임 라운드 요청에서 사용자가 입력한 숫자를 가져온다.
    // 이 때, ArrayList 로 가져오는 이유는, Strike, Ball 을 계산할 때 index 를 사용해 성능을 높이기 위함이다.
    ArrayList<Integer> numbers = baseballGameRoundRequest.getArrayListNumbers();

    // 사용자가 입력한 숫자가 유효한지 검증한다.
    baseballGamePolicy.validateInputNumbers(numbers);

    // 사용자가 입력한 숫자와 정답 숫자를 비교하여 Strike, Ball 을 계산한다.
    Counts counts = calculateCounts(numbers);

    // 승리 여부를 확인한다.
    boolean isWin = baseballGamePolicy.isWin(counts.strikeCount());

    return new BaseballGameRoundResponse(counts, isWin);
  }

  /**
   * 사용자가 입력한 숫자와 정답 숫자를 비교하여 Strike, Ball 을 계산해 Counts 객체로 반환한다.
   *
   * @param numbers 사용자가 입력한 숫자
   * @return Counts 객체
   */
  private Counts calculateCounts(final ArrayList<Integer> numbers) {
    int strikeCount = 0;
    int ballCount = 0;

    for (int i = 0; i < numbers.size(); i++) {
      if (baseballGamePolicy.isStrike(this.numbers, numbers.get(i), i)) {
        strikeCount++;
      } else if (baseballGamePolicy.isBall(this.numbers, numbers.get(i), i)) {
        ballCount++;
      }
    }

    return new Counts(strikeCount, ballCount);
  }
}
