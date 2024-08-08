package racingcar.util.number_generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.common.util.number_generator.RandomNumberGenerator;

@DisplayName("RandomNumberGenerator 클래스 테스트")
public class RandomNumberGeneratorTest {

  private final RandomNumberGenerator RandomNumberGenerator = new RandomNumberGenerator();

  private static Stream<Arguments> provideMinAndMax() {
    return Stream.of(
        Arguments.of(0, 1),
        Arguments.of(1, 2),
        Arguments.of(2, 3),
        Arguments.of(3, 4),
        Arguments.of(4, 5)
    );
  }

  private static Stream<Arguments> provideInvalidRangeMinAndMax() {
    return Stream.of(
        Arguments.of(1, 0),
        Arguments.of(2, 1),
        Arguments.of(3, 2),
        Arguments.of(4, 3)
    );
  }

  @DisplayName("generateRandomNumber 메서드는 min 이상 max 이하의 랜덤한 숫자를 반환한다.")
  @ParameterizedTest(name = "min: {0}.{0}, max: {0}.{1}")
  @MethodSource("provideMinAndMax")
  void generateRandomNumberTest(int min, int max) {

    // when
    int result = RandomNumberGenerator.generateRandomNumber(min, max);

    // then
    assertThat(result).isBetween(min, max);
  }

  @DisplayName("generateRandomNumber 메서드는 min이 max보다 크면 IllegalArgumentException을 발생한다.")
  @ParameterizedTest(name = "min: {0}, max: {1}")
  @MethodSource("provideInvalidRangeMinAndMax")
  void generateRandomNumberExceptionTest(int min, int max) {

    // when & then
    assertThatThrownBy(() -> RandomNumberGenerator.generateRandomNumber(min, max))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
