package lotto.common.util.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.constant.LottoConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumberGeneratorTest {
    private static final int ITERATION = 1000;
    private static LottoNumberGenerator lottoNumberGenerator;

    @BeforeAll
    static void setUp() {
        lottoNumberGenerator = new LottoNumberGenerator();
    }

    // 파라미터 생성 메서드
    private static Stream<List<Integer>> generateLottoNumbers() {
        return Stream.generate(LottoNumberGeneratorTest::generateLottoNumbersSet)
                .limit(ITERATION);
    }

    // 로또 번호 생성 메서드
    private static List<Integer> generateLottoNumbersSet() {
        return lottoNumberGenerator.generateLottoNumbers();
    }

    @DisplayName("랜덤하고 중복되지 않는 로또 번호 리스트를 생성한다.")
    @ParameterizedTest(name = "lottoNumbers: {0}")
    @MethodSource("generateLottoNumbers")
    void testGenerateLottoNumbers(List<Integer> lottoNumbers) {
        // then
        assertLottoNumbers(lottoNumbers);
    }

    private void assertLottoNumbers(List<Integer> lottoNumbers) {
        assertAll(
                () -> assertThat(lottoNumbers).hasSize(LottoConstants.LOTTO_SIZE),
                () -> assertThat(lottoNumbers).allMatch(
                        number -> number >= LottoConstants.LOTTO_MIN && number <= LottoConstants.LOTTO_MAX)
        );
    }
}
