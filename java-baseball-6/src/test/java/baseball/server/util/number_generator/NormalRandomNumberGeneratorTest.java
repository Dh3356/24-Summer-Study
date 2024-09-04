package baseball.server.util.number_generator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("NormalRandomNumberGenerator 클래스 테스트")
public class NormalRandomNumberGeneratorTest {

  private final NormalRandomNumberGenerator normalRandomNumberGenerator = new NormalRandomNumberGenerator();

  static Stream<Arguments> provideValidMinAndMaxAndSize() {
    return Stream.of(
        Arguments.of(1, 9, 3),
        Arguments.of(1, 9, 4),
        Arguments.of(1, 9, 5),
        Arguments.of(1, 9, 6),
        Arguments.of(1, 9, 7)
    );
  }

  static Stream<Arguments> provideInvalidGraterMinAndMax() {
    return Stream.of(
        Arguments.of(2, 1, 1),
        Arguments.of(3, 2, 1),
        Arguments.of(4, 3, 1),
        Arguments.of(5, 4, 1),
        Arguments.of(6, 5, 1)
    );
  }

  static Stream<Arguments> provideInvalidSize() {
    return Stream.of(
        Arguments.of(1, 8, 9),
        Arguments.of(1, 9, 10),
        Arguments.of(1, 9, 11),
        Arguments.of(1, 9, 12),
        Arguments.of(1, 9, 13)
    );
  }

  @DisplayName("generateRandomNumbers 메서드는 min 이상 max 이하의 랜덤한 숫자를 size 만큼 반환한다.")
  @ParameterizedTest(name = "min: {0}, max: {1}, size: {2}")
  @MethodSource("provideValidMinAndMaxAndSize")
  void generateRandomNumbersTest(int min, int max, int size) {
    // given
    Set<Integer> generatedNumbers = new HashSet<>(
        normalRandomNumberGenerator.generateRandomNumbers(min, max, size));

    // when & then
    assertAll(
        () ->
            assertEquals(size, generatedNumbers.size()),
        () -> generatedNumbers.forEach(number -> {
          assertAll(
              () -> assertTrue(number >= min),
              () -> assertTrue(number <= max)
          );
        })
    );
  }

  @DisplayName("generateRandomNumbers 메서드는 min 이 max 보다 크면 IllegalArgumentException 을 발생한다.")
  @ParameterizedTest(name = "min: {0}, max: {1}, size: {2}")
  @MethodSource("provideInvalidGraterMinAndMax")
  void validateMinAndMax(int min, int max, int size) {
    // when & then
    assertThrows(IllegalArgumentException.class,
        () -> normalRandomNumberGenerator.generateRandomNumbers(min, max, size));
  }

  @DisplayName("generateRandomNumbers 메서드는 size 가 max - min + 1 보다 크면 IllegalArgumentException 을 발생한다.")
  @ParameterizedTest(name = "min: {0}, max: {1}, size: {2}")
  @MethodSource("provideInvalidSize")
  void validateSize(int min, int max, int size) {
    // when & then
    assertThrows(IllegalArgumentException.class,
        () -> normalRandomNumberGenerator.generateRandomNumbers(min, max, size));
  }
}
