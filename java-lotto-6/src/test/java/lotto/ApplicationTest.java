package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static lotto.common.exception.CustomExceptionHandler.ERROR_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ApplicationTest extends NsTest {

    // 올바르지 않은 로또 구매 금액 생성
    private static Stream<String> provideInvalidPurchaseMoneys() {
        return Stream.of("1000j", "1000.6", "1000sdf", "hello", "1000 1000");
    }

    // 올바르지 않은 당첨 번호 생성
    private static Stream<String> provideInvalidWinningNumbers() {
        return Stream.of("1,2,3,4,5,s", "s,2,d,4,5", "asdfsadfasdf", "a,b,c,d,e,f,g");
    }

    // 올바르지 않은 보너스 번호 생성
    private static Stream<String> provideInvalidBonusNumbers() {
        return Stream.of("a", "sadf", "1.2", "123 213");
    }

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @DisplayName("로또 구입 금액이 정수가 아닐 시 예외가 발생한다.")
    @ParameterizedTest(name = "invalidPurchaseMoney: {0}")
    @MethodSource("provideInvalidPurchaseMoneys")
    void inputInvalidPurchaseMoney(String invalidPurchaseMoney) {
        assertSimpleTest(() -> {
            runException(invalidPurchaseMoney);
            AssertionsForClassTypes.assertThat(output()).contains(ERROR_PREFIX);
        });
    }

    @DisplayName("당첨 번호가 정수가 아닐 시 예외가 발생한다.")
    @ParameterizedTest(name = "invalidWinningNumbers: {0}")
    @MethodSource("provideInvalidWinningNumbers")
    void inputInvalidWinningNumbers(String invalidWinningNumbers) {
        assertSimpleTest(() -> {
            runException("1000", invalidWinningNumbers);
            assertThat(output()).contains(ERROR_PREFIX);
        });
    }

    @DisplayName("보너스 번호가 정수가 아닐 시 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInvalidBonusNumbers")
    void inputInvalidBonusNumber(String invalidBonusNumber) {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", invalidBonusNumber);
            assertThat(output()).contains(ERROR_PREFIX);
        });
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
