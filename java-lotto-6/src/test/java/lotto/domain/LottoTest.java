package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import lotto.common.constant.LottoConstants;
import lotto.common.util.generator.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    private static List<Integer> validNumbers;

    @BeforeAll
    static void setUp() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        validNumbers = lottoNumberGenerator.generateLottoNumbers();
    }

    // 유효 범위를 벗어난 숫자들을 생성
    private static Stream<List<Integer>> provideInvalidRangeNumbers() {
        List<Integer> includeUpperNumbers = new LinkedList<>(validNumbers);
        includeUpperNumbers.set(0, LottoConstants.LOTTO_MAX + 1);

        List<Integer> includeLowerNumbers = new LinkedList<>(validNumbers);
        includeLowerNumbers.set(0, LottoConstants.LOTTO_MIN - 1);

        return Stream.of(includeUpperNumbers, includeLowerNumbers);
    }

    // 로또 크기를 넘어가는 숫자들을 생성
    private static Stream<List<Integer>> provideInvalidSizeNumbers() {
        List<List<Integer>> overSizeNumbers = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            List<Integer> numbers = new LinkedList<>(validNumbers);
            numbers.add(i);
            overSizeNumbers.add(numbers);
        }
        return overSizeNumbers.stream();
    }

    // 중복된 숫자들을 생성
    private static Stream<List<Integer>> provideMultipleNumbers() {
        List<List<Integer>> multipleNumbers = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            List<Integer> numbers = new ArrayList<>(validNumbers);
            numbers.set(0, validNumbers.get(1));
            multipleNumbers.add(numbers);
        }
        return multipleNumbers.stream();
    }

    @DisplayName("로또 번호의 개수가 로또 크기를 넘어가면 예외가 발생한다.")
    @ParameterizedTest(name = "Invalid numbers: {0}")
    @MethodSource("provideInvalidSizeNumbers")
    void createLottoByOverSize(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @ParameterizedTest(name = "Invalid numbers: {0}")
    @MethodSource("provideMultipleNumbers")
    void createLottoByDuplicatedNumber(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @DisplayName("로또 번호의 유효 범위를 벗어난 경우 예외가 발생한다.")
    @ParameterizedTest(name = "Invalid numbers: {0}")
    @MethodSource("provideInvalidRangeNumbers")
    void createLottoByInvalidNumbers(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @DisplayName("로또 번호에 특정 숫자가 포함되어 있는지 확인한다.")
    @Test
    void contains() {
        Lotto lotto = new Lotto(validNumbers);

        validNumbers.forEach(number -> assertTrue(lotto.contains(number)));
    }
}