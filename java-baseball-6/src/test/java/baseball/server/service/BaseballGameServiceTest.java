package baseball.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import baseball.dto.request.BaseballGameRoundRequest;
import baseball.server.common.config.BaseballGameServerConfig;
import baseball.server.domain.Counts;
import baseball.server.service.game_policy.BaseballGamePolicy;
import baseball.server.util.number_generator.RandomNumberGenerator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("BaseballGameService 클래스 테스트")
public class BaseballGameServiceTest {

  private static RandomNumberGenerator randomNumberGenerator;
  private static BaseballGamePolicy baseballGamePolicy;
  private static BaseballGameService baseballGameService;
  private static int minNumber;
  private static int maxNumber;
  private static int numberSize;

  @BeforeAll
  static void setUp() {
    baseballGamePolicy = BaseballGameServerConfig.getBaseballGamePolicy();
    randomNumberGenerator = mock(
        BaseballGameServerConfig.getRandomNumberGenerator().getClass()
    );
    baseballGameService = new BaseballGameService(baseballGamePolicy, randomNumberGenerator);

    minNumber = baseballGamePolicy.getMinNumber();
    maxNumber = baseballGamePolicy.getMaxNumber();
    numberSize = baseballGamePolicy.getNumberSize();
  }

  private static Stream<Arguments> provideRoundArguments() {
    List<Integer> gamaNumbers = IntStream.range(minNumber, minNumber + numberSize).boxed().toList();

    return Stream.of(
        Arguments.of(
            gamaNumbers,
            gamaNumbers,
            new Counts(3, 0)
        ),
        Arguments.of(
            gamaNumbers,
            List.of(minNumber + 1, minNumber + 2, maxNumber),
            new Counts(0, 2)
        )
    );
  }

  @DisplayName("게임을 재시작하면 정답 숫자가 변경된다.")
  @Test
  void reStart() {
    // given
    List<Integer> numbers1 = IntStream.range(minNumber, minNumber + numberSize).boxed().toList();
    List<Integer> numbers2 = IntStream.range(minNumber + 1, minNumber + numberSize + 1).boxed()
        .toList();

    // when
    mockNumberGenerator(numbers1);
    baseballGameService.reStart();
    Counts counts1 = getCounts(numbers1);

    mockNumberGenerator(numbers2);
    baseballGameService.reStart();
    Counts counts2 = getCounts(numbers1);

    // then
    assertNotEquals(counts1, counts2);
  }

  @DisplayName("라운드 결과를 올바르게 계산해 반환한다.")
  @ParameterizedTest(name = "게임 숫자: {0}, 시도 숫자: {1}, 예상 결과: {2}")
  @MethodSource("provideRoundArguments")
  void playRound(List<Integer> gameNumbers, List<Integer> tryNumbers, Counts expectedCounts) {
    // given
    mockNumberGenerator(gameNumbers);
    baseballGameService.reStart();

    // when
    Counts counts = getCounts(tryNumbers);

    // then
    assertEquals(expectedCounts, counts);
  }

  private void mockNumberGenerator(List<Integer> generatedNumbers) {
    when(randomNumberGenerator.generateRandomNumbers(
        anyInt(), anyInt(), anyInt()
    )).thenReturn(generatedNumbers);
  }

  private Counts getCounts(List<Integer> numbers) {
    return baseballGameService.playRound(new BaseballGameRoundRequest(numbers)).getCounts();
  }
}

