package lotto.common.util.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoWinningCalculatorTest {

    private static LottoWinningCalculator lottoWinningCalculator;

    @BeforeAll
    static void setUp() {
        lottoWinningCalculator = new LottoWinningCalculator();
    }

    private static Stream<Object[]> provideLottoRankListAndProfit() {
        return Stream.of(
                new Object[]{List.of(LottoRank.MATCHED_SIX, LottoRank.NONE, LottoRank.NONE, LottoRank.NONE), 5.0E7},
                new Object[]{List.of(LottoRank.MATCHED_FIVE_WITH_BONUS, LottoRank.NONE, LottoRank.NONE, LottoRank.NONE),
                        750000.0},
                new Object[]{List.of(LottoRank.MATCHED_FIVE, LottoRank.NONE, LottoRank.NONE, LottoRank.NONE), 37500.0},
                new Object[]{List.of(LottoRank.MATCHED_FOUR, LottoRank.NONE, LottoRank.NONE, LottoRank.NONE), 1250.0},
                new Object[]{List.of(LottoRank.MATCHED_THREE, LottoRank.NONE, LottoRank.NONE, LottoRank.NONE), 125.0},
                new Object[]{List.of(LottoRank.NONE, LottoRank.NONE, LottoRank.NONE, LottoRank.NONE), 0.0}
        );
    }

    private static Stream<Object[]> provideLottoAndWinningLotto() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        return Stream.of(
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 5, 6)), winningLotto, LottoRank.MATCHED_SIX},
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 5, 7)), winningLotto, LottoRank.MATCHED_FIVE_WITH_BONUS},
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 5, 8)), winningLotto, LottoRank.MATCHED_FIVE},
                new Object[]{new Lotto(List.of(1, 2, 3, 4, 8, 9)), winningLotto, LottoRank.MATCHED_FOUR},
                new Object[]{new Lotto(List.of(1, 2, 3, 7, 8, 9)), winningLotto, LottoRank.MATCHED_THREE},
                new Object[]{new Lotto(List.of(1, 2, 7, 8, 9, 10)), winningLotto, LottoRank.NONE},
                new Object[]{new Lotto(List.of(1, 7, 8, 9, 10, 11)), winningLotto, LottoRank.NONE},
                new Object[]{new Lotto(List.of(7, 8, 9, 10, 11, 12)), winningLotto, LottoRank.NONE}
        );
    }

    @DisplayName("로또와 당청 로또를 비교하여 올바른 LottoRank 를 반환한다.")
    @ParameterizedTest(name = "Lotto: {0}, WinningLotto: {1}, Expected: {2}")
    @MethodSource("provideLottoAndWinningLotto")
    void calculateMatchedCount(Lotto lotto, WinningLotto winningLotto, LottoRank expected) {
        // when
        LottoRank actual = lottoWinningCalculator.calculateMatchedCount(lotto, winningLotto);

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("수익률을 계산한다.")
    @ParameterizedTest(name = "LottoRanks: {0}, Expected profit: {1}")
    @MethodSource("provideLottoRankListAndProfit")
    void calculateProfitRate(List<LottoRank> lottoRanks, double expectedProfit) {
        // given
        int lottoCount = 4;

        // when
        double profit = lottoWinningCalculator.calculateProfitRate(lottoRanks, lottoCount);

        // then
        assertEquals(expectedProfit, profit);
    }
}
