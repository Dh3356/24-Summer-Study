package baseball.client.view.output;

import static baseball.client.view.output.message.OutputMessage.BALL;
import static baseball.client.view.output.message.OutputMessage.NOTHING;
import static baseball.client.view.output.message.OutputMessage.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import baseball.ConsoleTest;
import baseball.dto.response.BaseballGameRoundResponse;
import baseball.server.domain.Counts;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("BaseballGameRoundOutputResultView 클래스 테스트")
public class BaseballGameRoundOutputResultViewTest extends ConsoleTest {

  private final BaseballGameRoundOutputResultView baseballGameRoundOutputResultView = new BaseballGameRoundOutputResultView();

  private static Stream<Arguments> provideBaseballGameRoundOutputResult() {
    return Stream.of(
        Arguments.of("3" + STRIKE, createNotWinBaseballGameRoundResponse(new Counts(3, 0))),
        Arguments.of("1" + BALL + " 1" + STRIKE,
            createNotWinBaseballGameRoundResponse(new Counts(1, 1))),
        Arguments.of("1" + STRIKE, createNotWinBaseballGameRoundResponse(new Counts(1, 0))),
        Arguments.of("1" + BALL, createNotWinBaseballGameRoundResponse(new Counts(0, 1))),
        Arguments.of(NOTHING, createNotWinBaseballGameRoundResponse(new Counts(0, 0)))
    );
  }

  private static BaseballGameRoundResponse createNotWinBaseballGameRoundResponse(Counts counts) {
    return new BaseballGameRoundResponse(counts, false);
  }

  @DisplayName("print 메서드 호출 시 정상적으로 출력되는지 확인")
  @ParameterizedTest(name = "expected: {0}, provided: {1}")
  @MethodSource("provideBaseballGameRoundOutputResult")
  void printTest(String expected, BaseballGameRoundResponse baseballGameRoundResponse) {
    baseballGameRoundOutputResultView.print(baseballGameRoundResponse);

    assertThat(output()).isEqualTo(expected);
  }
}
