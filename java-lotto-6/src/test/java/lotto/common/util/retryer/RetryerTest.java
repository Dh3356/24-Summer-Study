package lotto.common.util.retryer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.common.exception.classes.CustomException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RetryerTest {
    private static int iteration;

    @BeforeAll
    static void setUp() {
        iteration = 3;
    }

    // 예외를 발생시키는 메서드
    private int throwRuntimeException() {
        if (iteration == 0) {
            return 1;
        }
        iteration--;
        throw new CustomException("예외 발생");
    }

    @DisplayName("예외 발생 시 해당 메서드를 재시도한다.")
    @Test
    void repeatUntilSuccess() {
        // given
        int expected = 1;

        // when
        int result = Retryer.repeatUntilSuccess(this::throwRuntimeException);

        // then
        assertEquals(expected, result);
    }
}
