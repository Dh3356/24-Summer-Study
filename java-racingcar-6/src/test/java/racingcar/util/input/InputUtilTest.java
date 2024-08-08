package racingcar.util.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.ConsoleTest;
import racingcar.common.util.input.InputUtil;

@DisplayName("InputUtil 클래스 테스트")
public class InputUtilTest extends ConsoleTest {

  private static Stream<Integer> provideInt() {
    return Stream.of(1, 2, 3, 4, 5);
  }

  private static Stream<String> provideInvalidInt() {
    return Stream.of("a", "b, c", "", " ", null, "\n");
  }

  @AfterEach
  void tearDown() {
    Console.close();
  }

  @DisplayName("readInt 에서 정상적으로 숫자를 입력받는다.")
  @ParameterizedTest(name = "입력값: {0}")
  @MethodSource("provideInt")
  void readIntTest(int assertValue) {
    // given
    command(String.valueOf(assertValue));

    // when
    int result = InputUtil.readInt();

    // then
    assertThat(result).isEqualTo(assertValue);
  }

  @DisplayName("readInt 에서 숫자가 아닌 값을 입력하면 예외가 발생한다.")
  @ParameterizedTest(name = "입력값: {0}")
  @MethodSource("provideInvalidInt")
  void readIntExceptionTest(String input) {
    // given
    command(input);

    // when & then
    assertThatThrownBy(InputUtil::readInt)
        .isInstanceOf(IllegalArgumentException.class);
  }
}
