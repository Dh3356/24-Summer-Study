package baseball.server.service.game_policy;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import baseball.server.common.config.BaseballGameServerConfig;
import baseball.server.domain.Numbers;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("NormalBaseballGamePolicy 클래스 테스트")
public class NormalBaseballGameServicePolicyTest {

  private static final BaseballGamePolicy baseballGamePolicy = BaseballGameServerConfig.getBaseballGamePolicy();
  private static final int minNumber = baseballGamePolicy.getMinNumber();
  private static final int maxNumber = baseballGamePolicy.getMaxNumber();
  private static final int numberSize = baseballGamePolicy.getNumberSize();

  private static Stream<List<Integer>> provideInvalidSizeNumbers() {
    int smallerNumberSize = numberSize - 1;
    int biggerNumberSize = numberSize + 1;
    return Stream.of(
        IntStream.range(minNumber, minNumber + smallerNumberSize).boxed().toList(),
        IntStream.range(minNumber, minNumber + biggerNumberSize).boxed().toList()
    );
  }

  private static Stream<List<Integer>> provideConflictedNumbers() {
    return Stream.of(
        List.of(minNumber, minNumber, minNumber),
        List.of(minNumber, minNumber + 1, minNumber),
        List.of(minNumber, minNumber, minNumber + 1)
    );
  }

  private static Stream<List<Integer>> provideInvalidBoundNumbers() {

    return Stream.of(
        List.of(minNumber - 1, minNumber, minNumber + 1),
        List.of(maxNumber - 1, maxNumber, maxNumber + 1)
    );
  }

  private static Stream<List<Integer>> provideValidNumbers() {
    return Stream.of(
        IntStream.range(minNumber, minNumber + numberSize).boxed().toList()
    );
  }

  private static Stream<Arguments> provideStrikeArguments() {
    List<Integer> numbers = List.of(1, 2, 3);
    return Stream.of(
        Arguments.of(numbers, 1, 0),
        Arguments.of(numbers, 2, 1),
        Arguments.of(numbers, 3, 2)
    );
  }

  private static Stream<Arguments> provideBallArguments() {
    List<Integer> numbers = List.of(1, 2, 3);
    return Stream.of(
        Arguments.of(numbers, 2, 0),
        Arguments.of(numbers, 3, 1),
        Arguments.of(numbers, 1, 2)
    );
  }

  @DisplayName("잘못된 갯수의 숫자들이 입력되면 예외를 발생시킨다.")
  @ParameterizedTest(name = "입력 숫자 리스트: {0}")
  @MethodSource("provideInvalidSizeNumbers")
  void validateNumberSize(List<Integer> invalidSizeNumbers) {
    // when & then
    assertThrows(IllegalArgumentException.class,
        () -> baseballGamePolicy.validateInputNumbers(invalidSizeNumbers));
  }

  @DisplayName("중복된 숫자가 입력되면 예외를 발생시킨다.")
  @ParameterizedTest(name = "입력 숫자 리스트: {0}")
  @MethodSource("provideConflictedNumbers")
  void validateNumbersConflicts(List<Integer> conflictedNumbers) {
    // when & then
    assertThrows(IllegalArgumentException.class,
        () -> baseballGamePolicy.validateInputNumbers(conflictedNumbers));
  }

  @DisplayName("잘못된 범위의 숫자가 입력되면 예외를 발생시킨다.")
  @ParameterizedTest(name = "입력 숫자 리스트: {0}")
  @MethodSource("provideInvalidBoundNumbers")
  void validateNumbersRange(List<Integer> invalidBoundNumbers) {
    // when & then
    assertThrows(IllegalArgumentException.class,
        () -> baseballGamePolicy.validateInputNumbers(invalidBoundNumbers));
  }

  @DisplayName("정상적인 숫자들이 입력되면 예외를 발생시키지 않는다.")
  @ParameterizedTest(name = "입력 숫자 리스트: {0}")
  @MethodSource("provideValidNumbers")
  void validateInputNumbers(List<Integer> validNumbers) {
    // when & then
    assertDoesNotThrow(() -> baseballGamePolicy.validateInputNumbers(validNumbers));
  }

  @DisplayName("스트라이크 여부를 확인한다.")
  @ParameterizedTest(name = "정답 숫자: {0}, 숫자: {1}, 인덱스: {2}")
  @MethodSource("provideStrikeArguments")
  void isStrike(List<Integer> numbers, int number, int index) {
    // when & then
    assertTrue(baseballGamePolicy.isStrike(new Numbers(numbers), number, index));
  }

  @DisplayName("볼 여부를 확인한다.")
  @ParameterizedTest(name = "정답 숫자: {0}, 숫자: {1}, 인덱스: {2}")
  @MethodSource("provideBallArguments")
  void isBall(List<Integer> numbers, int number, int index) {
    // when & then
    assertTrue(baseballGamePolicy.isBall(new Numbers(numbers), number, index));
  }

  @DisplayName("승리 여부를 확인한다.")
  @Test
  void isWin() {
    // given
    int strikeCount = numberSize;

    // when & then
    assertAll(
        () -> assertFalse(baseballGamePolicy.isWin(strikeCount - 1)),
        () -> assertTrue(baseballGamePolicy.isWin(strikeCount)),
        () -> assertFalse(baseballGamePolicy.isWin(strikeCount + 1))
    );
  }
}
