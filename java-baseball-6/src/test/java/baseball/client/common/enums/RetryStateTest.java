package baseball.client.common.enums;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RetryState Enum 테스트")
public class RetryStateTest {

  @DisplayName("잘못된 int 값이 주어지면 IllegalArgumentException 을 던진다.")
  @Test
  void ofInvalidValueTest() {
    // given
    int invalidValue = -99;

    // when & then
    assertThrows(IllegalArgumentException.class, () -> RetryState.of(invalidValue));
  }
}
