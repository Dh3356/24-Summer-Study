package baseball.client.util.dispatcher;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import baseball.protocol.response.ServerResponse;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("DispatcherUtil 클래스 테스트")
public class DispatcherUtilTest {

  private static Stream<Arguments> provideValidDataAndType() {
    return Stream.of(
        Arguments.of("test", String.class),
        Arguments.of(1, Integer.class),
        Arguments.of(1L, Long.class),
        Arguments.of(1.0, Double.class),
        Arguments.of(1.0f, Float.class),
        Arguments.of(true, Boolean.class)
    );
  }

  private static Stream<Arguments> provideInvalidType() {
    return Stream.of(
        Arguments.of("test", Integer.class),
        Arguments.of(1, String.class),
        Arguments.of(1L, String.class),
        Arguments.of(1.0, String.class),
        Arguments.of(1.0f, String.class),
        Arguments.of(true, String.class)
    );
  }

  private static Stream<Arguments> provideInvalidData() {
    return Stream.of(
        Arguments.of(null, String.class),
        Arguments.of(null, Integer.class),
        Arguments.of(null, Long.class),
        Arguments.of(null, Double.class),
        Arguments.of(null, Float.class),
        Arguments.of(null, Boolean.class)
    );
  }

  @DisplayName("getDataAsType 메서드는 서버 응답에서 원하는 타입의 데이터를 추출한다.")
  @ParameterizedTest(name = "data: {0}, type: {1}")
  @MethodSource("provideValidDataAndType")
  void getDataAsTypeTest(Object data, Class<?> type) {
    // given
    ServerResponse<Object> response = new ServerResponse<>(data);

    // when
    Object result = DispatcherUtil.getDataAsType(response, type);

    // then
    assertTrue(type.isInstance(result));
  }

  @DisplayName("getDataAsType 메서드는 올바르지 않은 타입의 데이터를 추출하면 ClassCastException 를 발생시킨다.")
  @ParameterizedTest(name = "data: {0}, type: {1}")
  @MethodSource("provideInvalidType")
  void validateType(Object data, Class<?> type) {
    // given
    ServerResponse<Object> response = new ServerResponse<>(data);

    // when & then
    assertThrows(ClassCastException.class, () -> DispatcherUtil.getDataAsType(response, type));
  }

  @DisplayName("getDataAsType 메서드는 데이터가 잘못되었으면 IllegalStateException 를 발생시킨다.")
  @ParameterizedTest(name = "data: {0}, type: {1}")
  @MethodSource("provideInvalidData")
  void validateData(Object data, Class<?> type) {
    // given
    ServerResponse<Object> response = new ServerResponse<>(data);

    // when & then
    assertThrows(IllegalStateException.class, () -> DispatcherUtil.getDataAsType(response, type));
  }
}
