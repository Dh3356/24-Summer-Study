package baseball.server.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Numbers 클래스 테스트")
public class NumbersTest {

  @DisplayName("특정 숫자가 인덱스는 다르지만 포함되어있는지 확인한다.")
  @Test
  void containsButNotSameIndex() {
    // given
    Numbers numbers = new Numbers(List.of(1, 2, 3));

    // when
    boolean result = numbers.containsButNotSameIndex(1, 1);

    // then
    assertTrue(result);
  }

  @DisplayName("특정 숫자가 인덱스까지 정확하게 일치하는지 확인한다.")
  @Test
  void isExactMatch() {
    // given
    Numbers numbers = new Numbers(List.of(1, 2, 3));

    // when
    boolean result = numbers.isExactMatch(1, 0);

    // then
    assertTrue(result);
  }

  @DisplayName("인덱스가 범위를 벗어나면 예외를 발생시킨다.")
  @Test
  void validateIndex() {
    // given
    Numbers numbers = new Numbers(List.of(1, 2, 3));

    // when & then
    assertAll(
        () -> assertThrows(IllegalArgumentException.class, () -> numbers.isExactMatch(1, -1)),
        () -> assertThrows(IllegalArgumentException.class, () -> numbers.isExactMatch(1, 3))
    );
  }
}
