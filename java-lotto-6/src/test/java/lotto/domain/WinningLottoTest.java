package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.constant.LottoConstants;
import lotto.common.util.generator.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class WinningLottoTest {
    private static List<Integer> validNumbers;

    @BeforeAll
    static void setUp() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        validNumbers = lottoNumberGenerator.generateLottoNumbers();
    }

    // 유효 범위를 벗어난 보너스 번호를 생성
    private static Stream<Integer> provideInvalidRangeBonusNumber() {
        return Stream.of(
                LottoConstants.LOTTO_MIN - 1,
                LottoConstants.LOTTO_MAX + 1
        );
    }

    // 로또와 보너스 번호를 생성
    private static Stream<Object[]> provideLottoNumbersAndBonusNumber() {
        return Stream.of(
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7), false},
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        new WinningLotto(List.of(1, 32, 3, 6, 29, 4), 5), true},
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        new WinningLotto(List.of(3, 4, 5, 6, 7, 8), 1), true}
        );

    }

    // 당첨 로또 번호와 중복되는 보너스 번호를 생성
    private static Stream<Object[]> provideConflictLottoNumbersAndBonusNumber() {
        return Stream.of(
                new Object[]{List.of(1, 2, 3, 4, 5, 6), 6},
                new Object[]{List.of(1, 2, 3, 4, 5, 6), 5}
        );
    }

    @DisplayName("유효하지 않은 범위의 보너스 번호로 보너스 로또를 생성하면 예외가 발생한다.")
    @ParameterizedTest(name = "Invalid bonus number: {0}")
    @MethodSource("provideInvalidRangeBonusNumber")
    void createWinningLottoByInvalidBonusNumberRange(int bonusNumber) {
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(validNumbers, bonusNumber));
    }

    @DisplayName("당첨 로또에 보너스 번호가 포함되면 예외가 발생한다.")
    @ParameterizedTest(name = "Lotto numbers: {0}, Bonus number: {1}")
    @MethodSource("provideConflictLottoNumbersAndBonusNumber")
    void createWinningLottoByConflictBonusNumber(List<Integer> numbers, int bonusNumber) {
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(numbers, bonusNumber));
    }

    @DisplayName("사용자 로또에 보너스 번호가 포함되어 있는지 확인한다.")
    @ParameterizedTest(name = "Lotto: {0}, WinningLotto: {1}, Expected: {2}")
    @MethodSource("provideLottoNumbersAndBonusNumber")
    void containsBonusNumber(Lotto lotto, WinningLotto winningLotto, boolean expected) {
        boolean actual = winningLotto.containsBonusNumber(lotto);

        assertEquals(expected, actual);
    }
}
